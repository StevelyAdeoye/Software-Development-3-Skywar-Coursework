package skywars;

import java.util.List;

import factory.Data;
import factory.Data.Grid;
import factory.Factory.Ships;
import factory.MasterShipFactory.Master;


 // (Strategy pattern) 
 
public interface Strategy {
	void handleConflicts(Data model);
}

enum AggressiveMode implements Strategy {
	INSTANCE;

	@Override
	// Method to handle conflict if the mastership state is Aggressive 
	public void handleConflicts(Data model) {
		Master masterShip = CommonTool.getMaster(model);
		List<Ships> enemyShips = model.ships(masterShip.getX(),
				masterShip.getY());
		enemyShips.remove(masterShip);

		Grid grid = model.getGrid();
		List<Ships> ships = grid.getShips();
		   //if statement to check if the mastership is in a Grid containing 1 enemyShip
		if (enemyShips.size() == 1) {
			//destroys the enemy ship if there is 1 enemyship in the same grid as the Mastership
			ships.removeAll(enemyShips);
			//add one and update the number of desroyed enemy ship
			grid.setDestroyedship(grid.destroyedship() + 1);
		} else if (enemyShips.size() > 2) {
			//if statement to check if the mastership is in a Grid containing more than 3 enemyShips
			ships.remove(masterShip);
			//destroys the master ship if there are more than 2 enemy ship in the Grid
			System.out.println("MasterShip Destroyed  in Aggressive Mode ");
		}
	}

}

enum PassiveMode implements Strategy {
	INSTANCE;

	@Override
	// Method to handle conflict if the mastership state is Passive
	public void handleConflicts(Data model) {
		Master masterShip = CommonTool.getMaster(model);
		List<Ships> enemyShips = model.ships(masterShip.getX(),
				masterShip.getY());
		enemyShips.remove(masterShip);

		Grid grid = model.getGrid();
		List<Ships> ships = grid.getShips();
		//if statement to check if the mastership is in a Grid containing 1 enemyShip
		if (enemyShips.size() == 1) {
		//destroys the enemy ship if there is 1 enemyship in the same grid as the Mastership
			ships.removeAll(enemyShips);
		//add one and update the number of desroyed enemy ship
			grid.setDestroyedship(grid.destroyedship() + 1);
		} else if (enemyShips.size() > 1) {
			//if statement to check if the mastership is in a Grid containing more than 1 enemyShips
			ships.remove(masterShip);
			//destroys the master ship if there are more than 2 enemy ship in the Grid
			System.out.println("MasterShip Destroyed in Passive Mode");
			//Prints message to the console
		}
	}

}