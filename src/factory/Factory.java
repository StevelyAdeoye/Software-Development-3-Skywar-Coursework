package factory;

import skywars.Configs;
import skywars.Move;


 // (Factory pattern) 

 


public interface Factory {
	Ships create();
	

	public interface Ships {
		int getX();

		int getY();

		boolean move(Move deDirection);

		Ships duplicate();
		
	}

	public static abstract class Ship implements Ships {
		private int x;
		private int y;

		@Override
		public final int getX() {
			return x;
		}

		public final void setX(int x) {
			this.x = x;
		}

		@Override
		public final int getY() {
			return y;
		}

		public final void setY(int y) {
			this.y = y;
		}

		@Override
		public final boolean move(Move move) {
			int nextX = x;
			int nextY = y;
			switch (move) {
			case RIGHT:
				nextX++;
				break;
			case LEFT:
				nextX--;
				break;
			case DOWN:
				nextY++;
				break;
			case UP:
				nextY--;
				break;
			}

			if (nextX < 0 || nextX > Configs.X_MAXIMUM || nextY < 0
					|| nextY > Configs.Y_MAXIMUM) {
				return false;
			}

			x = nextX;
			y = nextY;
			return true;
		}

	}
	public static abstract class EnemyShip implements Ships {
		private int x;
		private int y;

		@Override
		public final int getX() {
			return x;
		}

		public final void setX(int x) {
			this.x = x;
		}

		@Override
		public final int getY() {
			return y;
		}

		public final void setY(int y) {
			this.y = y;
		}

		@Override
		public final boolean move(Move move) {
			int nextX = x;
			int nextY = y;
			switch (move) {
			case RIGHT:
				nextX++;
				break;
			case LEFT:
				nextX--;
				break;
			case DOWN:
				nextY++;
				break;
			case UP:
				nextY--;
				break;
			}

			if (nextX < 0 || nextX > Configs.X_MAXIMUM || nextY < 0
					|| nextY > Configs.Y_MAXIMUM) {
				return false;
			}

			x = nextX;
			y = nextY;
			return true;
		}

	}
}
