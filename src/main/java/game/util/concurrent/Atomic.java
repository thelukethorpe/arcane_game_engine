package game.util.concurrent;

public class Atomic<T> {
  private T value;

  public Atomic() {
    this(null);
  }

  public Atomic(T value) {
    this.value = value;
  }

  public synchronized T get() {
    return value;
  }

  public synchronized void set(T value) {
    this.value = value;
  }
}
