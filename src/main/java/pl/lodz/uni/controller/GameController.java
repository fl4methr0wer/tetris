package pl.lodz.uni.controller;

import pl.lodz.uni.model.Pile;
import pl.lodz.uni.model.Tetromino;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameController {

    private final Timer timer;
    private final GameStateListener userInterface;
    private Tetromino tetromino = new Tetromino();
    private final Pile pile;

    public GameController(GameStateListener userInterface, Pile pile) {
        this.userInterface = userInterface;
        this.pile = pile;
        this.timer = new Timer(1000, this::onTimerTick);
        this.timer.start();
    }

    private void onTimerTick(ActionEvent actionEvent) {
        System.out.println("TIMER TICK");
    }

    private void moveTetrominoDown() {

    }







}
