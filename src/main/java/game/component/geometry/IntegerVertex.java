package game.component.geometry;

public class IntegerVertex extends Vertex<Integer> {

  public IntegerVertex(int x, int y) {
    super(x, y);
  }

  public RealVertex toReal() {
    return new RealVertex(this.getX(), this.getY());
  }
}
