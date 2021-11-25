package game.canvas;

public interface Canvas {

  void setPixel(int x, int y, Colour colour);

  void shade(Shader shader);
}
