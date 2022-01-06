/**
 * Information for one bucket in a hash table using linear probing.
 *
 * @author Scott Charles
 * Course: COMP 2531
 * Instructor: Jason Heard
 */
public class BucketOpen {

    private String key;
    private DLLNode value;
    private boolean deleted;

    /**
     * parameterized constructor for hash table bucket.
     * @param key DLLNode for the bucket to point to.
     * @param value hash value index to store in array.
     */
    public BucketOpen(String key, DLLNode value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public String getKey() {
        return this.key;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setKey(DLLNode key) {
        this.key = key.getName();
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DLLNode getValue() {
        return value;
    }

    public void setValue(DLLNode name) {
        this.value = name;
    }
}
