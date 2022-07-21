package models;

public enum ExecutionLevels {
    HighestPriorityOnly(1),
    TopPriorities(2),
    AvailableForAll(4);

    private final int value;

    ExecutionLevels(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
