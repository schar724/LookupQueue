import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly linked list implemented by AwesomeLookupQueue.
 *
 * Iterator Code and implementation found on https://codereview.stackexchange.com/questions/141560/an-iterable-implementation-of-linkedlist
 *
 * @author Scott Charles
 * Course: COMP 2631
 * Instructor: Jason Heard
 */
public class List implements Iterable<DLLNode> {
    private DLLNode head;
    private DLLNode tail;
    private int size;

    /**
     * Default List constructor. Creates and empty list.
     */
    public List() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * adds name to end of queue. Used with enqueue method in AwesomeLookupQueue.
     *
     * @param name name to add to the end of DLL.
     * @return tail tail DLLNode
     */
    public DLLNode addToTail(String name) {
        if (tail != null) {
            tail = new DLLNode(name, null, tail);
            tail.getPrev().setNext(tail);
            size++;
        } else {
            head = tail = new DLLNode(name);
            size++;
        }
        return tail;
    }

    /**
     * removes name from front of list. Used in dequeue method in AwesomeLookupQueue.
     *
     * @return head removed DLLNode.
     */
    public String removeFromHead() {
        if (isEmpty()) {
            return null;
        }

        String rmvdName = head.getName();
        if (head == tail) {
            head = null;
            tail = null;
            size--;
        } else {
            head = head.getNext();
            head.setPrev(null);
            size--;
        }
        return rmvdName;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return this.size;
    }

    public DLLNode getHead() {
        return this.head;
    }

    /**
     * Inner Class for Iteration purposes.
     * implementation found on https://codereview.stackexchange.com/questions/141560/an-iterable-implementation-of-linkedlist
     *
     * @author Scott Charles
     *
     */
    public static class ListIterator implements Iterator<DLLNode> {
        private DLLNode currentNode;

        /**
         * List Iterator Constructor.
         * @param lst List
         */
        public ListIterator(List lst) {
            this.currentNode = lst.getHead();
        }

        @Override
        public boolean hasNext() {
            if (currentNode != null) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public DLLNode next() {
            DLLNode temp;
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                temp = currentNode;
                currentNode = currentNode.getNext();
            }
            return temp;
        }
    }

    @Override
    public Iterator<DLLNode> iterator() {
        return new ListIterator(this);
    }
}
