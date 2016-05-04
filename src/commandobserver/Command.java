package commandobserver;

import commandobserver.MainFrame.Observer;


public interface Command {
	void executeCommand();
	// command pattern used to move ships
	public static class Move implements Command {
		private final MainFrame frame;

		public Move(MainFrame frame) {
			this.frame = frame;
		}

		@Override
		public void executeCommand() {
			for (Observer observer : frame.getObservers()) {
				observer.onMove();
			}
		}
	}


	//command pattern used to undo ships
	public class Undo implements Command {
		private final MainFrame frame;

		public Undo(MainFrame frame) {
			this.frame = frame;
		}

		@Override
		public void executeCommand() {
			for (Observer observer : frame.getObservers()) {
				observer.onUndo();
			}
		}
	}

}
