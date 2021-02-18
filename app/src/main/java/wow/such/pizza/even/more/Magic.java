package wow.such.pizza.even.more;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class Magic {
  PizzaDeliveryBoy deliveryBoy;
  Scoring scoring;

  public Magic(PizzaDeliveryBoy deliveryBoy, Scoring scoring) {
    this.deliveryBoy = deliveryBoy;
    this.scoring = scoring;
  }

  Result go(String name) {
    List<Pair> pizzasDelivered = StreamSupport.stream(deliveryBoy.deliverPizzas(), false)
        .map(pizzas -> new Pair(pizzas.size(), pizzas)).collect(Collectors.toList());
    return new Result(name, pizzasDelivered.size(), pizzasDelivered, scoring.calculate(pizzasDelivered));
  }
}
