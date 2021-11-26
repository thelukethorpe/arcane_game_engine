package game.canvas;

public class Colour {

  private static final int RGB_VALUE_LIMIT = 255;

  public static final Colour BLACK = Colour.rgb(0, 0, 0);
  public static final Colour WHITE = Colour.rgb(RGB_VALUE_LIMIT, RGB_VALUE_LIMIT, RGB_VALUE_LIMIT);

  public static final Colour RED = Colour.rgb(RGB_VALUE_LIMIT, 0, 0);
  public static final Colour GREEN = Colour.rgb(0, RGB_VALUE_LIMIT, 0);
  public static final Colour BLUE = Colour.rgb(0, 0, RGB_VALUE_LIMIT);

  private final int alpha;
  private final int red;
  private final int green;
  private final int blue;

  private Colour(int alpha, int red, int green, int blue) {
    this.alpha = alpha;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  private static boolean isValidRGBValue(int rgbValue) {
    return 0 <= rgbValue && rgbValue <= RGB_VALUE_LIMIT;
  }

  public static Colour rgb(int red, int green, int blue) {
    if (!(isValidRGBValue(red) && isValidRGBValue(green) && isValidRGBValue(blue))) {
      throw new InvalidColourException("RGB Values must be between 0 and 255.");
    }
    return new Colour(RGB_VALUE_LIMIT, red, green, blue);
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

  public int asInt() {
    return ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
  }
}
