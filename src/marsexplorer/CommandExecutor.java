package marsexplorer;

public class CommandExecutor {	
	private float areaWidth;
	private float areaLength;
	private float posX;
	private float posY;
	private String orientation;
	
	private boolean isInit;
	
	CommandExecutor() {
		isInit = false;
	}
	
	void executeCommand(String command) {
		String[] cmds = command.split(" ");
		if (cmds.length < 1) return;
		
		if (cmds[0].equals(Command.AREA) && cmds.length > 2) {
			processAreaInfo(cmds[1], cmds[2]);
		} else if (cmds[0].equals(Command.INIT) && cmds.length > 3) {
			processInitialInfo(cmds[1], cmds[2], cmds[3]);
		} else if (cmds[0].equals(Command.MOVE) && cmds.length > 2) {
			makeMove(cmds[1], cmds[2]);
		} else if (cmds[0].equals(Command.TURN) && cmds.length > 1) {
			makeTurn(cmds[1]);
		}
		System.out.println(getPosition());
	}
	
	String getPosition() {
		if (isInit) {
			return String.format("Position: (%.2f, %.2f), Orientation: %s", posX, posY, orientation);
		} else {
			return "Position and Orientation Not yet Initialized!";
		}
		
	}
	
	private void processAreaInfo(String areaWidth, String areaLength) {
		try {
			float X = Float.parseFloat(areaWidth);
			float Y = Float.parseFloat(areaLength);
			
			this.areaLength = X;
			this.areaWidth = Y;
			
			isInit = false;
			
		} catch (Exception e) {
			System.err.println("Area initialization fails: " + e);
		}
	}
	
	private void processInitialInfo(String posX, String posY, String orientation) {
		try {
			float x = Float.parseFloat(posX);
			float y = Float.parseFloat(posY);
			
			this.posX = x;
			this.posY = y;
			this.orientation = orientation;
			
			isInit = true;
			
		} catch (Exception e) {
			System.err.println("Position initialization fails: " + e);
		}
	}
	
	private void makeMove(String move, String steps) {
		try {
			float stepValue = Float.parseFloat(steps);
			
			if (orientation.equals(Command.ORIENTATIONS[0])) {
				// east
				if (move.equals(Command.MOVES[0])) {
					// front
					posX = (posX+stepValue < areaLength) ? posX+stepValue : areaLength;
				} else if (move.equals(Command.MOVES[1])) {
					// back
					posX = (posX-stepValue > 0) ? posX-stepValue : 0;
				}
			} else if (orientation.equals(Command.ORIENTATIONS[1])) {
				// south
				if (move.equals(Command.MOVES[0])) {
					// front
					posY = (posY-stepValue > 0) ? posY-stepValue : 0;
				} else if (move.equals(Command.MOVES[1])) {
					// back
					posY = (posY+stepValue < areaWidth) ? posY+stepValue : areaWidth;
				}
			} else if (orientation.equals(Command.ORIENTATIONS[2])) {
				// west
				if (move.equals(Command.MOVES[0])) {
					// front
					posX = (posX-stepValue < areaLength) ? posX-stepValue : 0;
				} else if (move.equals(Command.MOVES[1])) {
					// back
					posX = (posX+stepValue < areaLength) ? posX+stepValue : areaLength;
				}
			} else if (orientation.equals(Command.ORIENTATIONS[3])) {
				// north
				if (move.equals(Command.MOVES[0])) {
					// front
					posY = (posY+stepValue < areaWidth) ? posY+stepValue : areaWidth;
				} else if (move.equals(Command.MOVES[1])) {
					// back
					posY = (posY-stepValue > 0) ? posY-stepValue : 0;
				}
			}
			
		} catch (Exception e) {
			System.err.println("Move fails: " + e);
		}
	}
	
	private void makeTurn(String turn) {
		int param = 0;
		if (turn.equals(Command.TURNS[0])) {
			// left
			param = -1;
		} else if (turn.equals(Command.TURNS[1])) {
			// right
			param = 1;
		}
		
		int idx;
		for (idx = 0; idx < Command.ORIENTATIONS.length; idx++) {
			if (Command.ORIENTATIONS[idx].equals(orientation)) break;
		}
		
		idx = (idx + param) % Command.ORIENTATIONS.length;
		orientation = Command.ORIENTATIONS[idx];
	}
}
