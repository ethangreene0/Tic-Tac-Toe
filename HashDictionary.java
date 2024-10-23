//Ethan Thomas Davies Greene
//251348539
//egreene4

import java.util.ArrayList;

public class HashDictionary implements DictionaryADT {
    private final ArrayList[] table;  // Hash table storing chains (ArrayLists of Data)
    private int numRecords;           // Total number of records in the dictionary

    // Constructor: Initializes the hash table of the specified size
    public HashDictionary(int size) {
        table = new ArrayList[size];  // Create the table with the given size
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<>();  // Initialize each chain as an empty ArrayList
        }
        numRecords = 0;
    }

    // Hash function using a polynomial hash technique
    private int hashFunction(String config, int tableSize) {
        int hashValue = 0;
        int a = 55;  // Prime number to reduce collisions
        for (int i = 0; i < config.length(); i++) {
            hashValue = (a * hashValue + config.charAt(i)) % tableSize;
        }
        return hashValue;
    }

    // Adds a record to the dictionary
    @Override
    public int put(Data record) throws DictionaryException {
        int index = hashFunction(record.getConfiguration(), table.length);
        ArrayList<Data> chain = table[index];

        // Check if the configuration already exists
        for (Data data : chain) {
            if (data.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException();
            }
        }

        // Add the record to the chain
        chain.add(record);
        numRecords++;

        // Return 1 if there was a collision, 0 otherwise
        return chain.size() > 1 ? 1 : 0;
    }

    // Removes a record from the dictionary
    @Override
    public void remove(String config) throws DictionaryException {
        int index = hashFunction(config, table.length);
        ArrayList<Data> chain = table[index];

        // Find and remove the record
        for (Data data : chain) {
            if (data.getConfiguration().equals(config)) {
                chain.remove(data);
                numRecords--;
                return;
            }
        }

        // If not found, throw an exception
        throw new DictionaryException();
    }

    // Retrieves the score associated with the given configuration
    @Override
    public int get(String config) {
        int index = hashFunction(config, table.length);
        ArrayList<Data> chain = table[index];

        // Find the record and return its score
        for (Data data : chain) {
            if (data.getConfiguration().equals(config)) {
                return data.getScore();
            }
        }

        // Return -1 if not found
        return -1;
    }

    // Returns the number of records in the dictionary
    @Override
    public int numRecords() {
        return numRecords;
    }
}
