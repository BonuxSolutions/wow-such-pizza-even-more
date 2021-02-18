package wow.such.pizza.even.more;

import java.util.List;

interface TeamSelector {
    int selectTeam(int pizzasLeft);

    int toBeFedT2();

    int toBeFedT3();

    int toBeFedT4();
}

final class SelectByPriority implements TeamSelector {
    private List<Integer> teams;
    private int t2;
    private int t3;
    private int t4;

    public SelectByPriority(List<Integer> teams, Pizzas pizzas) {
        this.teams = teams;
        this.t2 = pizzas.t2();
        this.t3 = pizzas.t3();
        this.t4 = pizzas.t4();
    }

    @Override
    public int toBeFedT2() {
        return t2;
    }

    @Override
    public int toBeFedT3() {
        return t3;
    }

    @Override
    public int toBeFedT4() {
        return t4;
    }

    @Override
    public int selectTeam(int pizzasLeft) {
        int p1 = teams.get(0);
        int p2 = teams.get(1);
        int p3 = teams.get(2);

        if (p1 == 2 && t2 > 0 && pizzasLeft > 1) {
            t2--;
            return 2;
        } else if (p1 == 3 && t3 > 0 && pizzasLeft > 2) {
            t3--;
            return 3;
        } else if (p1 == 4 && t4 > 0 && pizzasLeft > 3) {
            t4--;
            return 4;
        } else if (p2 == 2 && t2 > 0 && pizzasLeft > 1) {
            t2--;
            return 2;
        } else if (p2 == 3 && t3 > 0 && pizzasLeft > 2) {
            t3--;
            return 3;
        } else if (p2 == 4 && t4 > 0 && pizzasLeft > 3) {
            t4--;
            return 4;
        } else if (p3 == 2 && t2 > 0 && pizzasLeft > 1) {
            t2--;
            return 2;
        } else if (p3 == 3 && t3 > 0 && pizzasLeft > 2) {
            t3--;
            return 3;
        } else if (p3 == 4 && t4 > 0 && pizzasLeft > 3) {
            t4--;
            return 4;
        } else {
            return 0;
        }
    }
}

final class SelectByRunningCount implements TeamSelector {
    private int t2;
    private int t3;
    private int t4;

    public SelectByRunningCount(Pizzas pizzas) {
        this.t2 = pizzas.t2();
        this.t3 = pizzas.t3();
        this.t4 = pizzas.t4();
    }

    @Override
    public int toBeFedT2() {
        return t2;
    }

    @Override
    public int toBeFedT3() {
        return t3;
    }

    @Override
    public int toBeFedT4() {
        return t4;
    }

    @Override
    public int selectTeam(int pizzasLeft) {
        if (t2 > 0 && t2 > t3 && t2 > t4 && pizzasLeft > 1) {
            t2--;
            return 2;
        } else if (t3 > 0 && t3 > t2 && t3 > t4 && pizzasLeft > 2) {
            t3--;
            return 3;
        } else if (t4 > 0 && t4 > t3 && t4 > t3 && pizzasLeft > 3) {
            t4--;
            return 4;
        } else if (t2 > 0 && t2 > t3 && pizzasLeft > 1) {
            t2--;
            return 2;
        } else if (t3 > 0 && t3 > t2 && pizzasLeft > 2) {
            t3--;
            return 3;
        } else if (t2 > 0 && pizzasLeft > 1) {
            t2--;
            return 2;
        } else {
            return 0;
        }
    }
}
