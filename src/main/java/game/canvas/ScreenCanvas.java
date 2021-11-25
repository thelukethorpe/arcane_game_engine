package game.canvas;

import game.io.KeyboardMouseListener;

public interface ScreenCanvas extends Canvas {

  void paintToScreen();

  void show();

  void hide();

  void close();

  int getWidth();

  int getHeight();

  void addKeyboardMouseListener(KeyboardMouseListener keyboardMouseListener);
}
