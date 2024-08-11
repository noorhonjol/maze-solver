package main;
import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.AlgorithmType;
import SearchStrategies.SearchStrategyFactory;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Utilities {
    public static void resetCellPanels(boolean hardReset) {
        for(List<CellPanel> cellPanels : SettingsManger.cellPanels){
            for(CellPanel cellPanel : cellPanels){
                if(hardReset){
                    cellPanel.getCell().setCellType(CellType.NormalCell);
                    SettingsManger.setStartPoint(null);
                    SettingsManger.removeAllGoals();

                }
                if(hardReset||cellPanel.getCell().getCellType()==CellType.NormalCell){
                    cellPanel.setBackground(Color.WHITE);
                }

            }
        }
    }
    public static void search(){
        Utilities.resetCellPanels(false);

        AlgorithmType chosenAlgorithmType = SettingsManger.getAlgorithmType();

        AbstractSearchStrategy chosenAlgorithmStrategy = SearchStrategyFactory.createSearchStrategy(chosenAlgorithmType);
        SearchContext searchContext = new SearchContext(chosenAlgorithmStrategy);

        Set<Cell> goals = SettingsManger.getGoalPoints()
                .stream()
                .map(CellPanel::getCell)
                .collect(Collectors.toSet());


        int totalCost=0;

        List<List<CellPanel>> graph = SettingsManger.getCellPanels();
        CellPanel startPoint = SettingsManger.getStartPoint();

        if (Objects.isNull(startPoint)){
            JOptionPane.showMessageDialog(null, "no start point");
            return;
        }
        Cell tracePath = searchContext.search(graph, startPoint.getCell(), goals);

        if(tracePath==null){
            JOptionPane.showMessageDialog(null, "No path found");
            return;
        }


        while(tracePath != SettingsManger.getStartPoint().getCell()) {
            if(tracePath.cellType != CellType.GoalCell){
                SettingsManger.cellPanels.get(tracePath.getRow()).get(tracePath.getColumn()).setBackground(Color.pink);
            }
            tracePath=tracePath.parent;
            totalCost++;
        }

        SettingsManger.costLabel.setText("cost is : " + (totalCost>0?totalCost:"undefined"));
    }
    public static List<Cell> getSuccessors(Cell current,List<List<CellPanel>> graph) {
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
