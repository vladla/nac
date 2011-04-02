package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.Game.Player;
import vladyslav.lubenets.nac.Game.Result;
import vladyslav.lubenets.nac.ServerSide.State;
import junit.framework.TestCase;

public class RunGameTest extends TestCase {

	Game game;
	ServerSide serverSide; 
    ClientSide clientSide;
	
	@Override
    public void setUp() {
		game = new GameImplement();
		serverSide = new ServerSide();
        clientSide = new ClientSide();
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
        result = game.action(Game.Player.CROSS, -1, 1);
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

	public void testWinAtNotMainDiagonal() {
	    Result result = game.action(Player.CROSS, 1, 1);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.NOUGHT, 1, 2);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.CROSS, 0, 2);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.NOUGHT, 2, 2);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.CROSS, 2, 0);
	    assertEquals(Result.CROSSES_WIN, result);
	}
	
	public void testWinAtVertical() {
	    Result result = game.action(Player.CROSS, 1, 0);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.NOUGHT, 0, 0);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.CROSS, 1, 1);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.NOUGHT, 0, 1);
	    assertEquals(Result.CONTINUE, result);
	    result = game.action(Player.CROSS, 1, 2);
	    assertEquals(Result.CROSSES_WIN, result);
	}
	
    public void testSolianka() {
        Game game1_draw = new GameImplement();
        Game game2_nw = new GameImplement();
        Game game3_incorrect = new GameImplement();

        Result result1 = game1_draw.action(Player.CROSS, 1, 1);
        Result result2 = game2_nw.action(Player.CROSS, 1, 1);
        Result result3 = game3_incorrect.action(Player.CROSS, 1, 1);

        assertEquals(Result.CONTINUE, result1);
        assertEquals(Result.CONTINUE, result2);
        assertEquals(Result.CONTINUE, result3);

        result1 = game1_draw.action(Player.NOUGHT, 0, 0);
        assertEquals(Result.CONTINUE, result1);
        result2 = game2_nw.action(Player.NOUGHT, 2, 2);
        assertEquals(Result.CONTINUE, result2);
        result3 = game3_incorrect.action(Player.CROSS, -1, 10);
        assertEquals(Result.INVALID_PARAMS, result3);

        result1 = game1_draw.action(Player.CROSS, 1, 0);
        assertEquals(Result.CONTINUE, result1);
        result2 = game2_nw.action(Player.CROSS, 2, 1);
        assertEquals(Result.CONTINUE, result2);
        result3 = game3_incorrect.action(Player.CROSS, -1, -1);
        assertEquals(Result.INVALID_PARAMS, result3);

        result1 = game1_draw.action(Player.CROSS, 0, 0);
        assertEquals(Result.FIELD_BUSY, result1);
        result2 = game2_nw.action(Player.CROSS, 1, -1);
        assertEquals(Result.INVALID_PARAMS, result2);
        result3 = game3_incorrect.action(Player.CROSS, 0, 0);
        assertEquals(Result.EXPECTED_NOUGHT, result3);

        result1 = game1_draw.action(Player.NOUGHT, 1, 2);
        assertEquals(Result.CONTINUE, result1);
        result2 = game2_nw.action(Player.NOUGHT, 0, 1);
        assertEquals(Result.CONTINUE, result2);
        result3 = game3_incorrect.action(Player.NOUGHT, 1, 1);
        assertEquals(Result.FIELD_BUSY, result3);

        result1 = game1_draw.action(Player.CROSS, 0, 1);
        assertEquals(Result.CONTINUE, result1);
        result2 = game2_nw.action(Player.CROSS, 0, 0);
        assertEquals(Result.CONTINUE, result2);
        game3_incorrect.restart();
        result3 = game3_incorrect.action(Player.NOUGHT, 0, 0);
        assertEquals(Result.CONTINUE, result3);

        result1 = game1_draw.action(Player.NOUGHT, 2, 1);
        assertEquals(Result.CONTINUE, result1);
        result2 = game2_nw.action(Player.NOUGHT, 0, 2);
        assertEquals(Result.CONTINUE, result2);
        result3 = game3_incorrect.action(Player.NOUGHT, 1, 0);
        assertEquals(Result.EXPECTED_CROSS, result3);

        result1 = game1_draw.action(Player.CROSS, 2, 0);
        assertEquals(Result.CONTINUE, result1);

        result1 = game1_draw.action(Player.NOUGHT, 0, 2);
        assertEquals(Result.CONTINUE, result1);
        result2 = game2_nw.action(Player.CROSS, 1, 0);
        assertEquals(Result.CONTINUE, result2);

        result1 = game1_draw.action(Player.CROSS, 2, 2);
        assertEquals(Result.DRAW, result1);
        result2 = game2_nw.action(Player.NOUGHT, 1, 2);
        assertEquals(Result.NOUGHTS_WIN, result2);
    }
	
	public void testGetDataModification() {
		
		Result result1 = game.action(Player.NOUGHT, 0, 0);
	    assertEquals(Result.CONTINUE, result1);
	    result1 = game.action(Player.CROSS, 1, 1);
	    assertEquals(Result.CONTINUE, result1);
	    result1 = game.action(Player.NOUGHT, 0, 1);
	    assertEquals(Result.CONTINUE, result1);
		Player[][] result = game.getData();
		result[0][0] = null;
		Player[][] result2 = game.getData();
		assertEquals(Player.NOUGHT, result2[0][0]);
	    
	}
    
	public void testRestartAfterQuickGame() {
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
        result = game.action(Game.Player.NOUGHT, 0, 2);
        assertEquals(Game.Result.NEED_RESTART, result);
        
    }
	
	// Server side testing
	
	public void testServerCreateSocket() {
	    Result result = serverSide.create("127.0.0.1", 1234);
	    assertEquals(result, Result.SUCCESS);
	    
	    ServerSide.State state = serverSide.getState();
	    assertEquals(state, State.OPEN);
	    
	    serverSide.close();
	    
	    state = serverSide.getState();
	    assertEquals(state, State.CLOSED);
        	    
	}

    public void testServerTestConnection() {
        Result result = serverSide.test("The string for testing...");
        assertEquals(result, Result.SUCCESS);

        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    public void testServerWritting() {
        Result result = serverSide.write("The string for writting...");
        assertEquals(result, Result.SUCCESS);

        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testServerReading() {
        Result result = serverSide.read();
        assertEquals(result, Result.SUCCESS);
        
        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    //Client side testing

    public void testClientEstablishSocketConnection() {
        Result result = clientSide.connect("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        
        
        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testClientTestConnection() {
        Result result = clientSide.test("The string for testing...");
        assertEquals(result, Result.SUCCESS);
        
        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    public void testClientWritting() {
        Result result = clientSide.write("The string for writting...");
        assertEquals(result, Result.SUCCESS);

        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testClientReading() {
        Result result = clientSide.read();
        assertEquals(result, Result.SUCCESS);

        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    
}
