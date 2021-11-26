package game.component.geometry.animation;

import game.component.Component;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class Animation<TComponent extends Component> {

  private final Duration duration;
  private final Animator<TComponent> animator;

  public Animation(Duration duration, Animator<TComponent> animator) {
    this.duration = duration;
    this.animator = animator;
  }

  public void execute(TComponent component) {
    long durationMs = duration.toMillis();
    long startTimeMs = System.currentTimeMillis();
    Supplier<Long> getCurrentTimeMs = () -> System.currentTimeMillis() - startTimeMs;
    for (long currentTimeMs = getCurrentTimeMs.get();
        currentTimeMs <= durationMs;
        currentTimeMs = getCurrentTimeMs.get()) {
      animator.animate(currentTimeMs, component);
    }
  }

  public Future<Void> executeAsync(TComponent component) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    return executor.submit(
        () -> {
          execute(component);
          return null;
        });
  }
}
