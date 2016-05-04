package factory;

// (Factory pattern) Responsible for building battle stars which extends from EnemyShips.
 
public enum BattleStarFactory implements Factory {
	INSTANCE;

	@Override
	//method to create a new battlestar ship
	public Ships create() {
		return new BattleStar();
	}
	
	public static class BattleStar extends EnemyShip {

		@Override
		public BattleStar duplicate() {
			BattleStar ship = new BattleStar();
			ship.setX(this.getX());
			ship.setY(this.getY());
			return ship;
		}

	}

	
}
