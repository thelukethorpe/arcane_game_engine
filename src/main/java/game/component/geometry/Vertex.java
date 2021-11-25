package game.component.geometry;

import java.util.Objects;
import java.util.function.Function;

public class Vertex<T> {
  private final T x;
  private final T y;

  public Vertex(T x, T y) {
    this.x = x;
    this.y = y;
  }

  public T getX() {
    return x;
  }

  public T getY() {
    return y;
  }

  public <V> Vertex<V> map(Function<T, V> function) {
    return new Vertex<>(function.apply(x), function.apply(y));
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj instanceof Vertex) {
      Vertex that = (Vertex) obj;
      return this.x.equals(that.x) && this.y.equals(that.y);
    }
    return false;
  }
}
