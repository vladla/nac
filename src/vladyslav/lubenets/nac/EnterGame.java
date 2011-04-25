package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;

public interface EnterGame {

    void enterGame(Game game, GameConsole gameConsole);
    void enterGame(Game game, GameConsole gameConsole, int port);
    void enterGame(Game game, GameConsole gameConsole, int port, String host);

    
    
}
