package SearchStrategies;

import main.Cell;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearchStrategy extends AbstractSearchStrategy {
    @Override
    protected Comparator<Cell> getComparator() {
        return new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return Integer.compare(o1.h,o2.h);
            }
        };
    }

}
