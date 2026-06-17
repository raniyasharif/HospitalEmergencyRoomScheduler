
/**
 * Algorithms.java
 * Merge Sort + Binary Search + Linear Search + Undo Stack
 */
public class Algorithms {

    // ── Merge Sort

    public static void mergeSort(Patient[] arr, String sortBy) {
        if (arr == null || arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1, sortBy);

    }

    private static void mergeSortHelper(Patient[] arr, int left, int right, String sortBy) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortHelper(arr, left, mid, sortBy);
        mergeSortHelper(arr, mid + 1, right, sortBy);
        merge(arr, left, mid, right, sortBy);
    }

    private static void merge(Patient[] arr, int left, int mid, int right, String sortBy) {
        int leftLen = mid - left + 1;
        int rightLen = right - mid;

        Patient[] L = new Patient[leftLen];
        Patient[] R = new Patient[rightLen];

        System.arraycopy(arr, left, L, 0, leftLen);
        System.arraycopy(arr, mid + 1, R, 0, rightLen);

        int i = 0, j = 0, k = left;
        while (i < leftLen && j < rightLen) {
            if (compare(L[i], R[j], sortBy) <= 0) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < leftLen) arr[k++] = L[i++];
        while (j < rightLen) arr[k++] = R[j++];
    }

    private static int compare(Patient a, Patient b, String sortBy) {
        return switch (sortBy)
        {
            case "severity" -> {
                int cmp = Integer.compare(a.getSeverity(), b.getSeverity());
                yield cmp != 0 ? cmp : Long.compare(a.getArrivalTime(), b.getArrivalTime());
            }
            case "arrival" -> Long.compare(a.getArrivalTime(), b.getArrivalTime());
            case "name" -> a.getName().compareToIgnoreCase(b.getName());
            default -> Integer.compare(a.getId(), b.getId());
        };
    }


// ── Binary Search

    public static Patient binarySearchById(Patient[] arr, int targetId) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midId = arr[mid].getId();
            if (midId == targetId)
                return arr[mid];

            else if (midId < targetId)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return null;
    }

// ── Linear Search

    public static Patient[] linearSearchByName(Patient[] arr, String query) {
        query = query.toLowerCase();
        int count = 0;
        for (Patient p : arr)
            if (p.getName().toLowerCase().contains(query)) count++;

        Patient[] results = new Patient[count];
        int idx = 0;
        for (Patient p : arr)
            if (p.getName().toLowerCase().contains(query))
                results[idx++] = p;
        return results;
    }

// ── Undo Stack ────────────────────────────────────────────────────────────

    public static class ActionStack {
        private Patient[] stack;
        private int top;
        private static final int MAX = 50;

        public ActionStack() {
            stack = new Patient[MAX];
            top = -1;
        }

        public void push(Patient p) {
            if (top < MAX - 1)
                stack[++top] = p;
        }

        public Patient pop() {
            if (isEmpty())
                throw new IllegalStateException("Nothing to undo.");
            return stack[top--];
        }

        public Patient peek() {
            if (isEmpty())
                throw new IllegalStateException("Stack is empty.");
            return stack[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;

        }

    }
}



