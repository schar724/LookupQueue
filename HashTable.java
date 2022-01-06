/**
 * Creates hash values and stores in a hash table. Resizes based on a load factor of 65%.
 *
 * Code for resizeHashTable() found on https://www.algolist.net/Data_structures/Hash_table/Dynamic_resizing
 *
 * @author Scott Charles
 * Course: COMP 2631
 * Instructor: Jason Heard
 */
public class HashTable {

    private BucketOpen[] hashTable;
    private double resizeThreshold = 0.65;
    private int defaultArraySize = 101;
    private int size = 0;
    private double loadFactor;

    /**
     * default HashFunction constructor.
     */
    public HashTable() {
        hashTable = new BucketOpen[defaultArraySize];
        for (int i = 0; i < defaultArraySize; i++) {
            hashTable[i] = null;
        }
    }

    /**
     * hash function for strings provided by Jason Heard.
     *
     * @param name new Node to be added to the hash table.
     * @return hash long pre-compression.
     */
    public long hashFunction(String name) {
        long hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31) + name.charAt(i);
        }
        if (hash < 0) {
            return Math.abs(hash);
        }
        return hash;
    }

    /**
     * find the key or the index to add the key.
     *
     * @param name node to add to the hash table
     * @return index in the hash table or -1 if not found.
     */
    public int search(String name) {
        int hash = (int) hashFunction(name);
        int counter = 0;

        while (counter < this.hashTable.length) {
            int index = Math.abs((hash + counter) % this.hashTable.length);

            BucketOpen temp = hashTable[index];

            if (temp == null) {
                return index;
            } else if (temp != null && temp.getKey().equals(name)) {
                return index;
            }
            counter ++;
        }
        return -1;
    }

    /**
     * gets the next name in the queue based on the hash table index.
     * @param index index of the name being searched
     * @return name of the next name in the queue
     */
    public String getNext(int index) {
        DLLNode temp = hashTable[index].getValue().getNext();
        if (temp == null) {
            return null;
        }

        return hashTable[index].getValue().getNext().getName();
    }

    /**
     * checks to see if the hash table contains a name.
     *
     * @param name inquiry
     * @return condition if the list contains that name
     */
    public boolean contains(String name) {
        int index = search(name);

        if (index == -1) {
            return false;
        } else if (hashTable[index] != null && hashTable[index].getKey().equals(name)
                && !hashTable[index].isDeleted()) {
            return true;
        }
        return false;
    }

    /**
     * returns true if the array list has exceeded the load factor.
     * @return true if higher than 65%
     */
    public boolean loadFactorOutofBounds() {
        this.loadFactor = (size / hashTable.length);

        if (loadFactor >= resizeThreshold) {
            return true;
        }
        return false;
    }

    /**
     * add name to the hash table.
     * @param name new DLLNode to add to the hash table.
     */
    public void put(DLLNode name) {
        int index = search(name.getName());

        while (index == -1 || loadFactorOutofBounds()) {
            resizeHashTable();
            index = search(name.getName());
        }

        if (hashTable[index] == null || hashTable[index].isDeleted()) {
            BucketOpen newBucket = new BucketOpen(name.getName(), name);
            hashTable[index] = newBucket;
            this.size ++;
        }
    }

    /**
     * Transfers values to a temp array. Resizes and refills the hash table.
     *
     * Implementation and partial code found on https://www.algolist.net/Data_structures/Hash_table/Dynamic_resizing
     */
    private void resizeHashTable() {
        int tableSize = hashTable.length * 2 + 1;
        int primeSize = findNextPrime(tableSize);

        BucketOpen[] oldTable = hashTable;

        hashTable = new BucketOpen[primeSize];
        this.size = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && !oldTable[i].isDeleted()) {
                put(oldTable[i].getValue());
            }
        }
    }

    /**
     * finds the next largest prime number.
     *
     * @param numToPrime number less than the next largest prime
     * @return newSize Prime size for table
     */
    private int findNextPrime(int numToPrime) {
        for (int newSize = numToPrime; true; newSize++) {
            if (isPrime(newSize)) {
                return newSize;
            }
        }
    }

    /**
     * checks to see if a number is prime.
     * @param number number to check
     * @return true if prime
     */
    public boolean isPrime(int number) {
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * searches for name and removes it if it exists.
     * @param name name to be removed
     * @return name that was removed or null if doesn't exist
     */
    public void remove(String name) {
        int index = search(name);

        if (hashTable[index] != null
                && hashTable[index].getKey().equals(name)) {
            hashTable[index].setDeleted(true);
        }
    }

    /**
     * Gets the next name in the hash table.
     *
     * @param index index number to get next name
     * @return next name in hash table.
     */
    public String getPrev(int index) {
        DLLNode temp = hashTable[index].getValue().getPrev();
        if (temp == null) {
            return null;
        }
        return hashTable[index].getValue().getPrev().getName();
    }

    public BucketOpen[] getHashTable() {
        return hashTable;
    }
}
