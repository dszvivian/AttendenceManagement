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
    private JLabel lbRno;
    private JLabel lbName;
    private JTextField tfRno;

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    private String rno;


    public TakeAttendence(String data) {
        connection();
        fetch(data);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isPresent = isPresentCheckBox.isSelected();
                String name,astatus ,date,rno,cname;

                date = tfDate.getText();
                name = lbName.getText();
                rno = lbRno.getText();
                cname = data;


                if(isPresent){
                    astatus = "P";
                }
                else{
                    astatus = "F";
                }


                try {

                    pst = con.prepareStatement("insert into classattendence(rno,name,cname,date,astatus)values(?,?,?,?,?)");
                    pst.setString(1,rno);
                    pst.setString(2,name);
                    pst.setString(3,cname);
                    pst.setString(4,date);
                    pst.setString(5,astatus);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added ");

                    tfRno.setText("");

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });


        attendenceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try{


                    int row = attendenceTable.getSelectedRow();



                    String rno = (attendenceTable.getModel().getValueAt(row,0)).toString();
                    String name = (attendenceTable.getModel().getValueAt(row,1)).toString();

                    lbRno.setText(rno);
                    lbName.setText(name);


                }
                catch(Exception exception){
                    exception.printStackTrace();
                }


            }
        });


    }


    private void fetch(String data){
        try{
            connection();
            pst=con.prepareStatement("select rno , name from currentclass where cname = ?");
            pst.setString(1,data);
            rs= pst.executeQuery();

            attendenceTable.setModel(DbUtils.resultSetToTableModel(rs));

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


    public static void main(String[] args , String data) {
        JFrame frame = new JFrame("TakeAttendence");
        frame.setContentPane(new TakeAttendence(data).mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
