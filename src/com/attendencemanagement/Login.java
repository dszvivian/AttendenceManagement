package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login {
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JPanel mainPanel;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;


    public Login() {


        lbCreatenewaccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Signup.main(null);

            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username,password;

                username = tfUsername.getText();
                password = tfPassword.getText();


                try {
                    connection();

                        pst = con.prepareStatement("select * from users where username = ? and password = ?");
                        pst.setString(1, username);
                        pst.setString(2, password);
                        rs= pst.executeQuery();

                        if(rs.next()){
                            username = rs.getString("username");
                            password = rs.getString("password");


                            JOptionPane.showMessageDialog(null,"Login successfull !!!!");



                            HomePage.main(null);
                        }






                        tfUsername.setText("");
                        tfPassword.setText("");
                        tfUsername.requestFocus();




                        con.close();
                        rs.close();




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
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton loginButton;
    private JLabel lbForgotpassword;
    private JLabel lbCreatenewaccount;
}
