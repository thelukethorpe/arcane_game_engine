package game.component.geometry;

import game.component.geometry.shape.Polygon;

public class ScreenCanvasAdapter {

  private final double scale;
  private final RealVertex offset;

  public ScreenCanvasAdapter(IntegerVertex inputDimensions, IntegerVertex outputDimensions) {
    this.scale =
        Math.min(
            outputDimensions.getX() / (double) inputDimensions.getX(),
            outputDimensions.getY() / (double) inputDimensions.getY());
    this.offset = outputDimensions.toReal().minus(inputDimensions.toReal().grow(scale)).shrink(2.0);
  }

  public RealVertex reverse(RealVertex vertex) {
    return vertex.minus(offset).shrink(scale);
  }

  public RealVertex adapt(RealVertex vertex) {
    return vertex.grow(scale).plus(offset);
  }

  public Polygon adapt(Polygon polygon) {
    return polygon.transform(this::adapt);
  }
}
