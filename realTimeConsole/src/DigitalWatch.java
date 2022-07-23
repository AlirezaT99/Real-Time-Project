import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class ImagePanel extends JComponent {
    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

public class DigitalWatch extends Thread {
    JFrame frame;
    JButton timeBtn;
    JLabel timezone;
    ClockThread timerThread;

    public DigitalWatch(ClockThread thread) {
        this.timerThread = thread;
        frame = new JFrame();

        timezone = new JLabel(thread.zoneId);

        timezone.setForeground(Color.WHITE);
        timezone.setFont(new Font("TimesRoman", Font.BOLD, 15));
        timeBtn = new JButton();
        timeBtn.setForeground(Color.GREEN);
        timeBtn.setFont(new Font("TimesRoman", Font.BOLD, 16));
        timeBtn.setBackground(Color.DARK_GRAY);

        if (timerThread.zoneId.equals("Asia/Tehran")) {
            timezone.setBounds(80, 20, 200, 50);
        } else {
            timezone.setBounds(60, 20, 200, 50);
        }
        timeBtn.setBounds(60, 80, 130, 50);


        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(timezone);
        frame.add(timeBtn);
        frame.setSize(250, 200);
        frame.setLayout(null);
        frame.setVisible(true);
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
