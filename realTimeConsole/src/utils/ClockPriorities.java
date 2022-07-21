package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClockPriorities {
    public static List<Integer> getPriorityOrder(int second, boolean fixed) {
        ArrayList<List<Integer>> priorityTest = MockValues.getPriorityTest();
        return fixed ?
                Arrays.asList(0, 1, 2, 3)
                : MockValues.getPriorityTest().get(second);
    }
}
