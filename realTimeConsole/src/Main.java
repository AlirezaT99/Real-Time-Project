import utils.CPUUtilization;
import utils.ClockPriorities;

import java.time.Instant;
import java.util.List;

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
        String[] zoneIds = {"Asia/Tehran", "Europe/London", "Australia/Sydney", "America/Chicago"};
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        runClocks(zoneIds);
    }

    private static void runClocks(String[] zoneIds) {
        now = Instant.now();
        ClockThread[] threads = initializeThreads(zoneIds);
        Thread[] graphicThreads = initializeGraphicThreads(threads);
        int second, runningThreadsCount;
        final int[] priorities = new int[]{8, 6, 4, 2};
        List<Integer> priorityOrder;
        startThreads(threads, graphicThreads);
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

    private static void startThreads(ClockThread[] threads, Thread[] graphicThreads) {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            graphicThreads[i].start();
        }
    }

    private static ClockThread[] initializeThreads(String[] zoneIds) {
        ClockThread[] threads = new ClockThread[zoneIds.length];
        for (int i = 0; i < zoneIds.length; i++) {
            threads[i] = new ClockThread(zoneIds[i]);
        }
        return threads;
    }

    private static Thread[] initializeGraphicThreads(ClockThread[] threads) {
        Thread[] graphicThreads = new Thread[threads.length];
        for (int i = 0; i < threads.length; i++) {
            graphicThreads[i] = new Thread(new DigitalWatch(threads[i]));
        }
        return graphicThreads;
    }
}