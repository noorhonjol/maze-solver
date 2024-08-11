package main;

import javax.swing.*;

public class TypeOfCellChoicesPanel extends JPanel {
    JRadioButton startBtn,goalBtn,blockBtn,normalBtn;


    public TypeOfCellChoicesPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        startBtn = new JRadioButton("Start");
        goalBtn = new JRadioButton("Goal");
        blockBtn = new JRadioButton("Block");
        normalBtn = new JRadioButton("Normal");

        startBtn.setSelected(true);

        startBtn.addActionListener(e ->SettingsManger.setSelectedTypeForCell(CellType.StartCell));
        goalBtn.addActionListener(e -> SettingsManger.setSelectedTypeForCell(CellType.GoalCell));
        blockBtn.addActionListener(e -> SettingsManger.setSelectedTypeForCell(CellType.BlockCell));
        normalBtn.addActionListener(e -> SettingsManger.setSelectedTypeForCell(CellType.NormalCell));

        ButtonGroup bg = new ButtonGroup();
        bg.add(startBtn);
        bg.add(goalBtn);
        bg.add(blockBtn);
        bg.add(normalBtn);

        add(startBtn);
        add(goalBtn);
        add(blockBtn);
        add(normalBtn);
    }

}
