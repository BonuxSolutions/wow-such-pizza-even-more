package wow.such.pizza.even.more;

import static wow.such.pizza.even.more.Utils.readFromInputStream;
import static wow.such.pizza.even.more.Utils.saveResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) {
    var allfiles = args[0].split(",");
    if (allfiles.length == 1) {
      forFile(args[0]);
    } else if (allfiles.length > 1) {
      Arrays.asList(allfiles).forEach(App::forFile);
    }
  }

  private static void forFile(String file) {
    try {
      var is = new FileInputStream(file);
      var pizzas = readFromInputStream(is);

      var scoring = new Scoring(pizzas.pizzas());

      var teamSelectors = Map.of(
        "SelectByRunningCount", new SelectByRunningCount(pizzas),
        "List.of(2, 3, 4)", new SelectByPriority(List.of(2, 3, 4), pizzas),
        "List.of(2, 4, 3)", new SelectByPriority(List.of(2, 4, 3), pizzas),
        "List.of(3, 2, 4)", new SelectByPriority(List.of(3, 2, 4), pizzas),
        "List.of(3, 4, 2)", new SelectByPriority(List.of(3, 4, 2), pizzas),
        "List.of(4, 3, 2)", new SelectByPriority(List.of(4, 3, 2), pizzas),
        "List.of(4, 2, 3)", new SelectByPriority(List.of(4, 2, 3), pizzas)
      );
      var pizzerias = Map.<String, Pizzeria>of(
        "PizzeriaWithSortedPizzasThatGivesFirstAndLastPizzas", new PizzeriaWithSortedPizzas(pizzas.pizzas(), PizzeriaThatGivesFirstAndLastPizzas.class), "PizzeriaWithSortedPizzasThatTakesPizzasFromTop", new PizzeriaWithSortedPizzas(pizzas.pizzas(), PizzeriaThatTakesPizzasFromTop.class),
        "PizzeriaWithSortedPizzasThatTakesPizzasFromBottom", new PizzeriaWithSortedPizzas(pizzas.pizzas(), PizzeriaThatTakesPizzasFromBottom.class),
        "PizzeriaThatGivesFirstAndLastPizzas", new PizzeriaThatGivesFirstAndLastPizzas(pizzas.pizzas()),
        "PizzeriaThatTakesPizzasFromTop", new PizzeriaThatTakesPizzasFromTop(pizzas.pizzas()),
        "PizzeriaThatTakesPizzasFromBottom", new PizzeriaThatTakesPizzasFromBottom(pizzas.pizzas())
      );

      Result best = null;
      String combo = null;
      for (Map.Entry<String, TeamSelector> e : teamSelectors.entrySet()) {
        var teamSelector = e.getValue();
        for (Map.Entry<String, Pizzeria> e2 : pizzerias.entrySet()) {
          var pizzeria = e2.getValue();
          var result = new Magic(new Fry(teamSelector, pizzeria), scoring).go(file);
          if (best == null || result.score() > best.score()) {
            best = result;
            combo = e.getKey() + " - " + e2.getKey();
          }
        }
      }
      ;

      System.out.println(combo + " gave result " + best.score() + " for " + file);

      saveResult(best);
    } catch (FileNotFoundException e) {
      throw new UncheckedIOException(e);
    }
  }
}