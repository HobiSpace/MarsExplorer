package marsexplorer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandExecutorTest {
	CommandExecutor ce;
	
	@Before
	public void setUp() {
		ce = new CommandExecutor();
	}

	@Test
	public void testExecuteCommandArea() {
		ce.executeCommand("area 2.55 5.66");
		String posAfterArea = ce.getPosition();
		assertEquals("Position and Orientation Not yet Initialized!", posAfterArea);
	}

	@Test
	public void testExecuteCommandInit() {
		ce.executeCommand("area 2.55 5.66");
		
		ce.executeCommand("init 1.55 3.66 N");
		String posAfterInit = ce.getPosition();
		assertEquals("Position: (1.55, 3.66), Orientation: N", posAfterInit);
	}
	
	@Test
	public void testExecuteCommandMove() {
		ce.executeCommand("area 2.55 5.66");
		ce.executeCommand("init 1.55 3.66 E");
		
		ce.executeCommand("move f 1.0");
		String moveNotExceedMaxBound = ce.getPosition();
		assertEquals("Position: (2.55, 3.66), Orientation: E", moveNotExceedMaxBound);
		ce.executeCommand("move b 2.55");
		String moveNotExceedMinBound = ce.getPosition();
		assertEquals("Position: (0.00, 3.66), Orientation: E", moveNotExceedMinBound);
		ce.executeCommand("move f 3.0");
		String moveExceedMaxBound = ce.getPosition();
		assertEquals("Position: (2.55, 3.66), Orientation: E", moveExceedMaxBound);
		ce.executeCommand("move b 3.0");
		String moveExceedMinBound = ce.getPosition();
		assertEquals("Position: (0.00, 3.66), Orientation: E", moveExceedMinBound);
		
		ce.executeCommand("init 1.55 3.66 W");
		ce.executeCommand("move f 1.0");
		String moveWest = ce.getPosition();
		assertEquals("Position: (0.55, 3.66), Orientation: W", moveWest);
		
		ce.executeCommand("init 1.55 3.66 N");
		ce.executeCommand("move f 1.0");
		String moveNorth = ce.getPosition();
		assertEquals("Position: (1.55, 4.66), Orientation: N", moveNorth);
		
		ce.executeCommand("init 1.55 3.66 S");
		ce.executeCommand("move f 1.0");
		String moveSouth = ce.getPosition();
		assertEquals("Position: (1.55, 2.66), Orientation: S", moveSouth);
	}
	
	@Test
	public void testExecuteCommandTurn() {
		ce.executeCommand("area 2.55 5.66");
		
		ce.executeCommand("init 0 0 N");
		ce.executeCommand("turn l");
		String NTurnL = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: W", NTurnL);
		ce.executeCommand("turn l");
		String NTurnR = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: E", NTurnR);
		
		ce.executeCommand("init 0 0 S");
		ce.executeCommand("turn l");
		String STurnL = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: E", STurnL);
		ce.executeCommand("turn l");
		String STurnR = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: W", STurnR);
		
		ce.executeCommand("init 0 0 E");
		ce.executeCommand("turn l");
		String ETurnL = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: N", ETurnL);
		ce.executeCommand("turn l");
		String ETurnR = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: S", ETurnR);
		
		ce.executeCommand("init 0 0 W");
		ce.executeCommand("turn l");
		String WTurnL = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: S", WTurnL);
		ce.executeCommand("turn l");
		String WTurnR = ce.getPosition();
		assertEquals("Position: (0, 0), Orientation: N", WTurnR);
	}
	

}
