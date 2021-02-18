package wow.such.pizza.even.more;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param m      number of available pizzas
 * @param t2     number of 2-person teams
 * @param t3     number of 3-person teams
 * @param t4     number of 4-person teams
 * @param pizzas list of pizzas
 */
record Pizzas(int m, int t2, int t3, int t4, List<Pizza> pizzas) {
  @Override
  public String toString() {
    return String.format("%d,%d,%d,%d\n%s", m, t2, t3, t4, Utils.toString(pizzas));
  }
}

/**
 * @param ndx         pizza's index
 * @param i           number of ingredients
 * @param ingredients list of ingredients
 */
record Pizza(int ndx, int i, String[] ingredients) {
  @Override
  public String toString() {
    return String.format("%d %s\n", i, Arrays.toString(ingredients));
  }
}

record Pair(Integer team, List<Integer> pizzaIndices) {
  @Override
  public String toString() {
    return team + Utils.toString(pizzaIndices);
  }
};

/**
 * @param name            data set name
 * @param d               number of pizza deliveries
 * @param pizzasDelivered map from team (2,3,4-person) to pizza indices
 */
record Result(String name, int d, List<Pair> pizzasDelivered, long score) {
  @Override
  public String toString() {
    var s = pizzasDelivered.stream().map(e -> e.toString()).collect(Collectors.joining("\n", "", ""));
    return String.format("%d\n%s", d, s);
  }
}
