package main;

import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.AlgorithmType;
import SearchStrategies.SearchStrategyFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SettingsManger {
    public static final int width=10;
    public static final int height=10;
    public static MazePanel mazePanel = new MazePanel(width,height);
    public static List<List<CellPanel>> cellPanels;
    private static AlgorithmType algorithmType= AlgorithmType.AStar;
    private static CellType selectedTypeForCell = CellType.StartCell;
    private static CellPanel startPoint = null ;
    private static final Set<CellPanel> goalPoints =new HashSet<>();
    public static JLabel costLabel;
    //todo : resetting a cell should remove it from this set if its a goal node
    public static AlgorithmType getAlgorithmType() {
        return algorithmType;
    }
    public static void search(){
        CellPanel.resetCellPanels(false);
        AlgorithmType chosenAlgorithmType = SettingsManger.getAlgorithmType();

        AbstractSearchStrategy chosenAlgorithmStrategy = SearchStrategyFactory.createSearchStrategy(chosenAlgorithmType);

        SearchContext searchContext = new SearchContext(chosenAlgorithmStrategy);
        Set<Cell> goals = SettingsManger.getGoalPoints().stream().map(CellPanel::getCell).collect(Collectors.toSet());
        //todo handle things being null, maybe use try catch (?)
        int totalCost=0;
        try {
            Cell tracePath = searchContext.search(SettingsManger.mazePanel.getGraph(), SettingsManger.getStartPoint().getCell(), goals);
            if(tracePath==null){
                JOptionPane.showMessageDialog(null, "No path found");
            }
            else while(tracePath.cellType != CellType.StartCell) {
                if(tracePath.cellType != CellType.GoalCell){
                    SettingsManger.cellPanels.get(tracePath.getRow()).get(tracePath.getColumn()).setBackground(Color.pink);
                }
                tracePath=tracePath.parent;
                totalCost++;

            }
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "There is no start point");
        }


        SettingsManger.costLabel.setText("cost is : " + (totalCost>0?totalCost:"undefined"));
    }
    public static void setAlgorithmType(AlgorithmType algorithmType) {
        SettingsManger.algorithmType = algorithmType;
    }

    public static CellType getSelectedTypeForCell() {
        return selectedTypeForCell;
    }

    public static void setSelectedTypeForCell(CellType selectedTypeForCell) {
        SettingsManger.selectedTypeForCell = selectedTypeForCell;
    }

    public static CellPanel getStartPoint() {
        return startPoint;
    }

    public static void setStartPoint(CellPanel startPoint) {
        SettingsManger.startPoint = startPoint;
    }
    public static Set<CellPanel> getGoalPoints() {
        return goalPoints;
    }
    public static void addToGoalPoints(CellPanel cellPanel) {
        SettingsManger.goalPoints.add(cellPanel);
    }

    public static void removeAllGoals() {
        SettingsManger.goalPoints.clear();
    }
}