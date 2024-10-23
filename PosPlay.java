
/* This class represents a possible play and its associated score */
public class PosPlay {
    private final int row;
    private final int col; /* Row and column of the play */
    private final int score;    /* play's score               */

    public PosPlay(int v, int r, int c) {
	row = r;
	col = c;
	score = v;
    }

    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int getScore() {
	return score;
    }
}