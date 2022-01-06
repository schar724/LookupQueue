/**
 * Doubly linked list Node. Holds data (String name), and next and previous nodes.
 *
 * @author Scott Charles
 * Course: COMP 2631
 * Instructor: Jason Heard
 */
public class DLLNode {

    private String name;
    private DLLNode next;
    private DLLNode prev;

    /**
     * Default DLLNode constructor.
     */
    public DLLNode() {
        this.setNext(null);
        this.setPrev(null);
    }

    /**
     * Parameterized DLLNode, accepts a name if the list is empty.
     *
     * @param name name to be added to empty list.
     */
    public DLLNode(String name) {
        this.setName(name);
        this.setNext(null);
        this.setPrev(null);
    }

    /**
     * Parameterized DLLNode constructor.
     *
     * @param n name to be added
     * @param nxt pointer to next node in ALQ
     * @param pre pointer to previous node in ALQ
     */
    public DLLNode(String name, DLLNode nxt, DLLNode pre) {
        this.setName(name);
        this.setNext(nxt);
        this.setPrev(pre);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }

    public DLLNode getPrev() {
        return prev;
    }

    public void setPrev(DLLNode prev) {
        this.prev = prev;
    }
}