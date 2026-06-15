
/**
 * Patient.java
 * Represents a single patient in the Emergency Room.
 *
 * Implements Comparable so Java's PriorityQueue can order patients
 * automatically by severity (lower number = higher priority).
 */
public class Patient implements Comparable<Patient> {

    private static int idCounter = 1;

    private final int    id;
    private final String name;
    private final int    age;
    private final int    severity;
    private final long   arrivalTime;
    private       String status;

    public Patient(String name, int age, int severity) {
        if (severity < 1 || severity > 5)
            throw new IllegalArgumentException("Severity must be between 1 (critical) and 5 (walk-in).");

        this.id          = idCounter++;
        this.name        = name;
        this.age         = age;
        this.severity    = severity;
        this.arrivalTime = System.currentTimeMillis();
        this.status      = "WAITING";
    }

    // Constructor for loading from file
    public Patient(int id, String name, int age, int severity, long arrivalTime, String status) {
        this.id          = id;
        this.name        = name;
        this.age         = age;
        this.severity    = severity;
        this.arrivalTime = arrivalTime;
        this.status      = status;
        if (id >= idCounter) idCounter = id + 1;
    }

    @Override
    public int compareTo(Patient other) {
        if (this.severity != other.severity)
            return Integer.compare(this.severity, other.severity);
        return Long.compare(this.arrivalTime, other.arrivalTime);
    }

    public int    getId()          { return id; }
    public String getName()        { return name; }
    public int    getAge()         { return age; }
    public int    getSeverity()    { return severity; }
    public long   getArrivalTime() { return arrivalTime; }
    public String getStatus()      { return status; }
    public void   setStatus(String status) { this.status = status; }

    public String getSeverityLabel() {
        return switch (severity) {
            case 1 -> "Critical";
            case 2 -> "Serious";
            case 3 -> "Moderate";
            case 4 -> "Minor";
            case 5 -> "Walk-in";
            default -> "Unknown";
        };
    }

    public String toCSV() {
        return id + "," + name + "," + age + "," + severity + "," + arrivalTime + "," + status;
    }

    public static Patient fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        int    id          = Integer.parseInt(parts[0].trim());
        String name        = parts[1].trim();
        int    age         = Integer.parseInt(parts[2].trim());
        int    severity    = Integer.parseInt(parts[3].trim());
        long   arrivalTime = Long.parseLong(parts[4].trim());
        String status      = parts[5].trim();
        return new Patient(id, name, age, severity, arrivalTime, status);
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %-20s | Age: %2d | Severity: %d (%s) | Status: %s",
                id, name, age, severity, getSeverityLabel(), status);
    }
}