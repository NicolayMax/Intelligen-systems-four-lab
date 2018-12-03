package ru.bestcoders.aicarsuperracing.gui;

import ru.bestcoders.aicarsuperracing.engine.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;

public class LogWindow extends JFrame {
    private JTextArea textArea;
    private JPanel buttonPanel;
    private JButton startEdu;
    private JButton stopEdu;
    private JButton execute;
    private Logger logger;
    private GameEngine ge;

    public void setGE(GameEngine ge){
        this.ge = ge;
    }

    public LogWindow() {
        logger = Logger.getLogger("main");
        textArea = new JTextArea();
        buttonPanel = new JPanel();
        startEdu = new JButton("Начать обучение");
        stopEdu = new JButton("Приостановить/возобновить обучение");
        execute = new JButton("Запуск обуч.модели");
        textArea.setEditable(false);
        setTitle("Log");
        setSize(600, 300);
        startEdu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStartEduPress();
            }
        });
        stopEdu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStopEduPress();
            }
        });
        execute.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onExecutePress();
            }
        });
        buttonPanel.add(startEdu);
        buttonPanel.add(stopEdu);
        buttonPanel.add(execute);
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea));
        setVisible(true);
    }

    public void showInfo(String data) {
        textArea.append(data);
        textArea.setCaretPosition(textArea.getDocument().getLength());
        this.validate();
    }

    private void onStopEduPress() {
        ge.pauseLearning();
    }

    private void onStartEduPress() {
        logger.info("Обучение запущено");
        ge.startLearning();
    }

    private void onExecutePress() {
        logger.info("Выполнение запущено");
        ge.predictedRun();
    }
}
