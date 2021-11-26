package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import java.util.Collection;
import java.util.function.Function;

public interface Polygon {

  boolean contains(IntegerVertex vertex);

  Collection<IntegerVertex> getIntegerCovering();

  Polygon transform(Function<RealVertex, RealVertex> function);
}
