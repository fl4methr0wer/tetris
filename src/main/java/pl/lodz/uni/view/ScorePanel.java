package pl.lodz.uni.view;

import pl.lodz.uni.controller.ScoreUpdateListener;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements ScoreUpdateListener {
    private JLabel scoreLabel;

    public ScorePanel() {
        scoreLabel = new JLabel("Score: 0");
        //setLayout(new FlowLayout(FlowLayout.LEFT));
        add(scoreLabel);
        setPreferredSize(new Dimension(200, 50)); // Adjust dimensions as needed
    }

    @Override
    public void onScoreUpdate(int score) {
        scoreLabel.setText("Score: " + score);
    }
}

