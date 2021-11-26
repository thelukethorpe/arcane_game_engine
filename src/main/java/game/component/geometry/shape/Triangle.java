package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import game.util.MathUtil;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Triangle implements Polygon {
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
  public List<RealVertex> vertices() {
    return List.of(a, b, c);
  }

  @Override
  public Collection<IntegerVertex> getIntegerCovering() {
    int xTopLeft = MathUtil.floor(MathUtil.minimum(a.getX(), b.getX(), c.getX()));
    int yTopLeft = MathUtil.floor(MathUtil.minimum(a.getY(), b.getY(), c.getY()));
    int xBottomRight = MathUtil.ceiling(MathUtil.maximum(a.getX(), b.getX(), c.getX()));
    int yBottomRight = MathUtil.ceiling(MathUtil.maximum(a.getY(), b.getY(), c.getY()));
    Collection<IntegerVertex> result = new LinkedList<>();
    for (int x = xTopLeft; x <= xBottomRight; x++) {
      for (int y = yTopLeft; y <= yBottomRight; y++) {
        IntegerVertex vertex = new IntegerVertex(x, y);
        if (this.contains(vertex)) {
          result.add(vertex);
        }
      }
    }
    return result;
  }

  @Override
  public Polygon transform(Function<RealVertex, RealVertex> function) {
    return new Triangle(function.apply(a), function.apply(b), function.apply(c));
  }
}
