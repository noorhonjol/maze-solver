package SearchStrategies;

import main.Cell;

import java.util.Comparator;


public class UniformCostSearchStrategy extends AbstractSearchStrategy {

    @Override
    protected Comparator<Cell> getComparator() {
        return new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return Integer.compare(o1.getG(), o2.getG());
            }
        };
    }
}
