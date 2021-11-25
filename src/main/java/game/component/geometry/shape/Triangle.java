package game.component.geometry.shape;

import game.component.geometry.Vertex;
import game.util.MathUtil;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Triangle implements Polygon {
  private final Vertex<Double> a;
  private final Vertex<Double> b;
  private final Vertex<Double> c;

  public Triangle(Vertex<Double> a, Vertex<Double> b, Vertex<Double> c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  private double sign(Vertex<Double> p, Vertex<Double> q, Vertex<Double> r) {
    return (p.getX() - r.getX()) * (q.getY() - r.getY())
        - (q.getX() - r.getX()) * (p.getY() - r.getY());
  }

  @Override
  public boolean contains(Vertex<Integer> vertex) {
    // Half-plane check.
    Vertex<Double> p = new Vertex<>(vertex.getX().doubleValue(), vertex.getY().doubleValue());
    double dot1 = sign(p, a, b);
    double dot2 = sign(p, b, c);
    double dot3 = sign(p, c, a);
    boolean hasNegative = (dot1 < 0.0) || (dot2 < 0.0) || (dot3 < 0.0);
    boolean hasPositive = (dot1 > 0.0) || (dot2 > 0.0) || (dot3 > 0.0);
    return !(hasNegative && hasPositive);
  }

  @Override
  public List<Vertex<Double>> vertices() {
    return List.of(a, b, c);
  }

  @Override
  public Collection<Vertex<Integer>> getIntegerCovering() {
    int xTopLeft = MathUtil.floor(MathUtil.minimum(a.getX(), b.getX(), c.getX()));
    int yTopLeft = MathUtil.floor(MathUtil.minimum(a.getY(), b.getY(), c.getY()));
    int xBottomRight = MathUtil.ceiling(MathUtil.maximum(a.getX(), b.getX(), c.getX()));
    int yBottomRight = MathUtil.ceiling(MathUtil.maximum(a.getY(), b.getY(), c.getY()));
    Collection<Vertex<Integer>> result = new LinkedList<>();
    for (int x = xTopLeft; x <= xBottomRight; x++) {
      for (int y = yTopLeft; y <= yBottomRight; y++) {
        Vertex<Integer> vertex = new Vertex<>(x, y);
        if (this.contains(vertex)) {
          result.add(vertex);
        }
      }
    }
    return result;
  }

  @Override
  public Polygon transform(Function<Vertex<Double>, Vertex<Double>> function) {
    return new Triangle(function.apply(a), function.apply(b), function.apply(c));
  }
}
