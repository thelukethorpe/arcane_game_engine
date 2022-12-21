package geometry.shape;

import geometry.vertex.RealVertex;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Polygon {

  private final List<RealVertex> vertices;

  public Polygon(List<RealVertex> vertices) {
    this.vertices = Collections.unmodifiableList(vertices);
  }

  public List<RealVertex> getVertices() {
    return vertices;
  }

  public Polygon transform(Function<RealVertex, RealVertex> function) {
    return new Polygon(vertices.stream().map(function).collect(Collectors.toList()));
  }
}
