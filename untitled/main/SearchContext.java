package main;

import SearchStrategies.AbstractSearchStrategy;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SearchContext {

    int width=25;
    int height=25;
    protected AbstractSearchStrategy searchStrategy;

    public SearchContext(AbstractSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public Cell search(List<List<Cell>> graph, Cell start, Set<Cell> goals){

        PriorityQueue<Cell> openList = searchStrategy.getOpenList();
        Set<Cell> closeList = new HashSet<>();

        start.f=0;
        start.g=0;
        start.h=start.calculateHeuristic();
        openList.add(start);

        while(!openList.isEmpty()){

            Cell current= openList.poll();
            List<Cell> successors= getSuccessors(current,graph);
            for (Cell successor : successors){

                if(goals.contains(successor)){
                    successor.parent=current;
                    return successor;
                }

                else if (closeList.contains(successor)) {
                    continue;
                }

                else if (!openList.contains(successor) || current.g+1 < successor.g) {
                    successor.g = current.g+1;
                    successor.h = successor.calculateHeuristic();
                    successor.f = successor.g + successor.h;
                    successor.parent = current;

                    if (!openList.contains(successor)) {
                        openList.add(successor);

                        Main.cellPanels.get(successor.getRow()).get(successor.getColumn()).setBackground(Color.yellow);


                    }
                    else{
                        openList.remove(successor);
                        openList.add(successor);
                    }
                }
            }
            closeList.add(current);

        }
        return null;

    }


    private List<Cell> getSuccessors(Cell current,List<List<Cell>> graph) {
        List<Cell> successors = new ArrayList<>();
        int row = current.getRow();
        int col = current.getColumn();

        // Add adjacent cells (up, down, left, right)
        if (col-1 >= 0) successors.add(graph.get(row).get(col - 1));    // left
        if (col+1 <= width-1) successors.add(graph.get(row).get(col + 1));    // right
        if (row-1 >= 0) successors.add(graph.get(row - 1).get(col));    // top
        if (row+1 <= height - 1) successors.add(graph.get(row + 1).get(col));    // bottom

        // Optionally handle diagonal movements if needed
        // if (diagonal movements are allowed) { add diagonal cells }

        // Filter out blocked cells
        successors.removeIf(cell -> cell.cellType==main.CellType.BlockCell);

        return successors;
    }

}