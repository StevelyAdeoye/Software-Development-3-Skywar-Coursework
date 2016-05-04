package factory;

// (Factory pattern) Responsible for building battle shooters which extends from Enemy ship
 
public enum BattleShooterFactory implements Factory {
	INSTANCE;

	@Override
	public Ships create() {
		//method to create a new battleshooter ship
		return new BattleShooter();
	}

	public static class BattleShooter extends EnemyShip {

		@Override
		public BattleShooter duplicate() {
			BattleShooter ship = new BattleShooter();
			ship.setX(this.getX());
			ship.setY(this.getY());
			return ship;
		}

	}

	
}
