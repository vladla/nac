package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.ConsoleMenu.GameType;
import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.GameImplement;
import vladyslav.lubenets.nac.network.SocketConnection;
import vladyslav.lubenets.nac.network.SocketConnectionImpl;

public class RunGameWithNet {
    static final String HELLO_MESSAGE = "Please, write your char, x and y coordinates as follow x 1 2.";
    static final String STRING_IS_NOT_VALID = "String is not a valid format!";
    static final String ERROR_OCCURED = "Error occured!!!";
    static final String PLAYER_FIRST_FIRST_TYPE = "x";
    static final String PLAYER_FIRST_SECOND_TYPE = "X";
    static final String PLAYER_SECOND_FIRST_TYPE = "o";
    static final String PLAYER_SECOND_SECOND_TYPE = "O";
    static final String THANK_FOR_GAME = "Thank you for the game! Bye-bye!";
    public static final String QUIT = "quit";
    static final String RESTART = "restart";
    static final String GAME_RESTARTED = "Game restarted!";
    static final String CANT_MOVE = "You can't move by this player type!";

    // private static final Logger LOGGER =
    // Logger.getLogger(FakeServerThread.class.getSimpleName());


    static SocketConnection socketConnection = new SocketConnectionImpl();
    static SocketConnection socketConnectionSlave = new SocketConnectionImpl();

    public static final String HOST = "127.0.0.1";
    public static final String BAD_HOST = "127.1.2.1";
    public static final int PORT = 1234;
    public static final int BAD_PORT = 1111;
    public static final String CLIENT_HELLO_MESSAGE = "You are playing in client side mode.\nSo, you are playing by nought.\nPlease, enter informaion as follow o 1 2";
    public static final String SERVER_HELLO_MESSAGE = "You are playing in server side mode.\nSo, you are playing by cross.\nPlease, enter informaion as follow x 1 2";
    public static final String REG_EXP_REPLACE = "[\\s]{2,}";
    public static final String FROM_CLIENT = "From client:";
    public static final String FROM_SERVER = "From server:";
//    Serializable fName = "Test transaction";

    public static void main(String[] args) {

        GameType gameType;
        String inputParameters = "";
        String[] inputParameter;
        String host = "";
        Integer port = Integer.valueOf(0);

        Game game = new GameImplement();
        GameConsole gameConsole = new GameConsole();
        ConsoleMenu consoleMenu = new ConsoleMenuImpl();
        EnterGame enterGame = new EnterGameImpl();

        // **Console menu** static final String HELLO_MESSAGE =
        // "Please, write your char, x and y coordinates as follow x 1 2.";

        // Game type selective

        gameType = consoleMenu.selectGameType();

        // Getting host and port
        inputParameters = consoleMenu.enterParameters(gameType);

        if (!gameType.equals(GameType.SINGLE)) {
            inputParameter = inputParameters.split(" ");

            // Getting host

            if (gameType.equals(GameType.SERVER)) {
                port = Integer.valueOf(inputParameter[0]);
            }
            if (gameType.equals(GameType.CLIENT)) {
                host = inputParameter[0];
                port = Integer.valueOf(inputParameter[1]);
            }

        }

        try {

            if (gameType.equals(GameType.CLIENT)) {

                // Server for testing client

                enterGame.enterGame(game, gameConsole, port.intValue(), host);
            }
            if (gameType.equals(GameType.SERVER)) {

                // Server for testing client

                enterGame.enterGame(game, gameConsole, port.intValue());
            }
            if (gameType.equals(GameType.SINGLE)) {
                enterGame.enterGame(game, gameConsole);
            }
        } finally {
            socketConnection.close();
            socketConnectionSlave.close();
        }
    }
}
