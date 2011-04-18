package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;

public interface EnterGame {

    void enterGame(Game game, GameConsole gameConsole, Player playerType);
    void enterGame(Game game, GameConsole gameConsole, Player playerType, int port);
    void enterGame(Game game, GameConsole gameConsole, Player playerType, int port, String host);

    
    
}
