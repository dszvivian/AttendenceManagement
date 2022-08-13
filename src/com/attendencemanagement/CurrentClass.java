package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class CurrentClass {
    private JPanel mainPanel;
    private JLabel lbClassname;
    private JTable studentTable;
    private JButton addNewStudentButton;
    private JButton takeAttendenceButton;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;


    public static void main(String[] args,String classname) {
        JFrame frame = new JFrame(classname);
        frame.setContentPane(new CurrentClass(classname).mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    CurrentClass(String cname){
        this.lbClassname.setText(cname);
        connection();
        fetch();
        addNewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudent.main(null,lbClassname.getText());
            }
        });
        takeAttendenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void fetch(){
        try{
            pst=con.prepareStatement("select * from currentclass");
            rs= pst.executeQuery();

            studentTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void connection(){
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

}
