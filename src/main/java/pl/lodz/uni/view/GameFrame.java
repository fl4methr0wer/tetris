package pl.lodz.uni.view;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GridPanel gridPanel;
    private ScorePanel scorePanel;

    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //setLayout(new GridLayout(2, 1));
    }

    public void setGridPanel(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        add(gridPanel, BorderLayout.SOUTH);
        //setContentPane(gridPanel);
        pack();
    }

    public void setScorePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
        add(scorePanel, BorderLayout.NORTH);
        pack();
    }
}
