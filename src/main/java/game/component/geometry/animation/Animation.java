package game.component.geometry.animation;

import game.component.Component;

@FunctionalInterface
public interface Animation<TComponent extends Component> {
  void tick(long timeInMs, TComponent component);
}
