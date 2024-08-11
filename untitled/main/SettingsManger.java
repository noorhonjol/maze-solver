package main;
import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.AlgorithmType;
import SearchStrategies.SearchStrategyFactory;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    public static void search(){
        CellPanel.resetCellPanels(false);

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


        while(tracePath.cellType != CellType.StartCell) {
            if(tracePath.cellType != CellType.GoalCell){
                SettingsManger.cellPanels.get(tracePath.getRow()).get(tracePath.getColumn()).setBackground(Color.pink);
            }
            tracePath=tracePath.parent;
            totalCost++;
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
    public static List<List<CellPanel>> getCellPanels() {
        return cellPanels;
    }
    public static void setCellPanels(List<List<CellPanel>> cellPanels) {
        SettingsManger.cellPanels = cellPanels;
    }
    public static AlgorithmType getAlgorithmType() {
        return algorithmType;
    }
}