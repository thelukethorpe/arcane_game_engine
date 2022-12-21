package geometry.shape;

import geometry.vertex.RealVertex;

public class Circle {

  private final RealVertex centre;
  private final double radius;

  public Circle(RealVertex centre, double radius) {
    this.centre = centre;
    this.radius = Math.abs(radius);
  }

  public Circle translate(RealVertex offset) {
    return new Circle(centre.plus(offset), radius);
  }

  public Circle scale(double factor) {
    return new Circle(centre.scale(factor), radius * factor);
  }

  public RealVertex getCentre() {
    return centre;
  }

  public double getRadius() {
    return radius;
  }
}
