package skywars;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import commandobserver.MainFrame;
import commandobserver.MainFrame.Observer;

import factory.BattleCruiserFactory;
import factory.BattleShooterFactory;
import factory.BattleStarFactory;
import factory.Data;
import factory.MasterShipFactory;
import factory.Factory.Ships;
import factory.MasterShipFactory.Master;
import factory.MasterShipFactory.State;


//(Observer pattern)  


public class Sky implements Observer {
	public static void main(String[] strings) {
		new Sky();
	}

	private static final Random Randgenerate = new Random();

	private final Data data = new Data();
	private final MainFrame frame = new MainFrame(data);

	//method to draw the GUI frame, set title and set size
	public Sky() {

		int w = 500;
		int h = 500;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;
		frame.setBounds(x, y, w, h);
		frame.setTitle("SKY WARS");
		frame.setVisible(true);
		frame.getObservers().add(this);
		this.onReset();
	}


	@Override
	//method to clear the sky of all enemy ship and left with just the MasterShip
	public void onReset() {
		data.reset();
		Ships ship = MasterShipFactory.INSTANCE.create();
		data.getGrid().getShips().add(ship);
		frame.repaint();
	}



	@Override
	//method to undo patterns and return ships to previous 
	public void onUndo() {
		data.previous();
		frame.repaint();

	}

	@Override
	//method to move image
	public void onMove() {
		Master master = CommonTool.getMaster(data);
		if (master == null) {
			return;
		}
		data.duplicate();

		List<Ships> ships = data.getGrid().getShips();
		for (Ships ship : ships) {
			//randomly assign image to grids
			Move direction = this.RandomDirection();
			while (!ship.move(direction)) {
				direction = this.RandomDirection();
			}
		}
		//method to check what state as been selected 
		Strategy strategy = (frame.getMasterShipMode() == State.PASSIVE ? PassiveMode.INSTANCE
				: AggressiveMode.INSTANCE);
		strategy.handleConflicts(data);

		//if statement to randomly create enemy ships and add it into the Game
		if (Randgenerate.nextInt(3) == 0) {
			int enemyType = Randgenerate.nextInt(3);
			Ships enemyShip = null;
			switch (enemyType) {
			case 0:
				enemyShip = BattleCruiserFactory.INSTANCE.create();
				break;
			case 1:
				enemyShip = BattleShooterFactory.INSTANCE.create();
				break;
			case 2:
				enemyShip = BattleStarFactory.INSTANCE.create();
				break;
			}
			data.getGrid().getShips().add(enemyShip);
		}

		frame.repaint();

		if (CommonTool.getMaster(data) == null) {
			//message to notify the Master Ship has been destroyed
			JOptionPane.showMessageDialog(frame, "The MasterShip Has Been Destroyed...!!!!");
			//Closes the frame when the mastership is destroyed
			frame.dispose();
		}

	}
	//method to declare the random movement of the ship which can only be LEFT,RIGHT,UP or DOWN
	private Move RandomDirection() {
		Move[] directions = Move.values();
		int index = Randgenerate.nextInt(directions.length);
		return directions[index];
	}
}
