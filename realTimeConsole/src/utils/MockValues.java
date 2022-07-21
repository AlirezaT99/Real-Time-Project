package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MockValues {
    public static int[] utilizationTest = null;
    public static ArrayList<List<Integer>> priorityTest = null;

    private static void calcUtilizationTest() {
        int[] test = new int[60];
        int[] slice1 = new int[10], slice2 = new int[10], slice3 = new int[10];
        int[] slice4 = new int[10], slice5 = new int[10], slice6 = new int[10];
        Arrays.fill(slice1, 60);
        Arrays.fill(slice2, 75);
        Arrays.fill(slice3, 85);
        Arrays.fill(slice4, 95);
        Arrays.fill(slice5, 75);
        Arrays.fill(slice6, 65);
        System.arraycopy(slice1, 0, test, 0, 10);
        System.arraycopy(slice2, 0, test, 10, 10);
        System.arraycopy(slice3, 0, test, 20, 10);
        System.arraycopy(slice4, 0, test, 30, 10);
        System.arraycopy(slice5, 0, test, 40, 10);
        System.arraycopy(slice6, 0, test, 50, 10);
        utilizationTest = test;
    }

    private static void calcDynamicPriorities() {
        priorityTest = new ArrayList<>();
        List<Integer> order = Arrays.asList(0, 1, 2, 3);
        for (int i = 0; i < 6; i++) {
            Collections.shuffle(order);
            for (int j = 0; j < 10; j++) {
                priorityTest.add(new ArrayList<>(order));
            }
        }
    }

    public static int[] getUtilizationTest() {
        if (utilizationTest == null) {
            calcUtilizationTest();
        }
        return utilizationTest;
    }

    public static ArrayList<List<Integer>> getPriorityTest() {
        if (priorityTest == null) {
            calcDynamicPriorities();
        }
        return priorityTest;
    }
}
