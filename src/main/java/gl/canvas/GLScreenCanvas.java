package gl.canvas;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import game.canvas.ScreenCanvas;
import game.io.KeyboardMouseListener;
import gl.component.GLDrawableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

public class GLScreenCanvas implements ScreenCanvas<GLDrawableComponent> {
  private final JFrame frame;
  private final GLCanvas canvas;
  private final SimpleGLEventListener simpleGLEventListener;

  private GLScreenCanvas(JFrame frame) {
    GLProfile profile = GLProfile.get(GLProfile.GL2);
    GLCapabilities capabilities = new GLCapabilities(profile);
    this.frame = frame;
    this.canvas = new GLCanvas(capabilities);
    this.simpleGLEventListener = new SimpleGLEventListener();
    canvas.addGLEventListener(simpleGLEventListener);
    frame.add(canvas);
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public void drawToScreen(List<GLDrawableComponent> components) {
    simpleGLEventListener.loadComponents(components);
    canvas.display();
  }

  @Override
  public void show() {
    frame.setVisible(true);
  }

  @Override
  public void hide() {
    frame.setVisible(false);
  }

  @Override
  public void close() {
    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
  }

  @Override
  public int getWidth() {
    return frame.getWidth();
  }

  @Override
  public int getHeight() {
    return frame.getHeight();
  }

  @Override
  public void addKeyboardMouseListener(KeyboardMouseListener keyboardMouseListener) {
    // TODO
  }

  public static class Builder {
    private final JFrame frame = new JFrame();

    private Builder() {}

    public GLScreenCanvas build() {
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      return new GLScreenCanvas(frame);
    }

    public Builder fullscreen() {
      frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
      return this;
    }

    public Builder borderless() {
      frame.setUndecorated(true);
      return this;
    }
  }
}
