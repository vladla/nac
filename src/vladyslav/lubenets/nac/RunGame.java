package vladyslav.lubenets.nac;



import vladyslav.lubenets.nac.Game.Player;
import vladyslav.lubenets.nac.Game.Result;

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
    
    public static void main(String[] args) {

        String inputParameters = "";

        
		Game game = new GameImplement();
		GameConsole gameConsole = new GameConsole();
		Player player = Game.Player.CROSS;
		
		System.out.println(HELLO_MESSAGE);


			while (!(inputParameters.equals(QUIT))) {

				inputParameters = gameConsole.inputString().replaceAll("[\\s]{2,}", " ").trim();
				
				
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
				
				if (inputParametersArray[0].equals(PLAYER_FIRST_FIRST_TYPE)
						|| inputParametersArray[0].equals(PLAYER_FIRST_SECOND_TYPE)) {
					player = Game.Player.CROSS;
				}
				if (inputParametersArray[0].equals(PLAYER_SECOND_FIRST_TYPE)
						|| inputParametersArray[0].equals(PLAYER_SECOND_SECOND_TYPE)) {
					player = Game.Player.NOUGHT;
				}
				
				int xPosition = Integer.parseInt(inputParametersArray[1]);
				int yPosition = Integer.parseInt(inputParametersArray[2]);

				Result result = game.action(player, xPosition, yPosition);
				gameConsole.drawGameField(game);
				System.out.println(result);

			} System.out.println(THANK_FOR_GAME);
		} 	
		

	}