package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;

public class CreateClass {
    private JTextField tfCname;
    private JTextField tfCoursecode;
    private JPanel mainPanelClass;
    private JTextField tfCoursename;
    private JButton createClassButton;
    private JButton updateClassButton;
    private JButton deleteClassButton;
    private JTextField tfSearch;
    private JButton searchClassButton;
    private JList classList;

    Connection con;
    PreparedStatement pst;


    public CreateClass() {

        connection();
        loadClassList();

        createClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cname,coursename,coursecode;

                cname = tfCname.getText();
                coursename = tfCoursename.getText();
                coursecode = tfCoursecode.getText();


                try {
                    pst = con.prepareStatement("insert into class(cname,coursename,coursecode)values(?,?,?)");
                    pst.setString(1, cname);
                    pst.setString(2, coursename);
                    pst.setString(3, coursecode);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"New Class Created!!!!");

                    tfCname.setText("");
                    tfCoursename.setText("");
                    tfCoursecode.setText("");
                    tfCname.requestFocus();

                    loadClassList();

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                    System.out.println(e1);
                }
            }
        });


        updateClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cname,coursename,coursecode;

                cname = tfCname.getText();
                coursename = tfCoursename.getText();
                coursecode = tfCoursecode.getText();

                try {

                    pst = con.prepareStatement("update class set cname = ?,coursename = ?,coursecode = ? where cname = ?");
                    pst.setString(1, cname);
                    pst.setString(2, coursename);
                    pst.setString(3, coursecode);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Class Updateeed!!!!!");

                    tfCname.setText("");
                    tfCoursename.setText("");
                    tfCoursecode.setText("");
                    tfCname.requestFocus();
                    tfSearch.setText("");


                    loadClassList();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


            }
        });


        deleteClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cname;

                cname = tfSearch.getText();


                try {
                    pst = con.prepareStatement("delete from class  where cname = ?");
                    pst.setString(1, cname);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeeed!!!!!");

                    tfCname.setText("");
                    tfCoursename.setText("");
                    tfCoursecode.setText("");
                    tfCname.requestFocus();
                    tfSearch.setText("");


                    loadClassList();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });


        searchClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String cname = tfSearch.getText();
                    pst = con.prepareStatement("select cname,coursename,coursecode from class where cname = ?");
                    pst.setString(1, cname);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String name = rs.getString(1);
                        String price = rs.getString(2);
                        String qty = rs.getString(3);

                        tfCname.setText(name);
                        tfCoursename.setText(price);
                        tfCoursecode.setText(qty);
                    }
                    else
                    {
                        tfCname.setText("");
                        tfCoursename.setText("");
                        tfCoursecode.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Class name ");

                    }
                }

                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });


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


    public void loadClassList(){

        try {
            pst = con.prepareStatement("select cname from class");
            ResultSet rs = pst.executeQuery();
            DefaultListModel model = new DefaultListModel();

            while(rs.next()){
                model.addElement(rs.getString("cname"));
            }

            classList.setModel(model);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("CreateClass");
        frame.setContentPane(new CreateClass().mainPanelClass);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
