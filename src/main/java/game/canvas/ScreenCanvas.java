package game.canvas;

import game.component.Component;
import game.io.KeyboardMouseListener;

import java.util.List;

public interface ScreenCanvas<TComponent extends Component> {

  void drawToScreen(List<TComponent> components);

  void show();

  void hide();

  void close();

  int getWidth();

  int getHeight();

  void addKeyboardMouseListener(KeyboardMouseListener keyboardMouseListener);
}
