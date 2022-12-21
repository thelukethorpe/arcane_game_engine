package util;

import java.util.stream.Stream;

public class MathUtil {

  public static double minimum(Double... values) {
    return Stream.of(values).mapToDouble(x -> x).min().orElse(Double.MIN_VALUE);
  }

  public static double maximum(Double... values) {
    return Stream.of(values).mapToDouble(x -> x).max().orElse(Double.MIN_VALUE);
  }

  public static int floor(double value) {
    return (int) value;
  }

  public static int ceiling(double value) {
    int floor = floor(value);
    return floor >= value ? floor : floor + 1;
  }
}
