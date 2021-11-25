package game.canvas;

import game.io.CursorEvent;
import game.io.KeyboardMouseListener;
import java.awt.Canvas;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class BufferedScreenCanvas implements ScreenCanvas {
  private final BufferedImage bufferedImage;
  private final JFrame frame;
  private final Canvas canvas;

  private BufferedScreenCanvas(JFrame frame) {
    this.bufferedImage =
        new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
    this.frame = frame;
    this.canvas = new Canvas();
    frame.add(canvas);
  }

  public static Builder builder() {
    return new Builder();
  }

  private boolean isValidPixel(int x, int y) {
    return 0 <= x && x < this.getWidth() && 0 <= y && y < this.getHeight();
  }

  @Override
  public void paintToScreen() {
    canvas.getGraphics().drawImage(bufferedImage, 0, 0, null);
  }

  @Override
  public void setPixel(int x, int y, Colour colour) {
    if (isValidPixel(x, y)) {
      bufferedImage.setRGB(x, y, colour.asInt());
    }
  }

  @Override
  public void shade(Shader shader) {
    for (int x = 0; x < this.getWidth(); x++) {
      for (int y = 0; y < this.getHeight(); y++) {
        setPixel(x, y, shader.shade(x, y));
      }
    }
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
    return bufferedImage.getWidth();
  }

  @Override
  public int getHeight() {
    return bufferedImage.getHeight();
  }

  @Override
  public void addKeyboardMouseListener(KeyboardMouseListener keyboardMouseListener) {
    canvas.addMouseListener(
        new MouseListener() {
          private CursorEvent eventAdaptor(MouseEvent e) {
            return new CursorEvent(e.getXOnScreen(), e.getXOnScreen());
          }

          @Override
          public void mouseClicked(MouseEvent e) {}

          @Override
          public void mousePressed(MouseEvent e) {
            keyboardMouseListener.onMousePress(eventAdaptor(e));
          }

          @Override
          public void mouseReleased(MouseEvent e) {
            keyboardMouseListener.onMouseRelease(eventAdaptor(e));
          }

          @Override
          public void mouseEntered(MouseEvent e) {}

          @Override
          public void mouseExited(MouseEvent e) {}
        });
  }

  public static class Builder {
    private final JFrame frame = new JFrame();

    private Builder() {}

    public BufferedScreenCanvas build() {
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      return new BufferedScreenCanvas(frame);
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
