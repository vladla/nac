package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game.Player;


     enum GameType {
        SERVER, CLIENT, SINGLE
    }

     
     
     
public interface ConsoleMenu {

    
    GameType selectGameType();
    String enterParameters(GameType gameType);
    Player selectPlayerType();
    
       
}
