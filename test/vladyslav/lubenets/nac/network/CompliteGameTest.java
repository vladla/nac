package vladyslav.lubenets.nac.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;
import vladyslav.lubenets.nac.ConsoleMenu;
import vladyslav.lubenets.nac.ConsoleMenuImpl;
import vladyslav.lubenets.nac.EnterGame;
import vladyslav.lubenets.nac.EnterGameImpl;
import vladyslav.lubenets.nac.FakeServerThreadSimpleGameScenario;
import vladyslav.lubenets.nac.GameConsole;
import vladyslav.lubenets.nac.RunGameWithNet;
import vladyslav.lubenets.nac.ConsoleMenu.GameType;
import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.GameImplement;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;

public class CompliteGameTest extends TestCase {
    private static final Logger LOGGER = Logger.getLogger(FakeServerThread.class.getSimpleName());
    private static final int TIMEOUT_FOR_THE_MAIN_THREAD = 5000;

    SocketConnection socketConnection;
    SocketConnection socketConnectionSlave;
    public static final String HOST = "127.0.0.1";
    public static final String BAD_HOST = "127.1.2.1";
    public static final int PORT = 1234;
    public static final int BAD_PORT = 1111;
    Serializable fName = "Test transaction";

    @Override
    public void setUp() {
        
        socketConnection = new SocketConnectionImpl();
        socketConnectionSlave = new SocketConnectionImpl();
    }

    @Override
    public void tearDown() {
        socketConnection.close();
        socketConnectionSlave.close();
    }

    /*
    public void testSimpleGameOfClient() {
        try {
            GameType gameType = GameType.CLIENT;
            String inputParameters = "";
            String[] inputParameter;
            String host = "";
            Integer port = new Integer(0);
            Player playerType;

            playerType = Player.CROSS;
            port = Integer.valueOf(1234);
            host = "127.0.0.1";

            Game game = new GameImplement();
            GameConsole gameConsole = new GameConsole();
            ConsoleMenu consoleMenu = new ConsoleMenuImpl();
            EnterGame enterGame = new EnterGameImpl();

            new FakeServerThreadSimpleGameOfClient(socketConnectionSlave, port.intValue()).start();
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);

            enterGame.enterGame(game, gameConsole, playerType, port.intValue(), host);
            assertTrue(true);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }
    }
    */
    
    public void testShortGameScenario() {
        try {
            GameType gameType = GameType.CLIENT;
            String inputParameters = "";
            String[] inputParameter;
            String host = "";
            Integer port = new Integer(0);
            Player playerType;

            playerType = Player.CROSS;
            port = Integer.valueOf(1234);
            host = "127.0.0.1";

            Game game = new GameImplement();
            GameConsole gameConsole = new GameConsole();
            ConsoleMenu consoleMenu = new ConsoleMenuImpl();
            EnterGame enterGame = new EnterGameImpl();

            new FakeServerThreadSimpleGameScenario(socketConnectionSlave, port.intValue()).start();
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);

            enterGame.enterGame(game, gameConsole, playerType, port.intValue(), host);
            assertTrue(true);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }
    }

}
