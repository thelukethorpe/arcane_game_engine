package geometry.vertex;

public class RealVertex extends Vertex<Double> {
  public RealVertex(double x, double y) {
    super(x, y);
  }

  public static RealVertex unbox(Vertex<Double> vertex) {
    return new RealVertex(vertex.getX(), vertex.getY());
  }

  public double distanceFrom(RealVertex that) {
    double xDiff = this.getX() - that.getX();
    double yDiff = this.getY() - that.getY();
    return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
  }

  public RealVertex plus(RealVertex that) {
    return new RealVertex(this.getX() + that.getX(), this.getY() + that.getY());
  }

  public RealVertex minus(RealVertex that) {
    return new RealVertex(this.getX() - that.getX(), this.getY() - that.getY());
  }

  public RealVertex scale(double factor) {
    return unbox(this.map(v -> v * factor));
  }
}