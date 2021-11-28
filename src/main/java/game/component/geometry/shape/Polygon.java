package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import game.util.MathUtil;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class Polygon {

  public abstract boolean contains(IntegerVertex vertex);

  protected abstract Rectangle getRectangleCovering();

  public Stream<IntegerVertex> getIntegerCovering() {
    Rectangle rectangleCovering = this.getRectangleCovering();
    IntegerVertex topLeftVertex =
        IntegerVertex.unbox(rectangleCovering.getTopLeftVertex().map(MathUtil::floor));
    IntegerVertex bottomRightVertex =
        IntegerVertex.unbox(rectangleCovering.getBottomRightVertex().map(MathUtil::ceiling));
    return Stream.iterate(
            topLeftVertex,
            vertex -> !vertex.equals(bottomRightVertex),
            vertex -> {
              if (vertex.getY() <= bottomRightVertex.getY()) {
                return vertex.plus(0, 1);
              }
              return new IntegerVertex(vertex.getX() + 1, topLeftVertex.getY());
            })
        .filter(this::contains);
  }

  public abstract Polygon transform(Function<RealVertex, RealVertex> function);
}
