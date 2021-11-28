package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import java.util.function.Function;

public class Circle extends Polygon {

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
  protected Rectangle getRectangleCovering() {
    RealVertex radialVertex = new RealVertex(radius, radius);
    return new Rectangle(centre.minus(radialVertex), centre.plus(radialVertex));
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
