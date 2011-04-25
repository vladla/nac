package vladyslav.lubenets.nac;

import vladyslav.lubenets.nac.game.Game.Player;



     
     
     
public interface ConsoleMenu {

    public enum GameType {
        SERVER, CLIENT, SINGLE
    }

    
    
    
    GameType selectGameType();
    String enterParameters(GameType gameType);
    Player selectPlayerType();
    
       
}
