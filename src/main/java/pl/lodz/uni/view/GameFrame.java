package pl.lodz.uni.view;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GridPanel gridPanel;
    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setGridPanel(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        setContentPane(gridPanel);
        pack();
    }
}
