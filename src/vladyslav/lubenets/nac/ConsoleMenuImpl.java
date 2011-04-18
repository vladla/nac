package vladyslav.lubenets.nac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;

public class ConsoleMenuImpl implements ConsoleMenu {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String brReader = "";
    
    String host = "";
    int port = 0;
    String playerType = "";

    public GameType selectGameType() {
        while (!brReader.equals("server") || !brReader.equals("client") || !brReader.equals("single")) {
            brReader = "";
            System.out.println("What do you want to do, create a new game(write @server@ and press Enter) \nor connect to the active game(write @client@ and press Enter) \nor create a single game(write @single@ and press Enter)");
            try {
                brReader = br.readLine();
            } catch (IOException ex) {
                return null;
            }
            if (brReader.equals("server")) {
                return GameType.SERVER;
            }
            if (brReader.equals("client")) {
                return GameType.CLIENT;
            }
            if (brReader.equals("single")) {
                return GameType.SINGLE;
            }
        }
        return null;
    }

    @SuppressWarnings("boxing")
    public String enterParameters(GameType gameType) {
        if (gameType.equals(GameType.CLIENT)) {
            System.out.println("Please, write a host at the format xxx.xxx.xxx.xxx");
            while (!Pattern.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", brReader)) {
                brReader = "";
                try {
                    brReader = br.readLine();
                    host = brReader;
                } catch (IOException ex) {
                    return null;
                }
            }

            System.out.println("Please, write a port at the format xxxx");
            while (!Pattern.matches("\\d{4}", brReader)) {
                brReader = "";
                try {
                    brReader = br.readLine();
                    port = Integer.valueOf(brReader);
                } catch (IOException ex) {
                    return null;
                }
            }
            return host + " " + String.valueOf(port);

        }

        if (gameType.equals(GameType.SERVER)) {
            while (!Pattern.matches("\\d{4}", brReader)) {
                brReader = "";
                System.out.println("Please, write a port at the format xxxx");
                try {
                    brReader = br.readLine();
                    port = Integer.valueOf(brReader);
                    return String.valueOf(port);
                } catch (IOException ex) {
                    return null;
                }
            }
        }
        
        if (gameType.equals(GameType.SINGLE)) {
            return "single";
        }
        return null;

    }

    public Player selectPlayerType() {
        System.out.println("Please, type who do you want to play in the format X or O");
        while (!Pattern.matches("[X]|[O]", brReader)) {
            brReader = "";
            try {
                brReader = br.readLine();
                playerType = brReader;
                if (playerType.equals(Game.Player.CROSS)) {
                    return Game.Player.CROSS;                    
                }
                if (playerType.equals(Game.Player.NOUGHT)) {
                    return Game.Player.NOUGHT;                    
                }
                
            } catch (IOException ex) {
                return null;
            }
        }
        return null;

    }

}
