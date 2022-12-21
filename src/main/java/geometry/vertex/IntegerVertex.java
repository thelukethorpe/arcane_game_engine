package geometry.vertex;

public class IntegerVertex extends Vertex<Integer> {

  public IntegerVertex(int x, int y) {
    super(x, y);
  }

  public static IntegerVertex unbox(Vertex<Integer> vertex) {
    return new IntegerVertex(vertex.getX(), vertex.getY());
  }

  public RealVertex toReal() {
    return new RealVertex(this.getX(), this.getY());
  }

  public IntegerVertex plus(int x, int y) {
    return new IntegerVertex(this.getX() + x, this.getY() + y);
  }
}
