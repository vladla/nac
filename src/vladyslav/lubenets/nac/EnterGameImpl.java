package vladyslav.lubenets.nac;

import java.io.Serializable;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.game.Game.Result;
import vladyslav.lubenets.nac.network.SocketConnection;
import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;

public class EnterGameImpl implements EnterGame {
    
    Object[] dataToObject(Player player, int xPosition, int yPosition) {
        Object[] objects = new Object[3];
        objects[0] = player;
        objects[1] = xPosition;
        objects[2] = yPosition;
        return objects;
    }
    
    Player objectToPlayer(Object[] object) {
        return (Player) object[0];
        
    }

    int objectToXPosition(Object[] object) {
        return (Integer) object[1];
        
    }
    
    int objectToYPosition(Object[] object) {
        return (Integer) object[2];
        
    }

    
    public Object[] gameLogic(String inputParameters, Game game, GameConsole gameConsole, Player playerType, Player player, boolean firstMovePlayer) {
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
            player = Game.Player.CROSS;
        }
        if (inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_SECOND_FIRST_TYPE) || inputParametersArray[beginIndex].equals(RunGameWithNet.PLAYER_SECOND_SECOND_TYPE)) {
            player = Game.Player.NOUGHT;
        }

        if (firstMovePlayer) {
            if (!player.equals(playerType)) {
                firstMovePlayer = false;
                System.out.println(RunGameWithNet.CANT_MOVE);
                return null;
            }
        }

        int xPosition = Integer.parseInt(inputParametersArray[beginIndex + 1]);
        int yPosition = Integer.parseInt(inputParametersArray[beginIndex + 2]);

        Object[] objectReturn = dataToObject(player, xPosition, yPosition);

        return objectReturn;
    }

    
    public void enterGame(Game game, GameConsole gameConsole, Player playerType) {

        boolean firstMovePlayer = true;
        String inputParameters = "";
        Player player = null;

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
                if (!player.equals(playerType)) {
                    firstMovePlayer = false;
                    System.out.println(RunGameWithNet.CANT_MOVE);
                    continue;
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

    public void enterGame(Game game, GameConsole gameConsole, Player playerType, int port) {

        boolean firstMovePlayer = true;
        String inputParameters = "";
        Player player = null;
        final SocketConnection connection = null;

        try {
            connection.accept(port);
            while (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");
                Object[] objectReturn = gameLogic(inputParameters, game, gameConsole, playerType, player, firstMovePlayer);
                connection.write(objectReturn);
                player = objectToPlayer(objectReturn);
                int xPosition = objectToXPosition(objectReturn);
                int yPosition = objectToYPosition(objectReturn);
                Result result = game.action(player, xPosition, yPosition);
                System.out.println(result);
                gameConsole.drawGameField(game);
                Serializable res = connection.read();
                player = objectToPlayer((Object[]) res);
                xPosition = objectToXPosition((Object[]) res);
                yPosition = objectToYPosition((Object[]) res);
                result = game.action(player, xPosition, yPosition);
                System.out.println(result);

            }

        } catch (SocketConnectionException ex) {
            ex.printStackTrace();
        }

        System.out.println(RunGameWithNet.THANK_FOR_GAME);

    }

    public void enterGame(Game game, GameConsole gameConsole, Player playerType, int port, String host) {
        boolean firstMovePlayer = true;
        String inputParameters = "";
        Player player = null;
        final SocketConnection connection = null;

        try {
            connection.connect(host, port);
            while (!(inputParameters.equals(RunGameWithNet.QUIT))) {
                Serializable res = connection.read();
                player = objectToPlayer((Object[]) res);
                int xPosition = objectToXPosition((Object[]) res);
                int yPosition = objectToYPosition((Object[]) res);
                Result result = game.action(player, xPosition, yPosition);
                System.out.println(result);

                inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ");
                Object[] objectReturn = gameLogic(inputParameters, game, gameConsole, playerType, player, firstMovePlayer);
                connection.write(objectReturn);
                player = objectToPlayer(objectReturn);
                xPosition = objectToXPosition(objectReturn);
                yPosition = objectToYPosition(objectReturn);
                result = game.action(player, xPosition, yPosition);
                System.out.println(result);
                gameConsole.drawGameField(game);
                
            }

        } catch (SocketConnectionException ex) {
            ex.printStackTrace();
        }

        System.out.println(RunGameWithNet.THANK_FOR_GAME);

    }
}
