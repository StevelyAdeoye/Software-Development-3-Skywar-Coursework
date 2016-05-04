package factory;

import java.util.Random;

import skywars.Configs;


 // (Factory pattern) Responsible for building master ships.
 
public enum MasterShipFactory implements Factory {
	INSTANCE;

	private static final Random RANDOM = new Random();

	@Override
	public Ships create() {
		Master ship = new Master();
		do {
			ship.setX(RANDOM.nextInt(Configs.X_MAXIMUM));
			ship.setY(RANDOM.nextInt(Configs.Y_MAXIMUM));
		} while (ship.getX() == 0 && ship.getY() == 0);

		return ship;
	}

	public static class Master extends Ship {
		

		@Override
		public Master duplicate() {
			Master ship = new Master();
			//ship.setX(this.getX());
			//ship.setY(this.getY());
			return ship;
		}

	}

	public enum State {
		PASSIVE, AGGRESSIVE;
	}
}
