package com.attendencemanagement;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class TakeAttendence {
    private JPanel mainPanel;
    private JTable attendenceTable;
    private JTextField tfDate;
    private JCheckBox isPresentCheckBox;
    private JButton updateButton;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    private String rno;


    public TakeAttendence() {
//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                boolean isPresent = isPresentCheckBox.isSelected();
//                String status ,date;
//
//                date = tfDate.getText();
//
//                if(isPresent){
//                    status = "P";
//                }
//                else{
//                    status = "F";
//                }
//
//
//
//
//                try {
//
//                    pst = con.prepareStatement("update currentclass set date = ?, astatus = ? where cname = ? and rno = ?");
//                    pst.setString(1, date);
//                    pst.setString(2,status);
//                    pst.setString();
//
//
//                    pst.executeUpdate();
//                    JOptionPane.showMessageDialog(null, "Class Updateeed!!!!!");
//
//                    tfCname.setText("");
//                    tfCoursename.setText("");
//                    tfCoursecode.setText("");
//                    tfCname.requestFocus();
//                    tfSearch.setText("");
//
//
//                }
//
//                catch (SQLException e1)
//                {
//                    e1.printStackTrace();
//                }
//
//            }
//        });


        attendenceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                attendenceTable.getSelectedRow();

            }
        });
    }


    public void fetch(){
        try{
            pst=con.prepareStatement("select * from currentclass");
            rs= pst.executeQuery();

            attendenceTable.setModel(DbUtils.resultSetToTableModel(rs));
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
