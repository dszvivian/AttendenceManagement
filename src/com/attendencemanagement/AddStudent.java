package com.attendencemanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddStudent {
    private JPanel mainPanel;
    private JTextField tfName;
    private JTextField tfSem;
    private JTextField tfBranch;
    private JLabel lbClassname;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField tfSearch;
    private JButton searchButton;


    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public static void main(String[] args,String cname) {
        JFrame frame = new JFrame("AddStudent");
        frame.setContentPane(new AddStudent(cname).mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    AddStudent(String cname){

        lbClassname.setText(cname);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name,classname,sem,branch;

                name = tfName.getText();
                sem = tfSem.getText();
                classname = lbClassname.getText();
                branch = tfBranch.getText();


                try {
                    connection();
                    pst = con.prepareStatement("insert into currentclass(name,cname,sem,branch)values(?,?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, classname);
                    pst.setString(3, sem);
                    pst.setString(4, branch);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"New Student Added!!!!");

                    tfName.setText("");
                    tfSem.setText("");
                    tfBranch.setText("");
                    tfName.requestFocus();
                    con.close();

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                    System.out.println(e1);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name,sem,branch;

                name = tfName.getText();
                sem = tfSem.getText();
                branch = tfBranch.getText();

                try {
                    connection();
                    pst = con.prepareStatement("update currentclass set name = ?,sem = ?,branch = ? where cname = ? and rno = ?");
                    pst.setString(1, name);
                    pst.setString(2, sem);
                    pst.setString(3, branch);
                    pst.setString(4, lbClassname.getText());
                    pst.setString(5, tfSearch.getText());

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Class Updateeed!!!!!");

                    tfName.setText("");
                    tfSem.setText("");
                    tfBranch.setText("");
                    tfName.requestFocus();
                    tfSearch.setText("");


                    con.close();
                    rs.close();

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rno;

                rno = tfSearch.getText();


                try {
                    connection();
                    pst = con.prepareStatement("delete from currentclass  where cname = ? and rno = ?");
                    pst.setString(1, lbClassname.getText());
                    pst.setString(2,rno);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleteeeeeed!!!!!");

                    tfName.setText("");
                    tfSem.setText("");
                    tfBranch.setText("");
                    tfName.requestFocus();
                    tfSearch.setText("");
                    con.close();
                    rs.close();

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection();
                    String rno = tfSearch.getText();
                    pst = con.prepareStatement("select name,cname,sem,branch from currentclass where cname = ? and rno = ?");
                    pst.setString(1, lbClassname.getText());
                    pst.setString(2, rno);
                    rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String a = rs.getString(1);//name
                        String b = rs.getString(4);//branch
                        String c = rs.getString(3);//sem

                        tfName.setText(a);
                        tfSem.setText(c);
                        tfBranch.setText(b);
                    }
                    else
                    {
                        tfName.setText("");
                        tfSem.setText("");
                        tfBranch.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Rno !!!!!!!!!");

                    }

                    con.close();
                    rs.close();
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


}
