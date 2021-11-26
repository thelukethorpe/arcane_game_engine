package game.component.geometry.animation;

import game.component.Component;

@FunctionalInterface
public interface Animator<TComponent extends Component> {
  void animate(long timeInMs, TComponent component);
}
