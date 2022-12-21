package game.component;

import util.time.Tickable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class ComponentManager<TComponent extends Component> implements Tickable {
  private final ConcurrentLinkedQueue<TComponent> components;

  public ComponentManager() {
    this.components = new ConcurrentLinkedQueue<>();
  }

  @Override
  public void tick(double delta) {
    components.forEach(component -> component.tick(delta));
  }

  public void addComponent(TComponent component) {
    components.add(component);
  }

  public List<TComponent> getComponentsInReverseOrderOfAppearance() {
    PriorityQueue<ComponentWithFixedPrecedence> componentsInOrderOfAppearance =
        new PriorityQueue<>(Comparator.reverseOrder());
    components.stream()
        .map(ComponentWithFixedPrecedence::new)
        .forEach(componentsInOrderOfAppearance::offer);
    return componentsInOrderOfAppearance.stream()
        .map(ComponentWithFixedPrecedence::getComponent)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private class ComponentWithFixedPrecedence implements Comparable<ComponentWithFixedPrecedence> {
    private final double precedence;
    private final TComponent component;

    private ComponentWithFixedPrecedence(TComponent component) {
      this.precedence = component.getPrecedence();
      this.component = component;
    }

    public TComponent getComponent() {
      return component;
    }

    @Override
    public int compareTo(ComponentWithFixedPrecedence that) {
      return Double.compare(this.precedence, that.precedence);
    }
  }
}
