package wow.such.pizza.even.more;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

interface Pizzeria {
    List<Integer> pizzas(int n);

    int pizzasLeft();
}

final class PizzeriaWithSortedPizzas implements Pizzeria {
    private Pizzeria pizzaProvider;

    PizzeriaWithSortedPizzas(List<Pizza> pizzas, Class<? extends Pizzeria> clazz) {
        pizzas.sort(new Comparator<Pizza>() {
            @Override
            public int compare(Pizza o1, Pizza o2) {
                if (o1.i() > o2.i()) {
                    return 1;
                } else if (o1.i() < o2.i()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        try {
            this.pizzaProvider = clazz.getConstructor(List.class).newInstance(pizzas);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int pizzasLeft() {
        return pizzaProvider.pizzasLeft();
    }

    @Override
    public List<Integer> pizzas(int n) {
        return pizzaProvider.pizzas(n);
    }
}

final class PizzeriaThatGivesFirstAndLastPizzas implements Pizzeria {
    private LinkedList<Pizza> pizzas;

    public PizzeriaThatGivesFirstAndLastPizzas(List<Pizza> pizzas) {
        this.pizzas = new LinkedList<>(pizzas);
    }

    @Override
    public int pizzasLeft() {
        return pizzas.size();
    }

    @Override
    public List<Integer> pizzas(int n) {
        if (n == 2 && pizzas.size() > 1) {
            return List.of(pizzas.removeFirst().ndx(), pizzas.removeLast().ndx());
        } else if (n == 3 && pizzas.size() > 2) {
            return List.of(pizzas.removeFirst().ndx(), pizzas.removeLast().ndx(), pizzas.removeFirst().ndx());
        } else if (n == 4 && pizzas.size() > 3) {
            return List.of(pizzas.removeFirst().ndx(), pizzas.removeLast().ndx(), pizzas.removeFirst().ndx(),
                    pizzas.removeLast().ndx());
        } else {
            System.out.println("Requested " + n + " pizzas, but only " + pizzas.size() + " available.");
            return List.of();
        }
    }
}

final class PizzeriaThatTakesPizzasFromTop implements Pizzeria {
    private LinkedList<Pizza> pizzas;

    public PizzeriaThatTakesPizzasFromTop(List<Pizza> pizzas) {
        this.pizzas = new LinkedList<>(pizzas);
    }

    @Override
    public int pizzasLeft() {
        return pizzas.size();
    }

    @Override
    public List<Integer> pizzas(int n) {
        if (n == 2 && pizzas.size() > 1) {
            return List.of(pizzas.removeFirst().ndx(), pizzas.removeFirst().ndx());
        } else if (n == 3 && pizzas.size() > 2) {
            return List.of(pizzas.removeFirst().ndx(), pizzas.removeFirst().ndx(), pizzas.removeFirst().ndx());
        } else if (n == 4 && pizzas.size() > 3) {
            return List.of(pizzas.removeFirst().ndx(), pizzas.removeFirst().ndx(), pizzas.removeFirst().ndx(),
                    pizzas.removeFirst().ndx());
        } else {
            System.out.println("Requested " + n + " pizzas, but only " + pizzas.size() + " available.");
            return List.of();
        }
    }
}

final class PizzeriaThatTakesPizzasFromBottom implements Pizzeria {
    private LinkedList<Pizza> pizzas;

    public PizzeriaThatTakesPizzasFromBottom(List<Pizza> pizzas) {
        this.pizzas = new LinkedList<>(pizzas);
    }

    @Override
    public int pizzasLeft() {
        return pizzas.size();
    }

    @Override
    public List<Integer> pizzas(int n) {
        if (n == 2 && pizzas.size() > 1) {
            return List.of(pizzas.removeLast().ndx(), pizzas.removeLast().ndx());
        } else if (n == 3 && pizzas.size() > 2) {
            return List.of(pizzas.removeLast().ndx(), pizzas.removeLast().ndx(), pizzas.removeLast().ndx());
        } else if (n == 4 && pizzas.size() > 3) {
            return List.of(pizzas.removeLast().ndx(), pizzas.removeLast().ndx(), pizzas.removeLast().ndx(),
                    pizzas.removeLast().ndx());
        } else {
            System.out.println("Requested " + n + " pizzas, but only " + pizzas.size() + " available.");
            return List.of();
        }
    }
}