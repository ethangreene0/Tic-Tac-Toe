public class Data {
    private String config;
    private int score;

    // Constructor: Initializes the configuration and score
    public Data(String config, int score) {
        this.config = config;
        this.score = score;
    }

    // Returns the configuration string
    public String getConfiguration() {
        return config;
    }

    // Returns the score associated with the configuration
    public int getScore() {
        return score;
    }
}
