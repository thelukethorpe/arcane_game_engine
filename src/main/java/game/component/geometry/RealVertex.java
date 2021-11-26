package game.component.geometry;

public class RealVertex extends Vertex<Double> {
  public RealVertex(double x, double y) {
    super(x, y);
  }

  public static RealVertex unbox(Vertex<Double> vertex) {
    return new RealVertex(vertex.getX(), vertex.getY());
  }

  public RealVertex plus(RealVertex that) {
    return new RealVertex(this.getX() + that.getX(), this.getY() + that.getY());
  }

  public RealVertex minus(RealVertex that) {
    return new RealVertex(this.getX() - that.getX(), this.getY() - that.getY());
  }

  public RealVertex grow(double factor) {
    return unbox(this.map(v -> v * factor));
  }

  public RealVertex shrink(double factor) {
    return unbox(this.map(v -> v / factor));
  }
}
