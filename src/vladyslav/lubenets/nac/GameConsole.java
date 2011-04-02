package vladyslav.lubenets.nac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameConsole {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Constants
    static final String PATTERN_REG_EXP = "[x|o|X|O][\\s][0-9]{1,}[\\s][0-9]{1,}";
    // Constants for the field
    static final String END_OF_THE_STRING = "";
    static final String SEPARATOR = "|";
    static final String INTERMEDIATE_LINE = "---|---|---";

    public final Pattern pattern = Pattern.compile(PATTERN_REG_EXP);

    public boolean doMatch(String word) {
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    public void drawGameField(Game game) {
        for (int i = 0; i < game.getData().length; i++) {
            for (int j = 0; j < game.getData().length; j++) {
                if (j == 0) {
                    System.out.print(END_OF_THE_STRING);
                }
                if (game.getData()[i][j] == Game.Player.CROSS) {
                    System.out.print(" X ");
                } else if (game.getData()[i][j] == Game.Player.NOUGHT) {
                    System.out.print(" O ");
                } else
                    System.out.print(" - ");
                if (!(j == game.getData().length - 1)) {
                    System.out.print(SEPARATOR);
                }
                if (j == game.getData().length - 1) {
                    System.out.println(END_OF_THE_STRING);
                }
                if (j == game.getData().length - 1 && !(i == game.getData().length - 1)) {
                    System.out.println(INTERMEDIATE_LINE);
                }
            }
        }

    }

    public String inputString() {
        String brReader = "";
        try {
            brReader = br.readLine();
        } catch (IOException ex) {
            return RunGame.QUIT;
        }
        
        return brReader;

    }

}
