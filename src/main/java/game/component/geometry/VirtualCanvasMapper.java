package game.component.geometry;

import game.component.geometry.shape.Polygon;

public class VirtualCanvasMapper {

  private final double scale;
  private final double xOffset;
  private final double yOffset;

  public VirtualCanvasMapper(Vertex<Integer> inputDimensions, Vertex<Integer> outputDimensions) {
    this.scale =
        Math.min(
            outputDimensions.getX() / (double) inputDimensions.getX(),
            outputDimensions.getY() / (double) inputDimensions.getY());
    this.xOffset = (outputDimensions.getX() - inputDimensions.getX() * scale) / 2.0;
    this.yOffset = (outputDimensions.getY() - inputDimensions.getY() * scale) / 2.0;
  }

  public Vertex<Double> reverse(Vertex<Double> vertex) {
    //    return new Vertex<>((vertex.getX() / scale) - xOffset, (vertex.getY() / scale) - yOffset);
    return new Vertex<>((vertex.getX() - xOffset) / scale, (vertex.getY() - yOffset) / scale);
  }

  public Vertex<Double> map(Vertex<Double> vertex) {
    //    return new Vertex<>((vertex.getX() + xOffset) * scale, (vertex.getY() + yOffset) * scale);
    return new Vertex<>(vertex.getX() * scale + xOffset, vertex.getY() * scale + yOffset);
  }

  public Polygon map(Polygon polygon) {
    return polygon.transform(this::map);
  }
}
