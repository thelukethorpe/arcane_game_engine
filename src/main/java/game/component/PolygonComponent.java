package game.component;

import game.canvas.Canvas;
import game.canvas.Colour;
import game.canvas.Shader;
import game.component.geometry.Vertex;
import game.component.geometry.VirtualCanvasMapper;
import game.component.geometry.shape.Polygon;

public class PolygonComponent extends Component {

  private final Polygon polygon;
  private final Shader shader;

  public PolygonComponent(Polygon polygon, Shader shader) {
    this.polygon = polygon;
    this.shader = shader;
  }

  @Override
  public void print(Canvas canvas) {
    for (Vertex<Integer> vertex : polygon.getIntegerCovering()) {
      int x = vertex.getX();
      int y = vertex.getY();
      Colour colour = shader.shade(x, y);
      canvas.setPixel(x, y, colour);
    }
  }

  @Override
  public Component apply(VirtualCanvasMapper virtualCanvasMapper) {
    return new PolygonComponent(
        virtualCanvasMapper.map(polygon),
        (x, y) -> {
          Vertex<Double> virtualVertex = virtualCanvasMapper.reverse(new Vertex<>(x, y));
          return shader.shade(virtualVertex.getX(), virtualVertex.getY());
        });
  }
}
