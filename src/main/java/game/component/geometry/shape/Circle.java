package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import game.util.MathUtil;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

public class Circle implements Polygon {

  private final RealVertex centre;
  private final double radius;

  public Circle(RealVertex centre, double radius) {
    this.centre = centre;
    this.radius = Math.abs(radius);
  }

  @Override
  public boolean contains(IntegerVertex vertex) {
    double xDiff = centre.getX() - vertex.getX();
    double yDiff = centre.getY() - vertex.getY();
    return xDiff * xDiff + yDiff * yDiff <= radius * radius;
  }

  @Override
  public Collection<IntegerVertex> getIntegerCovering() {
    int xTopLeft = MathUtil.floor(centre.getX() - radius);
    int yTopLeft = MathUtil.floor(centre.getY() - radius);
    int xBottomRight = MathUtil.ceiling(centre.getX() + radius);
    int yBottomRight = MathUtil.ceiling(centre.getY() + radius);
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
    RealVertex transformedCentre = function.apply(centre);
    RealVertex transformedCircumferencePoint =
        function.apply(centre.plus(new RealVertex(radius, 0.0)));
    return new Circle(
        transformedCentre, transformedCentre.distanceFrom(transformedCircumferencePoint));
  }
}
