package game.canvas;

@FunctionalInterface
public interface Shader {
  static Shader of(Colour colour) {
    return (x, y) -> colour;
  }

  Colour shade(double x, double y);
}
