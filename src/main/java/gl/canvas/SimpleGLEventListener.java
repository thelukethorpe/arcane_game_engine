package gl.canvas;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import gl.component.GLDrawableComponent;

import java.util.Collections;
import java.util.List;

public class SimpleGLEventListener implements GLEventListener {
  private List<GLDrawableComponent> glDrawableComponents = Collections.emptyList();

  public void loadComponents(List<GLDrawableComponent> glDrawableComponents) {
    this.glDrawableComponents = glDrawableComponents;
  }

  @Override
  public void init(GLAutoDrawable glAutoDrawable) {}

  @Override
  public void dispose(GLAutoDrawable glAutoDrawable) {}

  @Override
  public void display(GLAutoDrawable glAutoDrawable) {
    GL2 gl2 = glAutoDrawable.getGL().getGL2();
    for (GLDrawableComponent glDrawableComponent : glDrawableComponents) {
      gl2.glBegin(glDrawableComponent.begin());
      glDrawableComponent.draw(gl2);
      gl2.glEnd();
    }
  }

  @Override
  public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {}
}
