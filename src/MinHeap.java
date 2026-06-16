
/**
 * MinHeap.java
 * A generic Min-Heap backed by an array.
 *
 * insert()  → O(log n)  via heapifyUp
 * poll()    → O(log n)  via heapifyDown
 * peek()    → O(1)
 */
public class MinHeap<T extends Comparable<T>> {
    private Object[] data;
    private int      size;
    private int      capacity;
    private static final int DEFAULT_CAPACITY = 16;

    public MinHeap() { this(DEFAULT_CAPACITY);
    }




    public MinHeap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data     = new Object[capacity];
        this.size     = 0;
    }



    private int parent(int i)     { return (i - 1) / 2; }
    private int leftChild(int i)  { return 2 * i + 1;   }
    private int rightChild(int i) { return 2 * i + 2;   }



    public void insert(T element) {

        if (size == capacity) resize();
        data[size] = element;
        heapifyUp(size);
        size++;

    }


    @SuppressWarnings("unchecked")

    public T poll() {

        if (isEmpty()) throw new IllegalStateException("Heap is empty.");
        T min = (T) data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;

        size--;
        if (size > 0) heapifyDown(0);

        return min;

    }


    @SuppressWarnings("unchecked")

    public T peek() {

        if (isEmpty()) throw new IllegalStateException("Heap is empty.");

        return (T) data[0];

    }


    @SuppressWarnings("unchecked")
    private void heapifyUp(int index) {
        while (index > 0) {
            int p = parent(index);
            T child = (T) data[index];
            T par   = (T) data[p];
            if (child.compareTo(par) < 0) {
                swap(index, p);
                index = p;
            }
            else break;

        }


    }

    @SuppressWarnings("unchecked")
    private void heapifyDown(int index) {
        while (true) {

            int smallest = index;
            int left     = leftChild(index);
            int right    = rightChild(index);

            if (left  < size && ((T) data[left]).compareTo((T) data[smallest])  < 0) smallest = left;

            if (right < size && ((T) data[right]).compareTo((T) data[smallest]) < 0) smallest = right;

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else break;

        }

    }

    private void swap(int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }

    private void resize() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;

    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int     size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] arr) {
        return (T[]) java.util.Arrays.copyOf(data, size, arr.getClass());
    }
}
