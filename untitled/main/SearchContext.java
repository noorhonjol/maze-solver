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

        start.f=0;
        start.g=0;
        start.h=start.calculateHeuristic();
        openList.add(start);

        while(!openList.isEmpty()){

            Cell current= openList.poll();
            List<Cell> successors= Utilities.getSuccessors(current,graph);
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



}