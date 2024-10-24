//Ethan Thomas Davies Greene
//251348539
//egreene4

public class Data {

    //Initializing final private variables of type string and int for config and score respectively
    private final String config;
    private final int score;

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
