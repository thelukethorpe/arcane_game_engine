package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import game.util.MathUtil;
import java.util.function.Function;

public class Triangle extends Polygon {
  private final RealVertex a;
  private final RealVertex b;
  private final RealVertex c;

  public Triangle(RealVertex a, RealVertex b, RealVertex c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  private double sign(RealVertex p, RealVertex q, RealVertex r) {
    return (p.getX() - r.getX()) * (q.getY() - r.getY())
        - (q.getX() - r.getX()) * (p.getY() - r.getY());
  }

  @Override
  public boolean contains(IntegerVertex vertex) {
    // Half-plane check.
    RealVertex p = new RealVertex(vertex.getX(), vertex.getY());
    double dot1 = sign(p, a, b);
    double dot2 = sign(p, b, c);
    double dot3 = sign(p, c, a);
    boolean hasNegative = (dot1 < 0.0) || (dot2 < 0.0) || (dot3 < 0.0);
    boolean hasPositive = (dot1 > 0.0) || (dot2 > 0.0) || (dot3 > 0.0);
    return !(hasNegative && hasPositive);
  }

  @Override
  protected Rectangle getRectangleCovering() {
    double xTopLeft = MathUtil.minimum(a.getX(), b.getX(), c.getX());
    double yTopLeft = MathUtil.minimum(a.getY(), b.getY(), c.getY());
    double xBottomRight = MathUtil.maximum(a.getX(), b.getX(), c.getX());
    double yBottomRight = MathUtil.maximum(a.getY(), b.getY(), c.getY());
    return new Rectangle(
        new RealVertex(xTopLeft, yTopLeft), new RealVertex(xBottomRight, yBottomRight));
  }

  @Override
  public Polygon transform(Function<RealVertex, RealVertex> function) {
    return new Triangle(function.apply(a), function.apply(b), function.apply(c));
  }
}
