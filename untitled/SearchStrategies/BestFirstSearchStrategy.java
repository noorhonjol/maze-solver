package SearchStrategies;

import main.Cell;
import java.util.Comparator;


public class BestFirstSearchStrategy extends AbstractSearchStrategy {
    @Override
    protected Comparator<Cell> getComparator() {
        return new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return Integer.compare(o1.getH(),o2.getH());
            }
        };
    }

}
