package marsexplorer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandGeneratorTest {
	CommandGenerator cg;
	
	@Before
	public void setUp() {
		cg = new CommandGenerator();
	}

	@Test
	public void testAreaInfoCommand() {		
		String areaInfo = cg.areaInfoCommand(2.55f, 2.55f);
		assertEquals("area 2.55 2.55;", areaInfo);
	}

	@Test
	public void testInitInfoCommand() {		
		String withoutAreaInfo = cg.initInfoCommand(1.55f, 1.55f, "N");
		assertNull(withoutAreaInfo);
		
		cg.areaInfoCommand(2.55f, 2.55f);
		
		String initInfo = cg.initInfoCommand(1.55f, 1.55f, "N");
		assertEquals("init 1.55 1.55 N;", initInfo);
		
		String duplicatedInit = cg.initInfoCommand(1.55f, 1.55f, "N");
		assertNull(duplicatedInit); 
		
		String outOfArea = cg.initInfoCommand(3f, 1.55f, "N");
		assertNull(outOfArea);
	}

	@Test
	public void testMoveCommand() {
		cg.areaInfoCommand(2.55f, 2.55f);
		
		String withoutInit = cg.moveCommand("f", 2.2f);
		assertNull(withoutInit);
		
		cg.initInfoCommand(1.55f, 1.55f, "N");
		
		String move = cg.moveCommand("f", 1);
		assertEquals("move f 1.0;", move);

	}

	@Test
	public void testTurnCommand() {
		cg.areaInfoCommand(2.55f, 2.55f);
		
		String withoutInit = cg.turnCommand("l");
		assertNull(withoutInit);
		
		cg.initInfoCommand(1.55f, 1.55f, "N");
		
		String turn = cg.turnCommand("l");
		assertEquals("turn l;", turn);

	}

}
