import javax.lang.model.util.ElementScanner14;

public class DoubleLinkedList<T> implements DoubleLinkedListADT<T> {
    // Double linked list node class
    public class DoubleLinkedListNode<T> {
        T info;
        DoubleLinkedListNode<T> next;
        DoubleLinkedListNode<T> back;

        public DoubleLinkedListNode() {
            info = null;
            next = null;
            back = null;
        }

        public String toString() {
            return info.toString();
        }
    }

    protected int count; // number of nodes
    protected DoubleLinkedListNode<T> first; // reference to first node
    protected DoubleLinkedListNode<T> last; // reference to last node

    //Default constructor
    public DoubleLinkedList() {
        first = null;
        last = null;
        count = 0;
    }

    public void initializeList() {
        first = null;
        last = null;
        count = 0;
    }

    public boolean isEmptyList() {
        return (first == null);
    }

    public T front() {
        return first.info;
    }

    public T back() {
        return last.info;
    }

    public int length() {
        return count;
    }

    public void print() {
        DoubleLinkedListNode<T> current; //variable to traverse the list
        current = first;
        while (current != null) { //while more data to print
            System.out.print(current.info + " ");
            current = current.next;
        }
    }

    public void reversePrint() {
        DoubleLinkedListNode<T> current; //variable to traverse the list
        current = last;
        System.out.print("[tail] - ");
        while (current != null) { //while more data to print
            System.out.print(current.info + " - ");
            current = current.back;
        }
        System.out.print(" [head]");
    }

    public boolean search(T searchItem) {
        DoubleLinkedListNode<T> current; //variable to traverse the list
        current = first;
        while (current != null)
            if (current.info == searchItem)
                return true;
            else
               current = current.next;
        return false;
    }

    public void insertNode(T insertItem) {
        boolean found;
        DoubleLinkedListNode<T> current;// moving reference
        DoubleLinkedListNode<T> trailCurrent = null; // just before current
        // Set up node to be inserted
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
        newNode.info = insertItem;
        newNode.next = null;
        newNode.back = null;
        // if the list is empty, newNode is the only node
        if (first == null) {
            first = newNode;
            last = newNode;
            count++;
        } else {
            found = false;
            current = first;
            // search the list
            while (current != null && !found) {
                Comparable<T> temp = (Comparable<T>) current.info;
                if (temp.compareTo(insertItem) >= 0)
                    found = true;
                else {
                    trailCurrent = current;
                    current = current.next;
                }
            }
            // insert new node before first
            if (current == first) {
                first.back = newNode;
                newNode.next = first;
                first = newNode;
                count++;
            } else {
                // insert newNode between trailCurrent and current
                if (current != null) {
                    trailCurrent.next = newNode;
                    newNode.back = trailCurrent;
                    newNode.next = current;
                    current.back = newNode;
                } else {
                    // insert new node after last
                    trailCurrent.next = newNode;
                    newNode.back = trailCurrent;
                    last = newNode;
                }
                count++;
            }
        }
    }

    public void deleteNode(T deleteItem) {
        DoubleLinkedListNode<T> current; // moving reference
        DoubleLinkedListNode<T> trailCurrent;// just before current
        boolean found;
        if (first == null)
            System.err.println("Cannot delete from an empty list.");
        // if node to be deleted is the first node
        else if (first.info.equals(deleteItem)) {
            current = first;
            first = first.next;
            if (first != null)
                first.back = null;
            else
                last = null;
            count--;
        } else {
            found = false;
            current = first;
            // search the list
            while (current != null && !found) {
                Comparable<T> temp = (Comparable<T>) current.info;
                if (temp.compareTo(deleteItem) >= 0)
                    found = true;
                else
                    current = current.next;
            }
            if (current == null)
                System.out.println("The item to be deleted is not in the list.");
            else if (current.info.equals(deleteItem)) {
                trailCurrent = current.back;
                trailCurrent.next = current.next;
                if (current.next != null)
                    current.next.back = trailCurrent;
                if (current == last)
                    last = trailCurrent;
                count--;
            } else
                System.out.println("The item to be deleted is not in list.");
        }
    }

    public String toString() {
        String output = "[head] - ";
        DoubleLinkedListNode<T> current = first;
        while(current != null) {
            output += current.info + " - ";
            current = current.next;
        }
        return output + "[tail]";
    }

    public String recursiveToString() {
        return "[head] - " + nodeToString(first) + "[tail]";
    }

    public String nodeToString(DoubleLinkedListNode<T> node) {
        if(node == null) return "";
        else return node.info + " - " + nodeToString(node.next);
    }

    public String backwardsString() {
        String output = "[tail] - ";
        DoubleLinkedListNode<T> current = last;
        while(current != null) {
            output += current.info + " - ";
            current = current.back;
        }
        return output + "[head]";
    }

    public String recursiveBackwardsString() {
        return "[tail] - " + backwardsNodeToString(last) + "[head]";
    }

    public String backwardsNodeToString(DoubleLinkedListNode<T> node) {
        if(node == null) return "";
        else return node.info + " - " + backwardsNodeToString(node.back);
    }

    public boolean equals(Object o) {
        if(o instanceof DoubleLinkedList) {
            DoubleLinkedList otherList = (DoubleLinkedList) o;
            DoubleLinkedListNode<T> current = first;
            DoubleLinkedListNode<T> compare = otherList.first;
            while(current != null && compare != null ) {
                if(current.equals(compare)) {
                    current = current.next;
                    compare = compare.next;
                }
                else
                    return false;
            }
            return current == compare;
        }
        else
            return false;
    }
    
    public void copy(DoubleLinkedList<T> otherList) {
        initializeList();
        System.gc();
        DoubleLinkedListNode<T> current = otherList.first;
        while(current != null) {
            insertNode(current.info);
            current = current.next;
        }
    }

    
    public void reversedCopy(DoubleLinkedList<T> otherList) {
        initializeList(); // Clear destination list contents
        System.gc(); // Clean up unlinked data
        DoubleLinkedListNode<T> trailCurrent = null; // Track previous node for linking
        DoubleLinkedListNode<T> copyItem = otherList.last; // Start at end of copy list
        // Avoiding .insertNode. to circumvent list sorting
        while(copyItem != null) {
            DoubleLinkedListNode<T> current = new DoubleLinkedListNode<T>(); // Create new node
            current.info = copyItem.info; // Copy info into new empty node
            if(trailCurrent != null) { // If destination list already has nodes, link between current and previous
                trailCurrent.next = current;
                current.back = trailCurrent;
            }
            if(first == null) first = current; // If list is empty, set first to new node
            trailCurrent = current; // Move trail up one node
            last = current; // Set end of list to most recent node
            copyItem = copyItem.back; // Prepare next node for copying (backwards traversal)
        }
    }
}