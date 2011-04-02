package vladyslav.lubenets.nac;

import java.util.Arrays;

class GameImplement implements Game {
    boolean flagToRestart = false;


	private final Player[][] field = new Player[FIELD_SIZE][FIELD_SIZE];

	private Player previousPlayer = null;

	private Result checkWinner() {
		boolean checkToValues = false;

		// Testing for winners at horizontal -- checked by test

		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
					if(j==FIELD_SIZE-1) {
						break;
					}
					checkToValues = (field[i][j] == field[i][j + 1]);
					if (checkToValues) {
					if (field[i][j] == null) {
						checkToValues = false;
						break;
					}
					if (j + 1 == FIELD_SIZE-1) {
						if (field[i][j + 1] == Player.CROSS) {
						    flagToRestart = true;
							return Result.CROSSES_WIN;
						}
						if (field[i][j + 1] == Player.NOUGHT) {
						    flagToRestart = true;
							return Result.NOUGHTS_WIN;
						}
					}
				} else {
					break;
				}
			}
		}
		
		// Testing for winners at vertical -- unchecked by test
		
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
					if(j==FIELD_SIZE-1) {
						break;
					}
					checkToValues = (field[j][i] == field[j+1][i]);
					if (checkToValues) {
					if (field[i][j] == null) {
						checkToValues = false;
						break;
					}
					if (j + 1 == FIELD_SIZE-1) {
						if (field[j+1][i] == Player.CROSS) {
                            flagToRestart = true;
						    return Result.CROSSES_WIN;
						}
						if (field[j+1][i] == Player.NOUGHT) {
                            flagToRestart = true;
						    return Result.NOUGHTS_WIN;
						}
					}
				} else {
					break;
				}
			}
		}

		// Testing for winners at \ - diagonal -- checked by test
		
		for (int i = 0; i < FIELD_SIZE; i++) {
					checkToValues = (field[i][i] == field[i+1][i+1]);
					if (checkToValues) {
					if (field[i][i] == null) {
						checkToValues = false;
						break;
					}
					if (i + 1 == FIELD_SIZE-1) {
						if (field[i+1][i+1] == Player.CROSS) {
                            flagToRestart = true;
						    return Result.CROSSES_WIN;
						}
						if (field[i+1][i+1] == Player.NOUGHT) {
	                        flagToRestart = true;
						    return Result.NOUGHTS_WIN;
						}
					}
				} else {
					break;
				}
			}
		
		
		// Testing for winners at / - diagonal -- checked by test
		
		for (int i = 0; i < FIELD_SIZE; i++) {
			checkToValues = (field[i][FIELD_SIZE-1-i] == field[i+1][FIELD_SIZE-2-i]);
			if (checkToValues) {
			if (field[i+1][FIELD_SIZE-2-i] == null) {
				checkToValues = false;
				break;
			}
			if (i+1 == FIELD_SIZE-1) {
				if (field[i+1][FIELD_SIZE-2-i] == Player.CROSS) {
                    flagToRestart = true;
				    return Result.CROSSES_WIN;
				}
				if (field[i+1][FIELD_SIZE-2-i] == Player.NOUGHT) {
                    flagToRestart = true;
				    return Result.NOUGHTS_WIN;
				}
			}
		} else {
			break;
		}
	}
		
		boolean flagForLoopExit = false;
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int k = 0; k < FIELD_SIZE; k++) {
				if (field[i][k] == null) {
					flagForLoopExit = true;
					break;
				} else if (i == FIELD_SIZE - 1 && k == FIELD_SIZE - 1) {
                    flagToRestart = true;
				    return Result.DRAW;
				}

			} 
			if (flagForLoopExit) {
				break;
			}
		}
		
		
		return Result.CONTINUE;
	}



	public void restart() {
		for (int i=0; i<FIELD_SIZE; i++) { 
		Arrays.fill(field[i], null);
		}
		previousPlayer = null;
        flagToRestart = false;
		
	}

	public Result action(Player player, int x, int y) {
		
	    if (x<0 || y<0) {
	        return Result.INVALID_PARAMS;
	    }
	    
	    if (flagToRestart) {
	        return Result.NEED_RESTART;
	    }
	    
	    
		if (x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) {
			return Result.INVALID_PARAMS;
		}


		boolean flagToLoopExit = false;
		
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (field[i][j] == null) {
					flagToLoopExit = true;
					break;
				} else if (i == FIELD_SIZE - 1 && j == FIELD_SIZE - 1) {
					return Result.NEED_RESTART;
				}
			}
			if (flagToLoopExit) {
				break;
			}
		}

		if (field[y][x] == Player.CROSS || field[y][x] == Player.NOUGHT) {
			return Result.FIELD_BUSY;
		}
			
		

		if (player == Player.CROSS) {
			if (previousPlayer== Player.CROSS) {
				return Result.EXPECTED_NOUGHT;
			}
			previousPlayer = Player.CROSS;
		}

		if (player == Player.NOUGHT) {
			if (previousPlayer == Player.NOUGHT) {
				return Result.EXPECTED_CROSS;
			}
			previousPlayer = Player.NOUGHT;
		}

		field[y][x] = player;
		
		return checkWinner();

	}

	public Player[][] getData () {
		Player[][] fieldClone = field.clone();
		
		
		for (int i=0; i<FIELD_SIZE;i++) {
			fieldClone[i] = field[i].clone();
		}
		return fieldClone;
	}
	

}