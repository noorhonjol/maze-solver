package main;
import javax.swing.*;

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
        hardResetBtn.addActionListener(e -> Utilities.resetCellPanels(true));
        return hardResetBtn;
    }

    private JButton getClearButton() {
        JButton hardResetBtn = new JButton("clear canvas");
        hardResetBtn.setSize(50, 50);
        hardResetBtn.addActionListener(e -> Utilities.resetCellPanels(false));
        return hardResetBtn;
    }

    private JButton getStartSearchButton(){
        JButton startSearchButton = new JButton("search");
        startSearchButton.setSize(50, 50);
        startSearchButton.addActionListener(e -> Utilities.search());
        return startSearchButton;
    }
}
