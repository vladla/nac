package vladyslav.lubenets.nac;


     enum GameType {
        SERVER, CLIENT
    }

     
     
     
public interface ConsoleMenu {

    
    GameType selectGameType();
    String enterParameters(GameType gameType);
    String selectPlayerType();
    
       
}
