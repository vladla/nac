package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.game.Game.Result;
import vladyslav.lubenets.nac.network.SocketConnection;
import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;
import vladyslav.lubenets.nac.network.SocketConnectionImpl;
import vladyslav.lubenets.nac.network.TransportObject;

public class EnterGameImpl implements EnterGame {

    public TransportObject gameLogic(String inputParameters, Game game, GameConsole gameConsole, Player playerType, Player player, boolean firstMovePlayer) {
        Player playerInside = null;

        if (inputParameters.equals(RunGameWithNet.RESTART)) {
            game.restart();
            System.out.println(RunGameWithNet.GAME_RESTARTED);
            System.out.println(RunGameWithNet.HELLO_MESSAGE);
            return null;
        }

        if (!(gameConsole.doMatch(inputParameters))) {
            if (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                System.out.println(RunGameWithNet.STRING_IS_NOT_VALID);
            }
            return null;
        }

        String[] inputParametersArray = inputParameters.split(" ");
        int beginIndex = 0;
        if (inputParametersArray[0].equals("")) {
            beginIndex = 1;
        }

        if (inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_FIRST_FIRST_TYPE) || inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_FIRST_SECOND_TYPE)) {
            playerInside = Game.Player.CROSS;
        }
        if (inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_SECOND_FIRST_TYPE) || inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_SECOND_SECOND_TYPE)) {
            playerInside = Game.Player.NOUGHT;
        }

        if (firstMovePlayer) {
            if (!player.equals(playerType)) {
                // firstMovePlayer = false;
                System.out.println(RunGameWithNet.CANT_MOVE);
                return null;
            }
        }

        int xPosition = Integer.parseInt(inputParametersArray[beginIndex + 1]);
        int yPosition = Integer.parseInt(inputParametersArray[beginIndex + 2]);

        TransportObject transportObject = new TransportObject();
        transportObject.setPlayer(playerInside);
        transportObject.setxPosition(Integer.valueOf(xPosition));
        transportObject.setyPosition(Integer.valueOf(yPosition));

        return transportObject;
    }

    public void enterGame(Game game, GameConsole gameConsole) {

        boolean firstMovePlayer = true;
        String inputParameters = "";
        Player player = null;
        ConsoleMenu consoleMenu = new ConsoleMenuImpl();

        // Getting PlayerType
        Player playerType = consoleMenu.selectPlayerType();

        // Game running
        System.out.println(RunGameWithNet.HELLO_MESSAGE);

        while (!(inputParameters.equals(RunGameWithNet.QUIT))) {

            inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");

            if (inputParameters.equals(RunGameWithNet.RESTART)) {
                game.restart();
                System.out.println(RunGameWithNet.GAME_RESTARTED);
                System.out.println(RunGameWithNet.HELLO_MESSAGE);
                continue;
            }

            if (!(gameConsole.doMatch(inputParameters))) {
                if (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                    System.out.println(RunGameWithNet.STRING_IS_NOT_VALID);
                }
                continue;
            }

            String[] inputParametersArray = inputParameters.split(" ");
            int beginIndex = 0;
            if (inputParametersArray[0].equals("")) {
                beginIndex = 1;
            }

            if (inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_FIRST_FIRST_TYPE) || inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_FIRST_SECOND_TYPE)) {
                player = Game.Player.CROSS;
            }
            if (inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_SECOND_FIRST_TYPE) || inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_SECOND_SECOND_TYPE)) {
                player = Game.Player.NOUGHT;
            }

            if (firstMovePlayer) {
                if (player != null) {
                    if (!player.equals(playerType)) {
                        firstMovePlayer = false;
                        System.out.println(RunGameWithNet.CANT_MOVE);
                        continue;
                    }
                }
            }

            int xPosition = Integer.parseInt(inputParametersArray[beginIndex + 1]);
            int yPosition = Integer.parseInt(inputParametersArray[beginIndex + 2]);

            Result result = game.action(player, xPosition, yPosition);
            gameConsole.drawGameField(game);
            System.out.println(result);

        }
        System.out.println(RunGameWithNet.THANK_FOR_GAME);
    }

    public void enterGame(Game game, GameConsole gameConsole, int port) {

        boolean firstMovePlayer = true;
        String inputParameters = "";
        Player player = null;
        SocketConnection connection = null;
        ConsoleMenu consoleMenu = new ConsoleMenuImpl();

        // Getting PlayerType
        Player playerType = consoleMenu.selectPlayerType();

        // Game running
        System.out.println(RunGameWithNet.HELLO_MESSAGE);

        try {
            connection.accept(port);
            while (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");
                TransportObject objectReturn = gameLogic(inputParameters, game, gameConsole, playerType, player, firstMovePlayer);
                connection.write(objectReturn);
                player = objectReturn.getPlayer();
                int xPosition = objectReturn.getxPosition().intValue();
                int yPosition = objectReturn.getyPosition().intValue();
                Result result = game.action(player, xPosition, yPosition);
                System.out.println(result);
                gameConsole.drawGameField(game);
                TransportObject res = (TransportObject) connection.read();
                player = res.getPlayer();
                xPosition = res.getxPosition().intValue();
                yPosition = res.getyPosition().intValue();
                result = game.action(player, xPosition, yPosition);
                System.out.println(result);

            }

        } catch (SocketConnectionException ex) {
            ex.printStackTrace();
        }

        System.out.println(RunGameWithNet.THANK_FOR_GAME);

    }

    public void enterGame(Game game, GameConsole gameConsole, int port, String host) {
        boolean firstMovePlayer = false;
        String inputParameters = "";
        Player player = null;
        SocketConnection socketConnection = new SocketConnectionImpl();
        Result result = null;

        // Getting PlayerType
        Player playerType = Game.Player.NOUGHT;

        try {

            socketConnection.connect(host, port);
            while (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                if (result != null) {
                    if (result.equals(Game.Result.NEED_RESTART) || result.equals(Game.Result.CROSSES_WIN) || result.equals(Game.Result.NOUGHTS_WIN)) {
                        if (!(gameConsole.doMatch(inputParameters))) {
                            if (!inputParameters.equals(RunGameWithNet.QUIT) && !inputParameters.equals(RunGameWithNet.RESTART)) {
                                System.out.println(RunGameWithNet.STRING_IS_NOT_VALID);
                            }
                        }
                        if (!inputParameters.equals(RunGameWithNet.RESTART)) {
                            System.out.println("Restart needed");
                        }
                        if (inputParameters.equals(RunGameWithNet.RESTART)) {
                            result = null;
                            game.restart();
                            System.out.println(RunGameWithNet.GAME_RESTARTED);
                            System.out.println(RunGameWithNet.CLIENT_HELLO_MESSAGE);
                        }
                        inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");
                        continue;
                    }
                }


                TransportObject res = (TransportObject) socketConnection.read();
                player = res.getPlayer();
                int xPosition = res.getxPosition().intValue();
                int yPosition = res.getyPosition().intValue();
                result = game.action(player, xPosition, yPosition);
                gameConsole.drawGameField(game);
                System.out.println("From server " + result);
                if (result.equals(Game.Result.CONTINUE)) {
                    System.out.println(RunGameWithNet.CLIENT_HELLO_MESSAGE);
                }

                if (result != null) {
                    if (result.equals(Game.Result.NEED_RESTART) || result.equals(Game.Result.CROSSES_WIN) || result.equals(Game.Result.NOUGHTS_WIN) || result.equals(Game.Result.NOUGHTS_WIN)) {
                        if (!(gameConsole.doMatch(inputParameters))) {
                            if (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                                System.out.println(RunGameWithNet.STRING_IS_NOT_VALID);
                            }
                            continue;
                        }

                        if (inputParameters.equals(RunGameWithNet.RESTART)) {
                            result = null;
                            game.restart();
                            System.out.println(RunGameWithNet.GAME_RESTARTED);
                            System.out.println(RunGameWithNet.CLIENT_HELLO_MESSAGE);
                            continue;
                        }
                        inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");
                        continue;
                    }
                }
                inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");
                TransportObject objectReturn = gameLogic(inputParameters, game, gameConsole, playerType, player, firstMovePlayer);
                socketConnection.write(objectReturn);
                player = objectReturn.getPlayer();
                xPosition = objectReturn.getxPosition().intValue();
                yPosition = objectReturn.getyPosition().intValue();
                result = game.action(player, xPosition, yPosition);
                gameConsole.drawGameField(game);
                System.out.println("From client:" + result);

            }
            return;
        } catch (SocketConnectionException ex) {
            ex.printStackTrace();
        } finally {
            socketConnection.close();
            System.out.println(RunGameWithNet.THANK_FOR_GAME);
        }

    }
}
