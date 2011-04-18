package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.GameImplement;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.game.Game.Result;

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
        int port = 0;
        Player playerType;

        Game game = new GameImplement();
        GameConsole gameConsole = new GameConsole();
        ConsoleMenu consoleMenu = new ConsoleMenuImpl();
        Player player = Game.Player.CROSS;
        EnterGame enterGame = new EnterGameImpl();
        
        // **Console menu**    static final String HELLO_MESSAGE = "Please, write your char, x and y coordinates as follow x 1 2.";


        // Game type selective

        gameType = consoleMenu.selectGameType();

        // Getting host and port
        inputParameters = consoleMenu.enterParameters(gameType);

        if (!inputParameters.equals("single")) {
            inputParameter = inputParameters.split(" ");

            // Getting host

            host = inputParameter[0];
            if (inputParameter.length > 1) {

                // Getting port

                port = Integer.valueOf(inputParameter[1]);
            }

        }

        // Getting PlayerType

        playerType = consoleMenu.selectPlayerType();

        // Game running
        System.out.println(HELLO_MESSAGE);

        if (gameType.equals(GameType.CLIENT)) {
            enterGame.enterGame(game, gameConsole, playerType, port, host);
        }
        if (gameType.equals(GameType.SERVER)) {
            enterGame.enterGame(game, gameConsole, playerType, port);
        }
        if (gameType.equals(GameType.SINGLE)) {
            enterGame.enterGame(game, gameConsole, playerType);
        }

    }
}
