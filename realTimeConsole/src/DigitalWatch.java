import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class DigitalWatch extends Thread {
    JFrame frame;
    JButton timeBtn;
    JLabel timezone;
    ClockThread timerThread;

    public DigitalWatch(ClockThread thread) {
        this.timerThread = thread;
        frame = new JFrame();

        timezone = new JLabel();
        timezone.setText(thread.zoneId);
        timeBtn = new JButton();
        timeBtn.setBounds(100, 100, 100, 50);
        timezone.setBounds(100, 20, 200, 50);

        frame.add(timezone);
        frame.add(timeBtn);
        frame.setSize(300, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void Suspend() {
        frame.setVisible(false);
        super.suspend();
//        frame.dispose();
    }

    public void Resume() {
        frame.setVisible(true);
//        frame.dispose();
        super.resume();
    }


    @Override
    public void run() {
        try {
            while (true) {
                printTime();
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
        }
    }

    public void printTime() {
        timeBtn.setText(timerThread.time);
    }

}

//public class DigitalWatch implements Runnable {
//    JFrame frame;
//    JButton timeBtn;
//    JLabel timezone;
//    ClockThread timerThread;
//
//    DigitalWatch(ClockThread thread) {
//        this.timerThread = thread;
//        frame = new JFrame();
//
//        timezone = new JLabel();
//        timezone.setText(thread.zoneId);
//        timeBtn = new JButton();
//        timeBtn.setBounds(100, 100, 100, 50);
//        timezone.setBounds(100, 20, 200, 50);
//
//        frame.add(timezone);
//        frame.add(timeBtn);
//        frame.setSize(300, 400);
//        frame.setLayout(null);
//        frame.setVisible(true);
//    }
//
//    public void run() {
//        try {
//            while (true) {
//                printTime();
//                TimeUnit.SECONDS.sleep(1);
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    public void printTime() {
//        timeBtn.setText(timerThread.time);
//    }
//
//
//}