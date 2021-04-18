package TestField;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DurationTest {
  public static void main(String[] args) {
    Duration duration = Duration.ZERO;
    System.out.println(duration);

    duration = duration.plus(Duration.ofHours(3));
    duration = duration.plus(Duration.ofMinutes(24));
    duration = duration.plus(Duration.ofSeconds(53));
    duration = duration.plus(Duration.ofMillis(32));
    System.out.println(duration.toMillis());

    LocalTime t = LocalTime.MIDNIGHT.plus(duration);
    System.out.println(DateTimeFormatter.ofPattern("hh:mm:ss").format(t));
  }
}
