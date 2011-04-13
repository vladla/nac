package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.game.Game.Result;
import vladyslav.lubenets.nac.game.GameImplement;

public class RunGame {
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

    public static void singleGame(String inputPlayerType, Game game, GameConsole gameConsole, Player player) {

        boolean firstMovePlayer = false;
        String inputParameters = "";

        while (!(inputParameters.equals(QUIT))) {

            inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");

            if (inputParameters.equals(RESTART)) {
                game.restart();
                System.out.println(GAME_RESTARTED);
                System.out.println(HELLO_MESSAGE);
                continue;
            }

            if (!(gameConsole.doMatch(inputParameters))) {
                if (!(inputParameters.equals(QUIT))) {
                    System.out.println(STRING_IS_NOT_VALID);
                }
                continue;
            }

            String[] inputParametersArray = inputParameters.split(" ");
            int beginIndex = 0;
            if (inputParametersArray[0].equals("")) {
                beginIndex = 1;
            }

            if (inputParametersArray[beginIndex].equals(PLAYER_FIRST_FIRST_TYPE) || inputParametersArray[beginIndex].equals(PLAYER_FIRST_SECOND_TYPE)) {
                player = Game.Player.CROSS;
            }
            if (inputParametersArray[beginIndex].equals(PLAYER_SECOND_FIRST_TYPE) || inputParametersArray[beginIndex].equals(PLAYER_SECOND_SECOND_TYPE)) {
                player = Game.Player.NOUGHT;
            }

            if (!firstMovePlayer) {
                if (!player.equals(inputPlayerType)) {
                    firstMovePlayer = true;
                    System.out.println(CANT_MOVE);
                    continue;
                }
            }

            int xPosition = Integer.parseInt(inputParametersArray[beginIndex + 1]);
            int yPosition = Integer.parseInt(inputParametersArray[beginIndex + 2]);

            Result result = game.action(player, xPosition, yPosition);
            gameConsole.drawGameField(game);
            System.out.println(result);

        }
        System.out.println(THANK_FOR_GAME);
    }

    @SuppressWarnings("boxing")
    public static void main(String[] args) {

        GameType gameType;
        String inputParameters = "";
        String[] inputParameter;
        String host = "";
        int port = 0;
        String playerType = "";

        Game game = new GameImplement();
        GameConsole gameConsole = new GameConsole();
        ConsoleMenu consoleMenu = new ConsoleMenuImpl();
        Player player = Game.Player.CROSS;

        gameType = consoleMenu.selectGameType();
        inputParameters = consoleMenu.enterParameters(gameType);

        inputParameter = inputParameters.split(" ");
        host = inputParameter[0];
        if (inputParameter.length > 1) {
            port = Integer.valueOf(inputParameter[1]);
        }
        playerType = consoleMenu.selectPlayerType();

        System.out.println(HELLO_MESSAGE);
        singleGame(inputParameters, game, gameConsole, player);

    }
}