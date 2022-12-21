package util.time;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeKeeper {

  private LocalDateTime previousTimestamp = LocalDateTime.now();

  public double TickAndGetDelta() {
    LocalDateTime currentTimestamp = LocalDateTime.now();
    double delta = Duration.between(previousTimestamp, currentTimestamp).toNanos() / 1_000.0;
    previousTimestamp = currentTimestamp;
    return delta;
  }
}
