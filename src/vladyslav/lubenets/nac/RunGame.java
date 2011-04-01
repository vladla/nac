package vladyslav.lubenets.nac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import vladyslav.lubenets.nac.Game.Player;
import vladyslav.lubenets.nac.Game.Result;

public class RunGame {
	static String QUIT = "quit";

	public static final Pattern pattern = Pattern
			.compile("[x|o|X|O]+[\\s]+[0-2]+[\\s]+[0-2]");

	public static boolean doMatch(String word) {
		java.util.regex.Matcher matcher = pattern.matcher(word);
		if (matcher.matches())
			return true;
		else
			return false;
	}

	public static void drawGameField(Game game) {
		for (int i = 0; i < game.getData().length; i++) {
			for (int j = 0; j < game.getData().length; j++) {
				if (j == 0) {
					System.out.print("''");
				}
				System.out.print(game.getData()[i][j]);
				if (!(j == game.getData().length - 1)) {
					System.out.print("|");
				}
				if (j == game.getData().length - 1) {
					System.out.println("''");
				}
				if (j == game.getData().length - 1) {
					System.out.println("''----|----|----''");
				}
			}
		}

	}

	public static String inputString() throws IOException {
		String inputString, result = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputString = br.readLine();
		if (!(inputString.equals("q"))) { // delete this!!!
			result = result + inputString;
		}
		if (inputString == QUIT) {
			return QUIT;
		}
		return result;

	}

	public static void main(String[] args) {

		Game game = new GameImplement();
		String inputString = "";
		Player player = Game.Player.CROSS;
		System.out
				.println("Please, write your char, x and y coordinates as follow x 1 2.");
		try {
			while (!(inputString.equals(QUIT))) {

				inputString = inputString();

				if (!(doMatch(inputString))) {
					System.out.println("String is not a valid format!");
					continue;
				}

				if (inputString.charAt(0) == 'x'
						|| inputString.charAt(0) == 'X') {
					player = Game.Player.CROSS;
				}
				if (inputString.charAt(0) == 'o'
						|| inputString.charAt(0) == 'O') {
					player = Game.Player.NOUGHT;
				}

				int x_position = Integer.parseInt(String.valueOf(inputString
						.charAt(2)));
				int y_position = Integer.parseInt(String.valueOf(inputString
						.charAt(4)));

				Result result = game.action(player, x_position, y_position);
				drawGameField(game);
				System.out.println(result);

			}
		} catch (IOException e) {
			System.out.println("Error occured!!!");
			e.printStackTrace();
		}

	}
}
