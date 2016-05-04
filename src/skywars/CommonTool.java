package skywars;

import java.util.List;

import factory.Data;
import factory.Factory.Ships; 
import factory.MasterShipFactory.Master;

// A simple tool for some common methods used in this game.
 

public class CommonTool {
	private CommonTool() {

	}

	public static Master getMaster(Data data) {
		List<Ships> ships = data.getGrid().getShips();
		for (Ships ship : ships) {
			if (ship instanceof Master) {
				return (Master) ship;
			}
		}
		return null;
	}
}
