package main;
import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.AlgorithmType;
import SearchStrategies.SearchStrategyFactory;
import javax.swing.*;
import java.awt.*;
import java.util.List;
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

        if (startPoint==null){
            JOptionPane.showMessageDialog(null, "no start point");
            SettingsManger.costLabel.setText("cost is : undefined");

            return;
        }
        Cell tracePath = searchContext.search(graph, startPoint.getCell(), goals);

        if(tracePath==null){
            JOptionPane.showMessageDialog(null, "No path found");
            SettingsManger.costLabel.setText("cost is : undefined");
            return;
        }



        while (tracePath != SettingsManger.getStartPoint().getCell()) {
            if (tracePath.cellType != CellType.GoalCell) {
                SettingsManger.cellPanels.get(tracePath.getRow()).get(tracePath.getColumn()).setBackground(Color.pink);
            }
            tracePath = tracePath.getParent();
            totalCost++;
        }

        SettingsManger.costLabel.setText("cost is : " + totalCost);
    }


}
