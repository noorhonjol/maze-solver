package main;

import SearchStrategies.AbstractSearchStrategy;
import SearchStrategies.AlgorithmType;
import SearchStrategies.SearchStrategyFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

public class MainButtons extends JPanel {
    public MainButtons() {
        SettingsManger.costLabel=new JLabel("cost value");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(getStartSearchButton());
        add(SettingsManger.costLabel);
        add(getResetButton());
        add(getClearButton());
        add(getMazeGenerateButton());
        
    }

    private JButton getMazeGenerateButton() {
        JButton generateMaze = new JButton("generate maze");
        generateMaze.setSize(50, 50);
        generateMaze.addActionListener(e -> MazePanel.createRandomMaze());
        return generateMaze;
    }

    private JButton getResetButton() {
        JButton hardResetBtn = new JButton("reset");
        hardResetBtn.setSize(50, 50);
        hardResetBtn.addActionListener(e -> CellPanel.resetCellPanels(true));
        return hardResetBtn;
    }

    private JButton getClearButton() {
        JButton hardResetBtn = new JButton("clear canvas");
        hardResetBtn.setSize(50, 50);
        hardResetBtn.addActionListener(e -> CellPanel.resetCellPanels(false));
        return hardResetBtn;
    }

    private JButton getStartSearchButton(){
        JButton startSearchButton = new JButton("search");
        startSearchButton.setSize(50, 50);
        startSearchButton.addActionListener(e -> SettingsManger.search());
        return startSearchButton;
    }
}
