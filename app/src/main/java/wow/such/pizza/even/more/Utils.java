package wow.such.pizza.even.more;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class Utils {

  private Utils() {
    throw new AssertionError("Must not call Utils constructor");
  }

  static int[] parseInts(String[] strs) {
    var ints = new int[strs.length];
    for (int i = 0; i < strs.length; i++) {
      ints[i] = Integer.parseInt(strs[i]);
    }
    return ints;
  }

  static Pizzas readFromInputStream(InputStream inputStream) {
    var pizzasBuilder = new PizzasBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      var line = br.readLine();
      assert line != null;
      pizzasBuilder.withFirstLine(line.split(" "));
      var ndx = 0;
      while ((line = br.readLine()) != null) {
        pizzasBuilder.withNthLine(ndx, line.split(" "));
        ndx++;
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    return pizzasBuilder.build();
  }

  static void saveResult(Result result) {
    Path path = Paths.get(result.name() + ".score." + result.score());
    byte[] strToBytes = result.toString().getBytes();

    try {
      Files.write(path, strToBytes);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  static <A> String toString(List<A> elems) {
    return elems.stream().map(Object::toString).reduce("", (partialString, element) -> partialString + " " + element);
  }

  private static class PizzasBuilder {
    private int m, t2, t3, t4;
    private List<Pizza> pizzas;

    PizzasBuilder withFirstLine(String[] firstLine) {
      var line = parseInts(firstLine);

      m = line[0];
      pizzas = new ArrayList<>(m);
      t2 = line[1];
      t3 = line[2];
      t4 = line[3];

      return this;
    }

    PizzasBuilder withNthLine(int ndx, String[] nthLine) {
      var i = Integer.parseInt(nthLine[0]);
      var ings = Arrays.copyOfRange(nthLine, 1, nthLine.length);
      assert ings.length == i;
      pizzas.add(new Pizza(ndx, i, ings));

      return this;
    }

    Pizzas build() {
      return new Pizzas(m, t2, t3, t4, pizzas);
    }
  }
}