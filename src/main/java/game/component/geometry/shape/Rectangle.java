package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import java.util.function.Function;

public class Rectangle extends Polygon {

  private final RealVertex topLeftVertex;
  private final double width;
  private final double height;

  public Rectangle(RealVertex topLeftVertex, double width, double height) {
    this.topLeftVertex = topLeftVertex;
    this.width = width;
    this.height = height;
  }

  public Rectangle(RealVertex topLeftVertex, RealVertex bottomRightVertex) {
    this(
        topLeftVertex,
        bottomRightVertex.getX() - topLeftVertex.getX(),
        bottomRightVertex.getY() - topLeftVertex.getY());
  }

  public RealVertex getTopLeftVertex() {
    return topLeftVertex;
  }

  public RealVertex getBottomRightVertex() {
    return topLeftVertex.plus(new RealVertex(width, height));
  }

  @Override
  public boolean contains(IntegerVertex vertex) {
    return topLeftVertex.getX() <= vertex.getX()
        && vertex.getX() <= topLeftVertex.getX() + width
        && topLeftVertex.getY() <= vertex.getY()
        && vertex.getY() <= topLeftVertex.getY() + height;
  }

  @Override
  protected Rectangle getRectangleCovering() {
    return this;
  }

  @Override
  public Polygon transform(Function<RealVertex, RealVertex> function) {
    return new Rectangle(function.apply(topLeftVertex), function.apply(getBottomRightVertex()));
  }
}
