package vladyslav.lubenets.nac.network;

import java.io.Serializable;

import vladyslav.lubenets.nac.game.Game.Player;

public class TransportObject implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Player player;
    Integer xPosition;
    Integer yPosition;
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Integer getxPosition() {
        return xPosition;
    }
    
    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }
    
    public Integer getyPosition() {
        return yPosition;
    }
    
    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

}
