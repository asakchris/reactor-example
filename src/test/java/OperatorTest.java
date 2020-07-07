import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperatorTest {
  @Test
  void map() {
    Flux.range(1, 5).map(elem -> elem * 10).subscribe(System.out::println);
  }

  @Test
  void flatMap() {
    Flux.range(1, 5).flatMap(elem -> Flux.range(elem * 10, 2)).subscribe(System.out::println);
  }

  @Test
  void flatMapMany() {
    Mono.just(3).flatMapMany(elem -> Flux.range(1, elem)).subscribe(System.out::println);
  }

  @Test
  void concat() throws InterruptedException {
    Flux<Integer> oneToFive = Flux.range(1, 5).delayElements(Duration.ofMillis(200));
    Flux<Integer> sixToTen = Flux.range(6, 5).delayElements(Duration.ofMillis(400));

    Flux.concat(oneToFive, sixToTen).subscribe(System.out::println);

    Thread.sleep(4000);
  }

  @Test
  void merge() throws InterruptedException {
    Flux<Integer> oneToFive = Flux.range(1, 5).delayElements(Duration.ofMillis(200));
    Flux<Integer> sixToTen = Flux.range(6, 5).delayElements(Duration.ofMillis(400));

    Flux.merge(oneToFive, sixToTen).subscribe(System.out::println);

    Thread.sleep(4000);
  }

  @Test
  void zip() {
    Flux<Integer> oneToFive = Flux.range(1, 5);
    Flux<Integer> sixToTen = Flux.range(6, 5);

    Flux.zip(oneToFive, sixToTen, (item1, item2) -> item1 + ", " + item2)
        .subscribe(System.out::println);
  }
}
