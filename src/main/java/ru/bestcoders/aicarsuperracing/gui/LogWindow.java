package ru.bestcoders.aicarsuperracing.gui;

import javax.swing.*;

public class LogWindow extends JFrame {
    private JTextArea textArea;

    public LogWindow() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        setTitle("Log");
        setSize(400, 300);
        add(new JScrollPane(textArea));
        setVisible(true);
    }

    public void showInfo(String data) {
        textArea.append(data);
        this.validate();
    }
}
