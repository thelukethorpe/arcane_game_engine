package game.io;

public class CursorEvent {
  private final int x;
  private final int y;

  public CursorEvent(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}
