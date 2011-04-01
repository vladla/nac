package vladyslav.lubenets.nac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import vladyslav.lubenets.nac.Game.Player;
import vladyslav.lubenets.nac.Game.Result;

public class RunGame {
	static String QUIT = "quit";

	public static void drawGameField(Game game) {
		for (int i = 0; i < game.getData().length; i++) {
			for (int j = 0; j < game.getData().length; j++) {
				System.out.print(game.getData()[i][j] + " ");
				if (j == game.getData().length - 1) {
					System.out.println("");
				}
			}
		}
		
	}

	public static String inputString() throws IOException {
		System.out
				.println("Please, write your char, x and y coordinates as follow x 1 2.");
		String inputString, result = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputString = br.readLine();
			if (!(inputString.equals("q"))) {
				result = result + inputString;
			}
			if (inputString==QUIT) {
				return QUIT;
			}
		return result;

	}

	public static void main(String[] args) {


		Game game = new GameImplement();
		String inputString = "";
		Player player = Game.Player.CROSS;
		
		while (!(inputString.equals(QUIT))) {
		drawGameField(game);
		try {
			inputString = inputString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (inputString.charAt(0)=='x' || inputString.charAt(0)=='X'){
			player = Game.Player.CROSS;
		}
		if (inputString.charAt(0)=='o' || inputString.charAt(0)=='O'){
			player = Game.Player.NOUGHT;
		}

		int x_position = Integer.parseInt(String.valueOf(inputString.charAt(2)));
		int y_position = Integer.parseInt(String.valueOf(inputString.charAt(4)));

		Result result = game.action(player, x_position, y_position);
		System.out.println(result);
		}
		
	}
}
