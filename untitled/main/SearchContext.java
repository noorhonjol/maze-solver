package main;

import SearchStrategies.AbstractSearchStrategy;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SearchContext {


    protected AbstractSearchStrategy searchStrategy;

    public SearchContext(AbstractSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public Cell search(List<List<CellPanel>> graph, Cell start, Set<Cell> goals){

        PriorityQueue<Cell> openList = searchStrategy.getOpenList();
        Set<Cell> closeList = new HashSet<>();

        start.setF(0);
        start.setG(0);
        start.setH(start.calculateHeuristic());
        openList.add(start);

        while(!openList.isEmpty()){

            Cell current= openList.poll();
            List<Cell> successors= getSuccessors(current,graph);
            for (Cell successor : successors){

                if(goals.contains(successor)){
                    successor.setParent(current);
                    return successor;
                }

                else if (closeList.contains(successor)) {
                    continue;
                }

                else if (!openList.contains(successor) || current.getG()+1 < successor.getG()) {
                    successor.setG(current.getG()+1);
                    successor.setH(successor.calculateHeuristic());
                    successor.setF(successor.getG() + successor.getH());
                    successor.setParent(current);

                    if (!openList.contains(successor)) {
                        openList.add(successor);

                        SettingsManger.cellPanels.get(successor.getRow()).get(successor.getColumn()).setBackground(Color.yellow);


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
    private List<Cell> getSuccessors(Cell current,List<List<CellPanel>> graph) {
        List<Cell> successors = new ArrayList<>();
        int row = current.getRow();
        int col = current.getColumn();


        if (col-1 >= 0) successors.add(graph.get(row).get(col - 1).getCell());    // left
        if (col+1 <= SettingsManger.width-1) successors.add(graph.get(row).get(col + 1).getCell());    // right
        if (row-1 >= 0) successors.add(graph.get(row - 1).get(col).getCell());    // top
        if (row+1 <= SettingsManger.height - 1) successors.add(graph.get(row + 1).get(col).getCell());    // bottom


        successors.removeIf(cell -> cell.cellType==main.CellType.BlockCell);

        return successors;
    }


}