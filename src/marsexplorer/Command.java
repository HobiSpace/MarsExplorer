package marsexplorer;
import java.util.Arrays;

public class Command {
	public static final String AREA = "area", INIT="init", MOVE="move", TURN="turn";
	
	public static final String[] ORIENTATIONS = {"E", "S", "W", "N"};
	public static final String[] MOVES = {"f", "b"};
	public static final String[] TURNS = {"l", "r"};
	
	static String generateAreaCmd(float areaWidth, float areaLength) {
		if (areaWidth < 0 || areaLength < 0) return null;
		if (areaWidth == 0 && areaLength == 0) return null;
		
		StringBuilder sb = new StringBuilder("area ");
		sb.append(areaWidth);
		sb.append(" ");
		sb.append(areaLength);
		sb.append(";");
		
		return sb.toString();
	}
	
	static String generateInitCmd(float x, float y, String orientation) {
		if (!Arrays.asList(ORIENTATIONS).contains(orientation.toUpperCase())) return null;
				
		StringBuilder sb = new StringBuilder("init ");
		sb.append(x);
		sb.append(" ");
		sb.append(y);
		sb.append(" ");
		sb.append(orientation.toUpperCase());
		sb.append(";");
		
		return sb.toString();
	}
	
	static String generateMoveCmd(String move, float steps) {
		if (!Arrays.asList(MOVES).contains(move.toLowerCase())) return null;
		if (steps < 0) return null;
		
		StringBuilder sb = new StringBuilder("move ");
		sb.append(move.toLowerCase());
		sb.append(" ");
		sb.append(steps);
		sb.append(";");
		return sb.toString();
	}
	
	static String generateTurnCmd(String turn) {
		if (!Arrays.asList(TURNS).contains(turn.toLowerCase())) return null;
		
		StringBuilder sb = new StringBuilder("turn ");
		sb.append(turn.toLowerCase());
		sb.append(";");
		return sb.toString();
	}
}
