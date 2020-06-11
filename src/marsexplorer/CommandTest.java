package marsexplorer;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;


public class CommandTest {

	@Test
	public void testGenerateAreaCmd() {
		String areaInfo = Command.generateAreaCmd(15.225f, 20.556f);
		assertEquals("area 15.225 20.556;", areaInfo);
		
		String leftNegative = Command.generateAreaCmd(-15.225f, 20.556f);
		assertNull(leftNegative);
		String rightNegative = Command.generateAreaCmd(15.225f, -20.556f);
		assertNull(rightNegative);
		String bothZero = Command.generateAreaCmd(0, 0);
		assertNull(bothZero);
	}

	@Test
	public void testGenerateInitCmd() {
		String initInfoNorth = Command.generateInitCmd(2.5f, 6.5f, "N");
		assertEquals("init 2.5 6.5 N;", initInfoNorth);
		String initInfoSouth = Command.generateInitCmd(2.5f, 6.5f, "S");
		assertEquals("init 2.5 6.5 S;", initInfoSouth);
		String initInfoEast = Command.generateInitCmd(2.5f, 6.5f, "E");
		assertEquals("init 2.5 6.5 E;", initInfoEast);
		String initInfoWest = Command.generateInitCmd(2.5f, 6.5f, "W");
		assertEquals("init 2.5 6.5 W;", initInfoWest);
		
		String lowerCaseOrientation = Command.generateInitCmd(2.5f, 6.5f, "n");
		assertEquals("init 2.5 6.5 N;", lowerCaseOrientation);
		
		String incorrectOrientation = Command.generateInitCmd(2.5f, 6.5f, "?");
		assertNull(incorrectOrientation);
	}

	@Test
	public void testGenerateMoveCmd() {
		String moveFront = Command.generateMoveCmd("f", 2.55f);
		assertEquals("move f 2.55;", moveFront);
		String moveBack = Command.generateMoveCmd("b", 2.55f);
		assertEquals("move b 2.55;", moveBack);
		
		String upperCaseMove = Command.generateMoveCmd("F", 2.55f);
		assertEquals("move f 2.55;", upperCaseMove);
		
		String incorrectMove = Command.generateMoveCmd("??", 0);
		assertNull(incorrectMove);
		
		String negativeStep = Command.generateMoveCmd("f", -2.55f);
		assertNull(negativeStep);
	}

	@Test
	public void testGenerateTurnCmd() {
		String turnLeft = Command.generateTurnCmd("l");
		assertEquals("turn l;", turnLeft);
		String turnRight = Command.generateTurnCmd("r");
		assertEquals("turn r;", turnRight);
		
		String upperCaseTurn = Command.generateTurnCmd("L");
		assertEquals("turn l;", upperCaseTurn);
		
		String incorrectTurn = Command.generateTurnCmd("??");
		assertNull(incorrectTurn);
	}

}
