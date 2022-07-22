import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.*;

public class DigitalWatch implements Runnable {
    JFrame f;
    Thread t = null;
    int hours = 0, minutes = 0, seconds = 0;
    String timeString = "";
    JButton b;
    JLabel l;
    ClockThread relatedThread;

    DigitalWatch(ClockThread thread) {
        this.relatedThread = thread;
        f = new JFrame();

        // Todo : check this!!!!
        t = new Thread(this);
        t.start();

        l = new JLabel();
        l.setText(thread.zoneId);
        b = new JButton();
        b.setBounds(100, 100, 100, 50);
        l.setBounds(100, 20, 200, 50);

        f.add(l);
        f.add(b);
        f.setSize(300, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void run() {
        try {
            while (true) {
                printTime();
                t.sleep(1000);  // interval given in milliseconds
            }
        } catch (Exception e) {
        }
    }

    public void printTime() {
        b.setText(relatedThread.time);
    }

//    public static void main(String[] args) {
//        new DigitalWatch();
//
//
//    }
}