/**
 * PatientHistory.java
 * Singly Linked List storing treated patient records.
 *
 * insertAtHead → O(1)
 * search       → O(n)
 */
public class PatientHistory {

    private static class Node {
        Patient data;
        Node    next;
        Node(Patient data) {
            this.data = data;
            this.next = null;
        }

    }

    private Node head;
    private int  size;

    public PatientHistory() {
        head = null;
        size = 0;

    }

    public void addTreated(Patient p) {
        p.setStatus("TREATED");
        Node newNode = new Node(p);
        newNode.next = head;
        head = newNode;
        size++;

    }

    public Patient searchById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId() == id) return current.data;
            current = current.next;

        }
        return null;

    }

    public Patient[] toArray() {
        Patient[] arr = new Patient[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    public int     size()    {
        return size;


    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void printAll() {
        if (isEmpty())
        {
            System.out.println("  (no treated patients yet)");
            return;

        }

        Node current = head;
        while (current != null) {
            System.out.println("  " + current.data);
            current = current.next;

        }


    }
}
