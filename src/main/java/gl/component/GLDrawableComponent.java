package gl.component;

import com.jogamp.opengl.GL2;
import game.component.Component;

public abstract class GLDrawableComponent extends Component {
  protected GLDrawableComponent(double precedence) {
    super(precedence);
  }

  public abstract int begin();

  public abstract void draw(GL2 gl2);
}
