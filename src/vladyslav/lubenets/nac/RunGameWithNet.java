package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.game.GameImplement;

public class RunGameWithNet {
    static final String HELLO_MESSAGE = "Please, write your char, x and y coordinates as follow x 1 2.";
    static final String STRING_IS_NOT_VALID = "String is not a valid format!";
    static final String ERROR_OCCURED = "Error occured!!!";
    static final String PLAYER_FIRST_FIRST_TYPE = "x";
    static final String PLAYER_FIRST_SECOND_TYPE = "X";
    static final String PLAYER_SECOND_FIRST_TYPE = "o";
    static final String PLAYER_SECOND_SECOND_TYPE = "O";
    static final String THANK_FOR_GAME = "Thank you for the game! Bye-bye!";
    static final String QUIT = "quit";
    static final String RESTART = "restart";
    static final String GAME_RESTARTED = "Game restarted!";
    static final String CANT_MOVE = "You can't move by this player type!";

    public static void main(String[] args) {

        GameType gameType;
        String inputParameters = "";
        String[] inputParameter;
        String host = "";
        Integer port = new Integer(0);
        Player playerType;

        Game game = new GameImplement();
        GameConsole gameConsole = new GameConsole();
        ConsoleMenu consoleMenu = new ConsoleMenuImpl();
        EnterGame enterGame = new EnterGameImpl();

      
        // **Console menu**    static final String HELLO_MESSAGE = "Please, write your char, x and y coordinates as follow x 1 2.";


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

        // Getting PlayerType

        playerType = consoleMenu.selectPlayerType();

        // Game running
        System.out.println(HELLO_MESSAGE);

        
        gameType = GameType.CLIENT;
        playerType = Player.CROSS;
        port = Integer.valueOf(1234);
        host = "127.0.0.1";
        
        

        if (gameType.equals(GameType.CLIENT)) {
            enterGame.enterGame(game, gameConsole, playerType, port.intValue(), host);
        }
        if (gameType.equals(GameType.SERVER)) {
            enterGame.enterGame(game, gameConsole, playerType, port.intValue());
        }
        if (gameType.equals(GameType.SINGLE)) {
            enterGame.enterGame(game, gameConsole, playerType);
        }

    }
}
