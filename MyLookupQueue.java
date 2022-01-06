import java.util.NoSuchElementException;

/**
 * Lookup Queue.
 *
 * @author Scott Charles
 * Course: COMP 2631
 * Instructor: Jason Heard
 */
public class MyLookupQueue implements LookupQueue {

    public List queue = new List();
    public HashTable ht = new HashTable();

    @Override
    public boolean enqueue(String name) {
        boolean condition;

        if (this.ht.contains(name)) {
            condition = false;
        } else {
            DLLNode newName = this.queue.addToTail(name);
            this.ht.put(newName);
            condition = true;
        }
        return condition;
    }

    @Override
    public String dequeue() throws NoSuchElementException {
        if (this.queue.isEmpty()) {
            throw new NoSuchElementException();
        }

        String frontName = this.queue.removeFromHead();
        this.ht.remove(frontName);
        return frontName;
    }

    @Override
    public boolean contains(String name) {
        return this.ht.contains(name);
    }

    @Override
    public String getNext(String name) throws NoSuchElementException {
        if (!this.ht.contains(name)) {
            throw new NoSuchElementException();
        }

        int index = ht.search(name);
        String nextName = ht.getNext(index);

        return nextName;
    }

    @Override
    public String getPrevious(String name) throws NoSuchElementException {
        if (!this.ht.contains(name)) {
            throw new NoSuchElementException();
        }

        int index = ht.search(name);
        String prevName = ht.getPrev(index);
        return prevName;
    }

    @Override
    public int size() {
        return this.queue.getSize();
    }

    @Override
    public String[] names() {

        String[] names = new String[queue.getSize()];
        int index = 0;
        for (DLLNode name : queue) {
            if (name != null) {
                names[index] = name.getName();
                index++;
            }
        }
        return names;
    }
}
