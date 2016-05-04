package factory;


 // (Factory pattern) Responsible for building battle cruisers which extends from enemyship
 
public enum BattleCruiserFactory implements Factory {
	INSTANCE;

	@Override
	public Ships create() {
		//method to create a new battlecruiser ship
		return new BattleCruiser();
	}

	public static class BattleCruiser extends EnemyShip {

		@Override
		public BattleCruiser duplicated() {
			BattleCruiser ship = new BattleCruiser();
			ship.setX(this.getX());
			ship.setY(this.getY());
			return ship;

		}

	}

	
	
	}


