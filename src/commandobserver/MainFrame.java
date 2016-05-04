package commandobserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import commandobserver.Command.Move;
import commandobserver.Command.Undo;
import factory.BattleCruiserFactory.BattleCruiser;
import factory.BattleShooterFactory.BattleShooter;
import factory.BattleStarFactory.BattleStar;
import factory.Factory.Ships;
import factory.MasterShipFactory.Master;
import factory.MasterShipFactory.State;
import skywars.Configs;

public class MainFrame extends JFrame {
	//used to draw label and buttons on the GUI
	private static final long serialVersionUID = 1L;
	ShipPanel skyPanel;
	JPanel downPanel = new JPanel();
	JPanel upPanel = new JPanel();
	JRadioButton passive = new JRadioButton("Passive");
	JRadioButton aggressive = new JRadioButton("Aggressive");
	JButton move = new JButton("Move");
	JButton sound = new JButton("Sound");
	JButton undo = new JButton("Undo");

	JLabel enemyNumber = new JLabel();

	Command moveCommand = new Move(this);
	Command undoCommand = new Undo(this);
	


	private ActionListener listener = new ActionListener() {
		@Override
		//declaring an action listener to know when a button is been clicked
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == move) {
				moveCommand.executeCommand();
			} else if (source == undo) {
				undoCommand.executeCommand();
			} 

		}
	};
	//creating a list of observers
	List<Observer> observers = new ArrayList<>();
	IDataFetcher fetcher;



	public MainFrame(IDataFetcher fetcher) {
		this.fetcher = fetcher;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());


		skyPanel = new ShipPanel(fetcher);
		this.add(skyPanel, BorderLayout.CENTER);

		//adding a panel to the GUI where the move, undo, restart button are been added on
		this.add(downPanel, BorderLayout.SOUTH);
		downPanel.setLayout(new GridLayout(1, 6));

		//adding a panel to the GUI where the no of destroyed enemies and sound button are been added on
		this.add(upPanel, BorderLayout.NORTH);
		upPanel.setLayout(new GridLayout(1, 6));

		//adding the buttons to the panel
		downPanel.add(passive);
		downPanel.add(aggressive);
		downPanel.add(move);
		downPanel.add(undo);
		upPanel.add(enemyNumber);
		upPanel.add(sound);

		//adding a button on the grid passive and aggresive mode
		ButtonGroup state = new ButtonGroup();
		state.add(passive);
		state.add(aggressive);
		passive.setSelected(true);


		move.addActionListener(listener);
		undo.addActionListener(listener);
		sound.addActionListener(new Music());

	}
	//returning a list of observers
	public List<Observer> getObservers() {
		return observers;
	}

	//used to get the state of the mastership selected
	public State getMasterShipMode() {
		return passive.isSelected() ? State.PASSIVE : State.AGGRESSIVE;

	}

	public void setMessage(String string) {

		enemyNumber.setText(string);

	}


	//used to display and show the number of destroyed ships
	@Override
	public void repaint() {
		super.repaint();
		enemyNumber.setText("Numbers of Enemies Ships Destroyed: " + fetcher.destroyedship());
	}
	// 
	public interface Observer {

		void onUndo();

		void onMove();

		void onReset();
	}

	public interface IDataFetcher {
		List<Ships> ships(int x, int y);

		int destroyedship();

	}

	private static class ShipPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private final IDataFetcher fetcher;

		public ShipPanel(IDataFetcher fetcher) {
			this.fetcher = fetcher;

		}


		//used to draw the sky
		@Override
		public void paintComponent(Graphics graphics) {
			int w = this.getWidth();
			int h = this.getHeight();
			int gridW = w / (Configs.X_MAXIMUM + 1);
			int gridH = h / (Configs.Y_MAXIMUM + 1);

			BufferedImage image = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);
			Graphics imageGraphics = image.getGraphics();
			imageGraphics.setColor(Color.DARK_GRAY);
			imageGraphics.fillRect(0, 0, w, h);

			for (int x = 0; x <= Configs.X_MAXIMUM; x++) {
				for (int y = 0; y <= Configs.Y_MAXIMUM; y++) {
					List<Ships> ships = fetcher.ships(x, y);
					this.createShips(imageGraphics, ships, x * gridW + 5, y
							* gridH + 5, gridW - 10, gridH - 10);
				}
			}

			graphics.drawImage(image, 0, 0, null);
		}


		//methos used to draw and create the ship objects
		private void createShips(Graphics graphics, List<Ships> ships, int x,
				int y, int w, int h) {
			int columnCount = 1;
			int rowCount = 1;
			while (columnCount * rowCount < ships.size()) {
				if (columnCount == rowCount) {
					rowCount++;
				} else {
					columnCount++;
				}
			}

			int columnW = w / columnCount;
			int rowH = h / rowCount;

			int column = 0;
			int row = 0;
			for (Ships ship : ships) {
				Image image = this.getImage(ship);
				graphics.drawImage(image, x + column * columnW, y + row
						* rowH, columnW, rowH, null);

				column++;
				if (column == columnCount) {
					column = 0;
					row++;
				}
			}
		}
		//Finds,Get and Loads ship image using the object Name
		private Image getImage(Ships ship) {
			String imageName = Images.BATTLE_STAR;
			if (ship instanceof BattleCruiser) {
				imageName = Images.BATTLE_CRUISER;
			} else if (ship instanceof BattleShooter) {
				imageName = Images.BATTLE_SHOOTER;
			} else if (ship instanceof BattleStar) {
				imageName = Images.BATTLE_STAR;
			} else if (ship instanceof Master) {
				imageName = Images.MASTER_SHIP;
			}
			return Images.getImage(imageName);
		}
	}


}



