package marsexplorer;
public class CommandGenerator {
	private double areaWidth;
	private double areaLength;
	private boolean infoTold;
	private boolean isInit;
	
	CommandGenerator() {
		infoTold = false;
		isInit = false;
	}
		
	String areaInfoCommand(float areaWidth, float areaLength) {
		String cmd = Command.generateAreaCmd(areaWidth, areaLength);
		
		if (cmd != null) {
			this.areaWidth = areaWidth;
			this.areaLength = areaLength;
			infoTold = true;
			isInit = false;
		}
		
		return cmd;
	}
	
	String initInfoCommand(float x, float y, String orientation) {
		if (!infoTold) {
			System.err.println("Need to first specify target area.");
			return null;
		}
		if (isInit) {
			System.err.println("Already initialized!");
			return null;
		}
		if (x > areaWidth || y > areaLength) {
			System.err.println("Initialized position out of target area.");
			return null;
		}
				
		String cmd = Command.generateInitCmd(x, y, orientation);
		
		if (cmd != null) {
			isInit = true;
		}
		
		return cmd;
	}
	
	String moveCommand(String move, float steps) {
		if (!isInit) {
			System.err.println("Need to first initialize position.");
			return null;
		}
		
		return Command.generateMoveCmd(move, steps);
	}
	
	String turnCommand(String turn) {
		if (!isInit) {
			System.err.println("Need to first initialize position.");
			return null;
		}
		
		return Command.generateTurnCmd(turn);
	}
}
