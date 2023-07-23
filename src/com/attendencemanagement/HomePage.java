package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JPanel homePanel;
    private JButton createANewClassButton;
    private JButton checkAttendenceOfClassButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("HomePage");
        frame.setContentPane(new HomePage().homePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
    }

    public HomePage() {
        createANewClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateClass.main(null);
            }
        });
        checkAttendenceOfClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentClassReport.main(null);
            }
        });
    }
}
