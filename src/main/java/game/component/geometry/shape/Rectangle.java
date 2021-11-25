package game.component.geometry.shape;

import game.component.geometry.Vertex;
import game.util.MathUtil;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Rectangle implements Polygon {

  private final Vertex<Double> topLeftVertex;
  private final double width;
  private final double height;

  public Rectangle(Vertex<Double> topLeftVertex, double width, double height) {
    this.topLeftVertex = topLeftVertex;
    this.width = width;
    this.height = height;
  }

  private Rectangle(Vertex<Double> topLeftVertex, Vertex<Double> bottomRightVertex) {
    this(
        topLeftVertex,
        bottomRightVertex.getX() - topLeftVertex.getX(),
        bottomRightVertex.getY() - topLeftVertex.getY());
  }

  private Vertex<Double> getBottomRightVertex() {
    return new Vertex<>(topLeftVertex.getX() + width, topLeftVertex.getY() + height);
  }

  @Override
  public boolean contains(Vertex<Integer> vertex) {
    return topLeftVertex.getX() <= vertex.getX()
        && vertex.getX() <= topLeftVertex.getX() + width
        && topLeftVertex.getY() <= vertex.getY()
        && vertex.getY() <= topLeftVertex.getY() + height;
  }

  @Override
  public List<Vertex<Double>> vertices() {
    double xTopLeft = topLeftVertex.getX();
    double yTopLeft = topLeftVertex.getY();
    return List.of(
        new Vertex<>(xTopLeft, yTopLeft),
        new Vertex<>(xTopLeft + width, yTopLeft),
        new Vertex<>(xTopLeft + width, yTopLeft + height),
        new Vertex<>(xTopLeft, yTopLeft + height));
  }

  @Override
  public Collection<Vertex<Integer>> getIntegerCovering() {
    int xTopLeft = MathUtil.ceiling(topLeftVertex.getX());
    int yTopLeft = MathUtil.ceiling(topLeftVertex.getY());
    int xBottomRight = MathUtil.floor(topLeftVertex.getX() + width);
    int yBottomRight = MathUtil.floor(topLeftVertex.getY() + height);
    Collection<Vertex<Integer>> result = new LinkedList<>();
    for (int x = xTopLeft; x <= xBottomRight; x++) {
      for (int y = yTopLeft; y <= yBottomRight; y++) {
        result.add(new Vertex<>(x, y));
      }
    }
    return result;
  }

  @Override
  public Polygon transform(Function<Vertex<Double>, Vertex<Double>> function) {
    return new Rectangle(function.apply(topLeftVertex), function.apply(getBottomRightVertex()));
  }
}
