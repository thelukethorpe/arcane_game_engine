package game.component;

import game.canvas.Canvas;
import game.component.geometry.ScreenCanvasAdapter;
import game.component.geometry.Vertex;
import game.util.concurrent.Atomic;

public abstract class Component {

  private final Atomic<Double> precedence;

  protected Component(double precedence) {
    this.precedence = new Atomic<>(precedence);
  }

  public abstract void print(Canvas canvas);

  public abstract void translate(Vertex<Double> offset);

  public abstract Component apply(ScreenCanvasAdapter screenCanvasAdapter);

  public double getPrecedence() {
    return precedence.get();
  }

  public void setPrecedence(double precedence) {
    this.precedence.set(precedence);
  }
}
