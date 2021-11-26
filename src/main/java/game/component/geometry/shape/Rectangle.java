package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import game.util.MathUtil;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Rectangle implements Polygon {

  private final RealVertex topLeftVertex;
  private final double width;
  private final double height;

  public Rectangle(RealVertex topLeftVertex, double width, double height) {
    this.topLeftVertex = topLeftVertex;
    this.width = width;
    this.height = height;
  }

  private Rectangle(RealVertex topLeftVertex, RealVertex bottomRightVertex) {
    this(
        topLeftVertex,
        bottomRightVertex.getX() - topLeftVertex.getX(),
        bottomRightVertex.getY() - topLeftVertex.getY());
  }

  private RealVertex getBottomRightVertex() {
    return new RealVertex(topLeftVertex.getX() + width, topLeftVertex.getY() + height);
  }

  @Override
  public boolean contains(IntegerVertex vertex) {
    return topLeftVertex.getX() <= vertex.getX()
        && vertex.getX() <= topLeftVertex.getX() + width
        && topLeftVertex.getY() <= vertex.getY()
        && vertex.getY() <= topLeftVertex.getY() + height;
  }

  @Override
  public List<RealVertex> vertices() {
    double xTopLeft = topLeftVertex.getX();
    double yTopLeft = topLeftVertex.getY();
    return List.of(
        new RealVertex(xTopLeft, yTopLeft),
        new RealVertex(xTopLeft + width, yTopLeft),
        new RealVertex(xTopLeft + width, yTopLeft + height),
        new RealVertex(xTopLeft, yTopLeft + height));
  }

  @Override
  public Collection<IntegerVertex> getIntegerCovering() {
    int xTopLeft = MathUtil.ceiling(topLeftVertex.getX());
    int yTopLeft = MathUtil.ceiling(topLeftVertex.getY());
    int xBottomRight = MathUtil.floor(topLeftVertex.getX() + width);
    int yBottomRight = MathUtil.floor(topLeftVertex.getY() + height);
    Collection<IntegerVertex> result = new LinkedList<>();
    for (int x = xTopLeft; x <= xBottomRight; x++) {
      for (int y = yTopLeft; y <= yBottomRight; y++) {
        result.add(new IntegerVertex(x, y));
      }
    }
    return result;
  }

  @Override
  public Polygon transform(Function<RealVertex, RealVertex> function) {
    return new Rectangle(function.apply(topLeftVertex), function.apply(getBottomRightVertex()));
  }
}
