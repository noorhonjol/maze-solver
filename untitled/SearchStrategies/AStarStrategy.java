package SearchStrategies;

import main.Cell;

import java.util.Comparator;

public class AStarStrategy extends AbstractSearchStrategy {

    @Override
    protected Comparator<Cell> getComparator() {
        return new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                if (!o1.getF().equals(o2.getF())) {
                    return Integer.compare(o1.getF(), o2.getF());
                } else {
                    return Integer.compare(o1.getH(), o2.getH());
                }
            }
        };
    }
}
