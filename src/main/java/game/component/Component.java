package game.component;

import game.canvas.Canvas;
import game.component.geometry.VirtualCanvasMapper;
import game.util.concurrent.Atomic;

public abstract class Component {

  private final Atomic<Double> precedence;

  protected Component() {
    this.precedence = new Atomic<>(0.0);
  }

  public abstract void print(Canvas canvas);

  public abstract Component apply(VirtualCanvasMapper virtualCanvasMapper);

  public double getPrecedence() {
    return precedence.get();
  }

  public void setPrecedence(double precedence) {
    this.precedence.set(precedence);
  }
}
