package wow.such.pizza.even.more;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

final class Scoring {
    private List<Pizza> pizzas;

    Scoring(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    long calculate(List<Pair> pizzasDelivered) {
        var deliveredPizzas = StreamSupport.stream(pizzasDelivered.spliterator(), false).map(Pair::pizzaIndices);
        long ingredients = deliveredPizzas.map(p -> {
            long c = p.stream().flatMap(id -> Arrays.stream(pizzas.get(id).ingredients())).distinct().count();
            return c * c;
        }).reduce(0L, Long::sum);

        return ingredients;
    }
}
