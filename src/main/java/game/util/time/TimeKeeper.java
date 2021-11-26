package game.util.time;

public class TimeKeeper<TValue> {
  private double previousTime;
  private TValue previousValue;

  public TimeKeeper(TValue initialValue) {
    this.previousTime = 0.0;
    this.previousValue = initialValue;
  }

  public double getPreviousTime() {
    return previousTime;
  }

  public TValue getPreviousValue() {
    return previousValue;
  }

  public void update(double time, TValue value) {
    previousTime = time;
    previousValue = value;
  }
}
