package vladyslav.lubenets.nac.game;

public interface Game {

	public enum Result {
		CONTINUE, NOUGHTS_WIN, CROSSES_WIN, DRAW, INVALID_PARAMS, EXPECTED_CROSS, EXPECTED_NOUGHT, 
		NEED_RESTART, FIELD_BUSY
	}

	public enum Player {
		CROSS, NOUGHT
		}

	public static final int FIELD_SIZE = 3;
	
	void restart();

	Result action(Player player, int X, int Y);
	Player[][] getData (); 
	Object commands(Player player, int X, int Y);
}
