package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHomePage {
    private JButton facultyButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginHomePage");
        frame.setContentPane(new LoginHomePage().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    private JButton studentButton;
    private JPanel mainpanel;

    public LoginHomePage() {
        facultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.main(null);
            }
        });
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentClassReport.main(null);
            }
        });
    }
}
