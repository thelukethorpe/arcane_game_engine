package game.component;

import geometry.vertex.RealVertex;
import util.concurrent.Atomic;
import util.time.Tickable;

public abstract class Component implements Tickable {

  private final Atomic<Double> precedence;

  protected Component(double precedence) {
    this.precedence = new Atomic<>(precedence);
  }

  public abstract void translate(RealVertex offset);

  public abstract void scale(double factor);

  public double getPrecedence() {
    return precedence.get();
  }

  public void setPrecedence(double precedence) {
    this.precedence.set(precedence);
  }
}
