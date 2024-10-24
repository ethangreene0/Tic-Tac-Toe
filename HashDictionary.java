//Ethan Thomas Davies Greene
//251348539
//egreene4

import java.util.ArrayList;

public class HashDictionary implements DictionaryADT {
    // Hash table storing chains (ArrayLists of Data)
    private final ArrayList[] table;
    // Total number of records in the dictionary
    private int numRecords;

    // Constructor: Initializes the hash table of the specified size
    public HashDictionary(int size) {
        // Create the table with the given size
        table = new ArrayList[size];
        // Initialize each chain as an empty ArrayList
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<>();
        }
        numRecords = 0;
    }

    // Hash function using a polynomial hash technique
    private int hashFunction(String config, int tableSize) {
        int hashValue = 0;
        int a = 10;
        for (int i = 0; i < config.length(); i++) {
            // Compute hash value using polynomial hash technique
            hashValue = (a * hashValue + (int) config.charAt(i)) % tableSize;
        }
        return hashValue;
    }

    // Adds a record to the dictionary
    @Override
    public int put(Data record) throws DictionaryException {
        // Compute the index for the record
        int index = hashFunction(record.getConfiguration(), table.length);
        // Retrieve the chain at the computed index
        ArrayList<Data> chain = table[index];

        // Check if the configuration already exists
        for (Data data : chain) {
            if (data.getConfiguration().equals(record.getConfiguration())) {
                // Throw an exception if duplicate is found
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
        // Compute the index for the configuration
        int index = hashFunction(config, table.length);
        // Retrieve the chain at the computed index
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
        // Compute the index for the configuration
        int index = hashFunction(config, table.length);
        // Retrieve the chain at the computed index
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