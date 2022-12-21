package gl.component;

import com.jogamp.opengl.GL2;
import game.canvas.Colour;
import game.canvas.Shader;
import geometry.shape.Polygon;
import geometry.vertex.RealVertex;

public abstract class GLPolygonDrawableComponent extends GLDrawableComponent {
  private final Shader shader;
  private Polygon polygon;

  protected GLPolygonDrawableComponent(Polygon polygon, Shader shader, double precedence) {
    super(precedence);
    this.polygon = polygon;
    this.shader = shader;
  }

  @Override
  public void translate(RealVertex offset) {
    polygon = polygon.transform(realVertex -> realVertex.plus(offset));
  }

  @Override
  public void scale(double factor) {
    polygon = polygon.transform(realVertex -> realVertex.scale(factor));
  }

  @Override
  public int begin() {
    return GL2.GL_POLYGON;
  }

  @Override
  public void draw(GL2 gl2) {
    for (RealVertex realVertex : polygon.getVertices()) {
      Colour colour = shader.shade(realVertex.getX(), realVertex.getY());
      gl2.glColor3i(colour.getRed(), colour.getGreen(), colour.getBlue());
      ;
      gl2.glVertex2d(realVertex.getX(), realVertex.getY());
    }
  }
}
