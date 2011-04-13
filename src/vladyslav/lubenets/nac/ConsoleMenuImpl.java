package vladyslav.lubenets.nac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleMenuImpl implements ConsoleMenu {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String brReader = "";
    
    String host = "";
    int port = 0;
    String playerType = "";


    public GameType selectGameType() {
        while (!brReader.equals("1") || !brReader.equals("2")) {
            brReader = "";
            System.out.println("What do you want to do, create a new game(write 1 and press Enter) or connect to the active game(write 2 and press Enter)");
            try {
                brReader = br.readLine();
            } catch (IOException ex) {
                return null;
            }
            if (brReader.equals("1")) {
                return GameType.SERVER;
            }
            if (brReader.equals("2")) {
                return GameType.CLIENT;
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
            } return host + " " + String.valueOf(port);

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
        return null;

    }

    public String selectPlayerType() {
        System.out.println("Please, type who do you want to play in the format X or O");
        while (!Pattern.matches("[X]|[O]", brReader)) {
            brReader = "";
            try {
                brReader = br.readLine();
                playerType = brReader;
                return playerType;
            } catch (IOException ex) {
                return null;
            }
        }
        return null;

    }

}
