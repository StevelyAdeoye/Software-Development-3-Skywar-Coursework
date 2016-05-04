package factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import commandobserver.MainFrame.IDataFetcher;

import factory.Factory.Ships;


 

public class Data implements IDataFetcher {
	private final Stack<Grid> gridStack = new Stack<>();
	private Grid grid = new Grid();

	public Grid getGrid() {
		return grid;
	}

	@Override
	//creating a list of ships
	public List<Ships> ships(int x, int y) {
		List<Ships> ships = new ArrayList<>();
		for (Ships ship : grid.getShips()) {
			if (ship.getX() == x && ship.getY() == y) {
				ships.add(ship);
			}
		}
		return ships;
	}

	@Override
	//return the number of destroyed ships
	public int destroyedship() {
		return grid.destroyedship();
	}
	
    //method to remove all the ships off the Grid
	public void reset() {
		gridStack.clear();
		grid.getShips().clear();
	}

	public void duplicate() {
		Grid newField = grid.duplicate();
		gridStack.push(grid);
		grid = newField;
	}
	//method to check if the grid is empty or not
	public boolean previous() {
		if (gridStack.isEmpty()) {
			return false;
		} else {
			grid = gridStack.pop();
			return true;
		}
	}
	//creating a list of object Ships
	public static class Grid {
		private List<Ships> ships = new ArrayList<>();
		private int destroyedship = 0;

		public List<Ships> getShips() {
			return ships;
		}

		public void setShips(List<Ships> ships) {
			this.ships = ships;
		}

		public int destroyedship() {
			return destroyedship;
		}

		public void setDestroyedship(int destroyedship) {
			this.destroyedship = destroyedship;
		}

		public Grid duplicate() {
			Grid field = new Grid();
			List<Ships> newShips = field.getShips();
			for (Ships ship : ships) {
				newShips.add(ship.duplicate());
			}
			field.destroyedship = this.destroyedship;
			return field;
		}
	}

	
	
	}



