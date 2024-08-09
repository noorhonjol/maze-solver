package SearchStrategies;

import main.Cell;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class AStarStrategy extends AbstractSearchStrategy {

    @Override
    protected Comparator<Cell> getComparator() {
        return new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                if (!o1.f.equals(o2.f)) {
                    return Integer.compare(o1.f, o2.f);
                } else {
                    return Integer.compare(o1.h, o2.h);
                }
            }
        };
    }
}
