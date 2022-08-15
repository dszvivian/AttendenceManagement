package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Signup {
    private JPanel mainPanel;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfCpassword;
    private JButton registerButton;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;



    public Signup() {
        connection();

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username,password,cpassword;

                username = tfUsername.getText();
                password = tfPassword.getText();
                cpassword = tfCpassword.getText();


                try {
                    connection();

                    if(!password.equals(cpassword)){
                        JOptionPane.showMessageDialog(null,"Password and confirm Password Field Does not Match!!!!");
                    }
                    else{
                        pst = con.prepareStatement("insert into users(username,password)values(?,?)");

                        pst.setString(1, username);
                        pst.setString(2, password);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"New User Created!!!!");

                        tfUsername.setText("");
                        tfPassword.setText("");
                        tfCpassword.setText("");
                        tfUsername.requestFocus();


                        HomePage.main(null);

                        con.close();
                        rs.close();

                    }


                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                    System.out.println(e1);
                }


            }
        });
    }

    private void connection(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:atm.db");
            System.out.println("Connection SuccessFulllllll!.....");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Signup");
        frame.setContentPane(new Signup().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
