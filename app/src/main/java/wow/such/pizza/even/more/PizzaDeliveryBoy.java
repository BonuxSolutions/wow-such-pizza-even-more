package wow.such.pizza.even.more;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

interface PizzaDeliveryBoy extends Iterable<List<Integer>> {
    default Spliterator<List<Integer>> deliverPizzas() {
        return spliterator();
    };
}

final class Fry implements PizzaDeliveryBoy {
    private Pizzeria pizzeria;
    private TeamSelector teamSelector;

    public Fry(TeamSelector teamSelector, Pizzeria pizzaProvider) {
        this.teamSelector = teamSelector;
        this.pizzeria = pizzaProvider;
    }

    @Override
    public Iterator<List<Integer>> iterator() {
        return new Iterator<List<Integer>>() {
            @Override
            public boolean hasNext() {
                return (pizzeria.pizzasLeft() > 1 && teamSelector.toBeFedT2() > 0)
                        || (pizzeria.pizzasLeft() > 2 && teamSelector.toBeFedT3() > 0)
                        || (pizzeria.pizzasLeft() > 3 && teamSelector.toBeFedT4() > 0);
            }

            @Override
            public List<Integer> next() {
                return pizzeria.pizzas(teamSelector.selectTeam(pizzeria.pizzasLeft()));
            }
        };
    }
}
