package game.component.geometry.shape;

import game.component.geometry.IntegerVertex;
import game.component.geometry.RealVertex;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface Polygon {

  boolean contains(IntegerVertex vertex);

  List<RealVertex> vertices();

  Collection<IntegerVertex> getIntegerCovering();

  Polygon transform(Function<RealVertex, RealVertex> function);
}
