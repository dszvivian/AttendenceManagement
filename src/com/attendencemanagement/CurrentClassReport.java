package com.attendencemanagement;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CurrentClassReport {
    private JPanel mainPanel;
    private JTextField tfClassname;
    private JButton getReportButton;
    private JTable currentclassattendenceTable;


    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;


    public CurrentClassReport() {
        getReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String classname = tfClassname.getText();
                fetch(classname);

            }
        });
    }

    private void fetch(String data){
        try{
            connection();
            pst=con.prepareStatement("select rno , name , date , astatus from classattendence where cname = ?");
            pst.setString(1,data);
            rs= pst.executeQuery();

            currentclassattendenceTable.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        JFrame frame = new JFrame("CurrentClassReport");
        frame.setContentPane(new CurrentClassReport().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
