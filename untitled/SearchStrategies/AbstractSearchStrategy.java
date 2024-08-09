package SearchStrategies;

import main.Cell;

import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class AbstractSearchStrategy {
    private final PriorityQueue<Cell> openList;

    public AbstractSearchStrategy() {
        this.openList = new PriorityQueue<>(getComparator());
    }

    protected abstract Comparator<Cell> getComparator();

    public PriorityQueue<Cell> getOpenList() {
        return openList;
    }
}
