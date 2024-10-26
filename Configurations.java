//Ethan Thomas Davies Greene
//251348539
//egreene4

public class Configurations {

    private final char[][] board;
    private final int boardSize;
    private final int lengthToWin;
    private final int maxLevels;

    // Constructor
    public Configurations(int boardSize, int lengthToWin, int maxLevels) {
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;
        this.board = new char[boardSize][boardSize];

        // Initialize the board with empty spaces
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Creates and returns an empty HashDictionary with a prime size
    public HashDictionary createDictionary() {
        // Using a prime number within the suggested range
        return new HashDictionary(6007);
    }

    // Checks if the current board configuration is in the hashTable
    public int repeatedConfiguration(HashDictionary hashTable) {
        String config = boardToString();
        return hashTable.get(config);  // Returns the score if found, otherwise -1
    }

    // Adds the current board configuration and score to the hashTable
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String config = boardToString();
        hashDictionary.put(new Data(config, score));
    }

    // Stores a symbol ('X' or 'O') in board[row][col]
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Returns true if board[row][col] is empty, otherwise false
    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    // Checks if a player with the specified symbol has won by forming a sequence of length 'lengthToWin'
    public boolean wins(char symbol) {
        // Check rows, columns, and both diagonals for a winning sequence
        return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
    }

    // Checks if the game is a draw: no empty positions and no player has won
    public boolean isDraw() {
        // Check for empty positions
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        // No empty positions; check for no winners
        return !wins('X') && !wins('O');
    }

    // Evaluates the board to determine the current game state
    public int evalBoard() {
        // Computer (O) wins
        if (wins('O')) return 3;

        // Human (X) wins
        if (wins('X')) return 0;

        // Game is a draw
        if (isDraw()) return 2;

        // Game is undecided
        return 1;
    }

    // Converts the current board configuration to a string representation
    private String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    // Helper methods for checking winning conditions
    private boolean checkRows(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            int count = 0;
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == symbol) {
                    count++;
                    if (count >= lengthToWin) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(char symbol) {
        for (int j = 0; j < boardSize; j++) {
            int count = 0;
            for (int i = 0; i < boardSize; i++) {
                if (board[i][j] == symbol) {
                    count++;
                    if (count >= lengthToWin) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals(char symbol) {
        // Check top-left to bottom-right diagonals
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = 0; j <= boardSize - lengthToWin; j++) {
                int count = 0;
                for (int k = 0; k < lengthToWin; k++) {
                    if (board[i + k][j + k] == symbol) {
                        count++;
                        if (count >= lengthToWin) return true;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }

        // Check top-right to bottom-left diagonals
        for (int i = 0; i <= boardSize - lengthToWin; i++) {
            for (int j = lengthToWin - 1; j < boardSize; j++) {
                int count = 0;
                for (int k = 0; k < lengthToWin; k++) {
                    if (board[i + k][j - k] == symbol) {
                        count++;
                        if (count >= lengthToWin) return true;
                    } else {
                        count = 0;
                        break;
                    }
                }
            }
        }

        return false;
    }
}
