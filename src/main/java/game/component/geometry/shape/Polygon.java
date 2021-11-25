package game.component.geometry.shape;

import game.component.geometry.Vertex;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface Polygon {

  boolean contains(Vertex<Integer> vertex);

  List<Vertex<Double>> vertices();

  Collection<Vertex<Integer>> getIntegerCovering();

  Polygon transform(Function<Vertex<Double>, Vertex<Double>> function);
}
