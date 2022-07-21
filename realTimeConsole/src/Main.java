import utils.CPUUtilization;
import utils.ClockPriorities;
import utils.MockValues;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Main {
    public static final boolean DEBUG = true;
    public static final boolean FIXED_PRIORITY = true;
    public static Instant now;

    /**
     * defines an array of zone ids and sets the main thread priority to
     * max to make sure it will remain active and the starts the clocks
     */
    public static void main(String[] args) {
        ClockPriorities.getPriorityOrder(0, true);
        String[] zoneIds = {"Asia/Tehran", "Europe/London", "Australia/Sydney", "America/Chicago"};
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        runClocks(zoneIds);
    }

    private static void runClocks(String[] zoneIds) {
        now = Instant.now();
        Thread[] threads = initializeThreads(zoneIds);
        int second, runningThreadsCount;
        final int[] priorities = new int[]{8, 6, 4, 2};
        List<Integer> priorityOrder;
        startThreads(threads);
        while (true) {
            now = Instant.now();
            second = (int) now.getEpochSecond() % 60;
            runningThreadsCount = CPUUtilization.getExecutionPlan(second, DEBUG).getValue();
            priorityOrder = ClockPriorities.getPriorityOrder(second, FIXED_PRIORITY);
            for (int i = 0; i < 4; i++) {
                threads[priorityOrder.get(i)].setPriority(priorities[i]);
            }
            if (DEBUG) {
                for (int i = 0; i < 4; i++) {
                    if (priorityOrder.get(i) <= runningThreadsCount) {
                        threads[i].resume();
                    } else {
                        threads[i].suspend();
                    }
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private static void startThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static Thread[] initializeThreads(String[] zoneIds) {
        Thread[] threads = new ClockThread[zoneIds.length];
        for (int i = 0; i < zoneIds.length; i++) {
            threads[i] = new ClockThread(zoneIds[i]);
        }
        return threads;
    }
}