package game.component;

import game.component.geometry.VirtualCanvasMapper;
import game.util.concurrent.Atomic;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class ComponentManager {
  private final ConcurrentLinkedQueue<Component> components;
  private final Atomic<VirtualCanvasMapper> virtualCanvasMapper;

  public ComponentManager() {
    this.components = new ConcurrentLinkedQueue<>();
    this.virtualCanvasMapper = new Atomic<>();
  }

  public void setVirtualCanvasMapper(VirtualCanvasMapper virtualCanvasMapper) {
    this.virtualCanvasMapper.set(virtualCanvasMapper);
  }

  public void addComponent(Component component) {
    VirtualCanvasMapper virtualCanvasMapper = this.virtualCanvasMapper.get();
    if (virtualCanvasMapper != null) {
      component = component.apply(virtualCanvasMapper);
    }
    components.add(component);
  }

  public List<Component> getComponents() {
    return new ArrayList<>(components);
  }

  public List<Component> getComponentsInOrderOfAppearance() {
    PriorityQueue<ComponentWithCachedPrecedence> componentsInOrderOfAppearance =
        new PriorityQueue<>();
    for (Component component : components) {
      componentsInOrderOfAppearance.offer(new ComponentWithCachedPrecedence(component));
    }
    return componentsInOrderOfAppearance.stream()
        .map(ComponentWithCachedPrecedence::getComponent)
        .collect(Collectors.toList());
  }

  private static class ComponentWithCachedPrecedence
      implements Comparable<ComponentWithCachedPrecedence> {
    private final double cachedPrecedence;
    private final Component component;

    private ComponentWithCachedPrecedence(Component component) {
      this.cachedPrecedence = component.getPrecedence();
      this.component = component;
    }

    @Override
    public int compareTo(ComponentWithCachedPrecedence that) {
      return Double.compare(this.cachedPrecedence, that.cachedPrecedence);
    }

    public Component getComponent() {
      return component;
    }
  }
}
