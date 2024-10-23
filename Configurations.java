//Ethan Thomas Davies Greene
//251348539
//egreene4

//Defining the public class Configurations
public class Configurations {

    //Initializing all positions on the board to be empty
    private final char[][] board;
    private final int boardSize;
    private final int lengthToWin;

    /**
     * Constructor to initialize the Configurations object with the specified parameters.
     * @param boardSize The size of the board.
     * @param lengthToWin The length of the sequence needed to win the game.
     * @param maxLevels The maximum level of the game tree to be explored.
     */
    public Configurations(int boardSize, int lengthToWin, int maxLevels) {
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.board = new char[boardSize][boardSize];

        //setting all positions on the game board to empty
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    /**
     * Creates and returns an empty HashDictionary.
     * @return An empty HashDictionary.
     */
    public HashDictionary createDictionary() {

        // Chosen prime number between 6000 and 10000
        return new HashDictionary(6007);
    }

    /**
     * Checks if the current board configuration is already present in the hashTable.
     * @param hashTable The HashDictionary to check.
     * @return The score if the configuration is present, otherwise -1.
     */
    public int repeatedConfiguration(HashDictionary hashTable) {
        String config = getConfigString();
        return hashTable.get(config);
    }

    /**
     * Adds the current board configuration and its score to the hashDictionary.
     * @param hashDictionary The HashDictionary to store the configuration.
     * @param score The score associated with the configuration.
     */
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String config = getConfigString();
        try {
            hashDictionary.put(new Data(config, score));
        } catch (DictionaryException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores the specified symbol at the given board position.
     * @param row The row position.
     * @param col The column position.
     * @param symbol The symbol to store (X or O).
     */
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    /**
     * Checks if the specified board position is empty.
     * @param row The row position.
     * @param col The column position.
     * @return True if the position is empty, otherwise false.
     */
    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    /**
     * Checks if the specified symbol has a winning sequence on the board.
     * @param symbol The symbol to check (X or O).
     * @return True if the symbol has a winning sequence, otherwise false.
     */
    public boolean wins(char symbol) {
        // Check rows and columns for a winning sequence
        for (int i = 0; i < boardSize; i++) {
            if (checkLine(symbol, i, 0, 0, 1) || checkLine(symbol, 0, i, 1, 0)) {
                return true;
            }
        }

        // Check diagonal lines for a winning sequence
        return checkLine(symbol, 0, 0, 1, 1) || checkLine(symbol, 0, boardSize - 1, 1, -1);
    }


    /**
     * Checks if the game is a draw.
     * @return True if the game is a draw, otherwise false.
     */
    public boolean isDraw() {

        //Checks if there are any empty positions left on the board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        //If no empty positions and no winner, its a draw
        return !wins('X') && !wins('O');
    }

    /**
     * Evaluates the current state of the board.
     * @return 3 if the computer has won, 0 if the human player has won, 2 if the game is a draw, 1 if the game is still undecided.
     */
    public int evalBoard() {
        if (wins('O')) {
            //Computer wins
            return 3;
        } else if (wins('X')) {
            //Human wins
            return 0;
        } else if (isDraw()) {
            //Draw
            return 2;
        } else {
            //Undecided
            return 1;
        }
    }

    /**
     * Returns a string representation of the current board configuration.
     * @return The string representation of the board.
     */
    private String getConfigString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    /**
     * Checks for a winning line of symbols starting from (startRow, startCol) in the direction specified by (deltaRow, deltaCol).
     * @param symbol The symbol to check (X or O).
     * @param startRow The starting row position.
     * @param startCol The starting column position.
     * @param deltaRow The row increment.
     * @param deltaCol The column increment.
     * @return True if a winning sequence is found, otherwise false.
     */
    private boolean checkLine(char symbol, int startRow, int startCol, int deltaRow, int deltaCol) {
        int count = 0;
        for (int i = 0; i < boardSize; i++) {
            int row = startRow + i * deltaRow;
            int col = startCol + i * deltaCol;
            if (row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] == symbol) {
                count++;
                if (count >= lengthToWin) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}