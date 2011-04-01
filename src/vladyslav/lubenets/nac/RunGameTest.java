package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.Game.Result;
import junit.framework.TestCase;

public class RunGameTest extends TestCase {
	
	Game game;
	
	@Override
    public void setUp() {
		game = new GameImplement();
	}

	public void testCrossesWin() {
		Result result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 0, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 2, 2);
		assertEquals(Game.Result.CROSSES_WIN, result);
	}

	public void testDraw() {
		Result result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 1, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 1, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 0, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 2, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 2, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 2, 2);
		assertEquals(Game.Result.DRAW, result);
	}

	public void testNoughtWin() {
		Result result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 2, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 2, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 0, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 1, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 1, 2);
		assertEquals(Game.Result.NOUGHTS_WIN, result);
	}

	public void testTwoCrossesInSuccession() {
		Result result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 0, 0);
		assertEquals(Game.Result.EXPECTED_NOUGHT, result);
	}

	public void testTwoNoughtsInSuccession() {
		Result result = game.action(Game.Player.NOUGHT, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 0);
		assertEquals(Game.Result.EXPECTED_CROSS, result);
	}

	public void testErrorBadInteger() {
		Result result = game.action(Game.Player.CROSS, 5, 1);
		assertEquals(Game.Result.INVALID_PARAMS, result);
	}

//	public void testErrorBadXOSymbol() {
//		Result result = game.action(Game.Result.CONTINUE, 2, 1);
//		assertEquals(Game.Result.INVALID_PARAMS, result);
//	}

	public void testNextMove() {
		Result result = game.action(Game.Player.CROSS, 1, 2);
		assertEquals(Game.Result.CONTINUE, result);
	}

	public void testNewGame() {
		Result result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 2, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 1, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 1, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 0, 2);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 2, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 0, 0);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 0, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.CROSS, 2, 1);
		assertEquals(Game.Result.DRAW, result);
		result = game.action(Game.Player.NOUGHT, 0, 1);
		assertEquals(Game.Result.NEED_RESTART, result);
		game.restart();
		
		result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
	
	}

	public void testFieldIsBusy() {
		Result result = game.action(Game.Player.CROSS, 1, 1);
		assertEquals(Game.Result.CONTINUE, result);
		result = game.action(Game.Player.NOUGHT, 1, 1);
		assertEquals(Game.Result.FIELD_BUSY, result);
	}
}