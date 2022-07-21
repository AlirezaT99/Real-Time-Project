import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ClockThread extends Thread {

    private final String zoneId;

    public ClockThread(String zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public void run() {
        ZonedDateTime zdt;
        while (true) {
            zdt = ZonedDateTime.ofInstant(Main.now, ZoneId.of(zoneId));
            String formattedTime = zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
            System.out.println(zoneId + "\t\t" + formattedTime);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
