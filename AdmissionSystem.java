import javax.swing.*;
import java.util.Arrays;
import java.sql.*;

import java.awt.*;
import java.awt.event.*;

public class AdmissionSystem implements ActionListener {
 
    
    JFrame f;
    JButton b1,b2,b3,b4,b5;
    
    AdmissionSystem () {
        f = new JFrame("Student Admission System");
        
        b1 = new JButton("Add a new Student");
        b1.setBounds(50, 50, 300, 40);
        b2 = new JButton("Update Student's details");
        b2.setBounds(50, 120, 300, 40);        
        b3 = new JButton("Search for a Student using Student Name");
        b3.setBounds(50, 190, 300, 40);
        b4 = new JButton("Promote all the Students");
        b4.setBounds(50, 260, 300, 40);     
        b5 = new JButton("Show Registered Students");
        b5.setBounds(50, 330, 300, 40);     
        
        
        
        f.add(b1);f.add(b2);f.add(b3);f.add(b4);f.add(b5);

        f.setLayout(null);
        f.setVisible(true);
        f.setSize(500,500);
        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);      
        b5.addActionListener(this);      
          
        
    }

    int i = 0;
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1){
            addAStudent();
        }
        if (ae.getSource() == b2){
            whatToUpdate();
        }
        if (ae.getSource() == b3){
            whoToSearch();
        }
        if (ae.getSource() == b4){
            toPromote();
        }
        if (ae.getSource() == b5){
            showStudents();
        }
    }

    private static void addAStudent() {
        JFrame f1 = new JFrame("Insert Details");
        JLabel l1,l2,l3,l4;
        JTextField t1,t2,t3,t4;
        JButton b1;
        l1 = new JLabel("Enter Name:");
        l1.setBounds(50, 20, 300, 40);
        t1 = new JTextField();
        t1.setBounds(50, 60, 300, 30);

        l2 = new JLabel("Enter Course:");
        l2.setBounds(50, 110, 300, 40);
        t2 = new JTextField();
        t2.setBounds(50, 150, 300, 30);
        
        l3 = new JLabel("Enter Year:");
        l3.setBounds(50, 200, 300, 40);
        t3 = new JTextField();
        t3.setBounds(50, 240, 300, 30);
        
        l4 = new JLabel("Enter Section:");
        l4.setBounds(50, 290, 300, 40);
        t4 = new JTextField();
        t4.setBounds(50, 330, 300, 30);

        b1 = new JButton("Submit");
        b1.setBounds(50, 380, 300, 30);

        f1.add(l1);
        f1.add(t1);
        f1.add(l2);
        f1.add(t2);
        f1.add(l3);
        f1.add(t3);
        f1.add(l4);
        f1.add(t4);
        f1.add(b1);


        f1.setLayout(null);
        f1.setVisible(true);
        f1.setSize(500, 500);
        f1.setResizable(true);
        f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae){ 

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false", "root", "root");
                    Statement stmt = con.createStatement();
                    String query = "insert into student values('" + t1.getText() + "','" + t2.getText() + "',"+ Integer.parseInt(t3.getText())+ ",'" + t4.getText() + "')";
                    stmt.executeUpdate(query);
                    con.close();
                    f1.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    private static void whatToUpdate() {
        JFrame f2 = new JFrame("What to Update");
        JLabel l1 = new JLabel("Select what do you want to update");
        l1.setBounds(100, 50, 200, 40);
        JRadioButton rb1,rb2,rb3;
        JButton b;
        rb1 = new JRadioButton("Course");
        rb1.setBounds(100, 110, 200, 40);
        
        rb2 = new JRadioButton("Year");
        rb2.setBounds(100, 170, 200, 40);

        rb3 = new JRadioButton("Section");
        rb3.setBounds(100, 230, 200, 40);

        f2.add(rb1);
        f2.add(rb2);
        f2.add(rb3);
        b = new JButton("click");
        b.setBounds(100, 280, 200, 30);
        b.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (rb1.isSelected()) {
                    JOptionPane.showMessageDialog(f2, "Course will be updated");
                    int id = 1;
                    updateStudentDetails(id);
                }
                if (rb2.isSelected()) {
                    JOptionPane.showMessageDialog(f2, "Year will be updated");
                    int id = 2;
                    updateStudentDetails(id);
                }
                if (rb3.isSelected()) {
                    JOptionPane.showMessageDialog(f2, "Section will be updated");
                    int id = 3;
                    updateStudentDetails(id);
                    
                }
                f2.dispose();
            }
        });
        f2.add(l1);
        f2.add(b);
        f2.setLayout(null);
        f2.setVisible(true);
        f2.setSize(500, 500);
        f2.setResizable(true);
        f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
    }
    

    private static void updateStudentDetails(int id) {


        JFrame f3 = new JFrame("Update Details");
        JLabel l1, l2;
        JTextField t1, t2;
        JButton b1;
        l1 = new JLabel("Enter Name:");
        l1.setBounds(50, 20, 300, 40);
        t1 = new JTextField();
        t1.setBounds(50, 60, 300, 30);

        l2 = new JLabel("Enter Value to be Updated:");
        l2.setBounds(50, 110, 300, 40);
        t2 = new JTextField();
        t2.setBounds(50, 150, 300, 30);

        b1 = new JButton("Submit");
        b1.setBounds(50, 380, 300, 30);

        f3.add(l1);
        f3.add(t1);
        f3.add(l2);
        f3.add(t2);
        f3.add(b1);

        f3.setLayout(null);
        f3.setVisible(true);
        f3.setSize(500, 500);
        f3.setResizable(true);
        f3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false", "root", "root");
                    Statement stmt = con.createStatement();

                    if (id == 1){
                        String r = "update student set" + " Course =" + "'" + t2.getText() + "'"+ "where Name =" + "'" + t1.getText() + "'";
                        stmt.executeUpdate(r);
                        con.close();
                    }
                    if (id == 2) {
                        String r = "update student set" + " Year =" + Integer.parseInt(t2.getText())+ "where Name ="+ "'" + t1.getText() + "'";
                        stmt.executeUpdate(r);
                        con.close();
                    }
                    if (id == 3) {
                        String r = "update student set" + " Section =" + "'" + t2.getText() + "'" + "where Name ="+ "'" + t1.getText() + "'";
                        stmt.executeUpdate(r);
                        con.close();
                    }
                    f3.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        JOptionPane.showMessageDialog(f3, "UPDATED!");
        
    }

    JFrame f4;
    public void whoToSearch() {
        f4 = new JFrame("Search a Student");
        JTextField t1 = new JTextField();
        JLabel l1 = new JLabel("Type Student's Name here:");
        JButton b = new JButton("Search");
        l1.setBounds(100, 100, 300, 40);
        t1.setBounds(100, 150, 300, 30);
        b.setBounds(150, 200, 100, 30);

        f4.add(l1);f4.add(t1);f4.add(b);

        f4.setLayout(null);
        f4.setVisible(true);
        f4.setSize(500, 500);
        f4.setResizable(true);
        f4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        b.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b){
                    String name = t1.getText();
                    searchForStudent(name);
                    f4.dispose();
                }
                
            }
        });

    }

    private void searchForStudent(String name) {
        JFrame f5;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student");
            while (rs.next()){
                if (rs.getString(1).equalsIgnoreCase(name)){
                    f5 = new JFrame("Result");
                    Object[][] rowData = new Object[][] { { rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4) } };
                    String[] columnNames = new String[] { "Name", "Course", "Year", "Section" };
                    JTable jt = new JTable(rowData, columnNames);
                    jt.setBounds(50, 40, 200, 300);
                    // jt.setFont(new Font("Serif", Font.BOLD, 20));
                    jt.setEnabled(false);
                    JScrollPane sp = new JScrollPane(jt);
                    f5.add(sp);
                    con.close();
                    f5.dispose();
                    f5.setSize(300, 100);
                    f5.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    f5.setVisible(true);
                }
            }
            JOptionPane.showMessageDialog(f4, "Sorry this Student is not Registered!");
            f4.dispose();
            con.close();
            
        }
        catch (Exception e){

        }
        
    }
    
    static JFrame f6;

    public static void toPromote() {
        JComboBox<String> cbSection;
        JButton b;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false", "root", "root");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select * from student;");
            int k;
            for (k = 0; r.next() == true ; k++);
            r.close();
            Object year[] = new Object[(int)k];
            String section[] = new String[(int)k];
            f6 = new JFrame("Promote");
            ResultSet r1 = s.executeQuery("select * from student;");
            for (int i = 0 ; r1.next()==true; i++){
                if (r1.getInt(3) > 0 && r1.getString(4) != null){
                year[i] = r1.getInt(3);
                section[i] = r1.getString(4);
            }

            }     
            r1.close();           
            s.close();
            c.close();
            Object[] y = Arrays.stream(year).distinct().toArray(Object[]::new);
            String[] sec = Arrays.stream(section).distinct().toArray(String[]::new);
            
            Arrays.sort(y);
            Arrays.sort(sec);
                    JComboBox<Object> cbYear = new JComboBox<Object>(y);
                    cbSection = new JComboBox<String>(sec);
                    cbYear.setBounds(100, 100, 90, 20);
                    cbSection.setBounds(250, 100, 90, 20);

                    b = new JButton("Promote");
                    b.setBounds(150, 150, 100, 30);

                    f6.add(b);
                    f6.add(cbYear);
                    f6.add(cbSection);
                    f6.setSize(400, 400);                    
                    f6.setLayout(null);
                    f6.setVisible(true);
                    f6.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    
                    cbYear.addActionListener(new ActionListener(){
                    
                        @Override
                        public void actionPerformed(ActionEvent ae) {

                            if(ae.getSource() == cbYear){

                                try{
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false", "root", "root");
                                    Statement s = c.createStatement();
                                    int y = (int) cbYear.getItemAt(cbYear.getSelectedIndex());
                                    ResultSet r = s.executeQuery("select * from student where year = "+ y +";");
                                    int k;
                                    for (k = 0; r.next() == true ; k++);
                                    r.close();

                                    String section[] = new String[k];
                                    ResultSet r1 = s.executeQuery("select * from student where year = " + y + ";");
                                    for (int i = 0; r1.next() == true; i++) {
                                if (r1.getInt(3) == y) {
                                    section[i] = r1.getString(4);

                                }
                               
                            }
                            r1.close();
                            s.close();                            
                            c.close();
                            String[] sec = Arrays.stream(section).distinct().toArray(String[]::new);
                            Arrays.sort(sec);
                            JComboBox<String> cbSection;
                            cbSection = new JComboBox<String>(sec);
                            cbSection.setBounds(250, 100, 90, 20);
                            f6.setVisible(false);
                            JFrame f6 = new JFrame("Promote");
                            b.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        Connection c = DriverManager.getConnection(
                                                "jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false",
                                                "root", "root");
                                        Statement s = c.createStatement();
                                        if (e.getSource() == b) {
                                            int year = (int) cbYear.getItemAt(cbYear.getSelectedIndex());
                                            String section = cbSection.getItemAt(cbSection.getSelectedIndex());
                                            int newYear = year + 1;
                                            String r = "update student set" + " Year = " + newYear + " where year = "
                                                    + year + " and section =" + "'" + section + "'";
                                            // System.out.println(r);
                                            s.executeUpdate(r);
                                            s.close();

                                            c.close();

                                        }
                                        JOptionPane.showMessageDialog(f6, "PROMOTED!");
                                        System.out.println();
                                        f6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        f6.dispose();
                                    } catch (Exception E) {
                                        E.printStackTrace();
                                    } finally {
                                        f6.dispose();
                                    }
                                }
                            });
                            f6.add(b);
                            f6.add(cbYear);
                            f6.add(cbSection);
                            f6.setSize(400, 400);
                            f6.setLayout(null);
                            f6.setVisible(true);
                            f6.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                } catch (Exception ex){
                                    ex.printStackTrace();
                                }
                               
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    JFrame f7;
    private void showStudents() {
        
        int m = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MSDB?autoReconnect=true&useSSL=false", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student");
            for (m = 0; rs.next() == true ; m++);
            rs.close();
            rs = stmt.executeQuery("select * from student");     
            Object[][] rowData = new Object[m][4];
            for (int j = 0; rs.next() == true; j++ ){
                for (int l = 0; l < 4; l++) {
                    if (l != 2){
                        rowData[j][l] = rs.getString(l+1);
                    }
                    else{
                        rowData[j][l] = rs.getInt(l+1);
                    }   

                }
                
            }
            rs.close();
            rs = stmt.executeQuery("select * from student");     
            
            while (rs.next()){
                m = m * 100;

                int s = m - 400;
                    f7 = new JFrame("!Registered Students!");

                    String[] columnNames = new String[] { "Name", "Course", "Year", "Section" };
                    JTable jt = new JTable(rowData, columnNames);
                    jt.setBounds(50, 100, 200, m);
                    jt.setRowHeight(30);
                    jt.setFont(new Font("Serif", Font.BOLD, 20));
                    jt.setEnabled(false);

                    JScrollPane sp = new JScrollPane(jt);

                    f7.add(sp);
                    con.close();
                    f7.dispose();
                    f7.setSize(500, s);
                    f7.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    f7.setVisible(true);
            }
        }
        catch (Exception e){

        }
        
    }
    public static void main(String[] args) throws Exception {
            new AdmissionSystem();
    }
}