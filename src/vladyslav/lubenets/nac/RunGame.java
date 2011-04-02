package vladyslav.lubenets.nac;

import java.io.IOException;

import vladyslav.lubenets.nac.Game.Player;
import vladyslav.lubenets.nac.Game.Result;

public class RunGame {
    static final String HELLO_MESSAGE = "Please, write your char, x and y coordinates as follow x 1 2.";
    static final String STRING_IS_NOT_VALID = "String is not a valid format!";
    static final String ERROR_OCCURED = "Error occured!!!";
    static final char PLAYER_FIRST_FIRST_TYPE = 'x';
    static final char PLAYER_FIRST_SECOND_TYPE = 'X';
    static final char PLAYER_SECOND_FIRST_TYPE = 'o';
    static final char PLAYER_SECOND_SECOND_TYPE = 'O';
    static final String THANK_FOR_GAME = "Thank you for the game! Bye-bye!";
    
    public static void main(String[] args) {

        String inputString = "";

        
		Game game = new GameImplement();
		GameConsole gameConsole = new GameConsole();
		Player player = Game.Player.CROSS;
		
		System.out.println(HELLO_MESSAGE);

		try {
			while (!(inputString.equals(GameConsole.QUIT))) {

				inputString = gameConsole.inputString();

				if (!(gameConsole.doMatch(inputString))) {
				    if (!(inputString.equals(GameConsole.QUIT))) {
				        System.out.println(STRING_IS_NOT_VALID);
				    }
					continue;
				}

				if (inputString.charAt(0) == PLAYER_FIRST_FIRST_TYPE
						|| inputString.charAt(0) == PLAYER_FIRST_SECOND_TYPE) {
					player = Game.Player.CROSS;
				}
				if (inputString.charAt(0) == PLAYER_SECOND_FIRST_TYPE
						|| inputString.charAt(0) == PLAYER_SECOND_SECOND_TYPE) {
					player = Game.Player.NOUGHT;
				}

				int x_position = Integer.parseInt(String.valueOf(inputString
						.charAt(2)));
				int y_position = Integer.parseInt(String.valueOf(inputString
						.charAt(4)));

				Result result = game.action(player, x_position, y_position);
				gameConsole.drawGameField(game);
				System.out.println(result);

			} System.out.println(THANK_FOR_GAME);
		} catch (IOException e) {
			System.out.println(ERROR_OCCURED);
			e.printStackTrace();
		}

	}
}
