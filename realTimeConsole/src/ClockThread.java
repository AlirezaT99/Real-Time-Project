import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ClockThread extends Thread {

    public boolean suspendflag = false;
    public final String zoneId;
    public String time = "";

    public ClockThread(String zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public void run() {
        ZonedDateTime zdt;
        while (true) {
            zdt = ZonedDateTime.ofInstant(Main.now, ZoneId.of(zoneId));
            String formattedTime = zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
            this.time = formattedTime;
            System.out.println(zoneId + "\t\t" + formattedTime);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void Suspend() {
        suspendflag = true;
        super.suspend();
    }

    public void Resume() {
        suspendflag = false;
        super.resume();
    }
}
