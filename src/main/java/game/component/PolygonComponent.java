package game.component;

import game.canvas.Canvas;
import game.canvas.Colour;
import game.canvas.Shader;
import game.component.geometry.ScreenCanvasAdapter;
import game.component.geometry.Vertex;
import game.component.geometry.shape.Polygon;

public class PolygonComponent extends Component {

  private final Shader shader;
  private Polygon polygon;

  public PolygonComponent(Polygon polygon, Shader shader, double precedence) {
    super(precedence);
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
  public void translate(Vertex<Double> offset) {
    polygon =
        polygon.transform(
            vertex -> new Vertex<>(vertex.getX() + offset.getX(), vertex.getY() + offset.getY()));
  }

  @Override
  public Component apply(ScreenCanvasAdapter screenCanvasAdapter) {
    return new PolygonComponent(
        screenCanvasAdapter.adapt(polygon),
        (x, y) -> {
          Vertex<Double> virtualVertex = screenCanvasAdapter.reverse(new Vertex<>(x, y));
          return shader.shade(virtualVertex.getX(), virtualVertex.getY());
        },
        this.getPrecedence());
  }
}
