import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TeacherMainMenu {

	JFrame tcMenu;
	JLabel lblcount;
	String name;
	String id;
	int count;
	//variables for sql connection	
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null, connect2 = null;
	PreparedStatement ps = null, ps2 = null;
	ResultSet rs = null, rs2=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMainMenu window = new TeacherMainMenu();
					window.tcMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeacherMainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("SELECT * from teacher WHERE teacher_id=?");
			
			ps.setInt(1, Login.id);
			
			rs = ps.executeQuery();
			rs.next();
				name = (String.valueOf(rs.getString("surname")+", "+rs.getString("fname"))+" "+rs.getString("midname").substring(0,1)+". ");
				id = String.valueOf(rs.getInt("teacher_id"));
				
			ps2 = connect.prepareStatement("select count(*) as record_count from teacherstudent where teacher_id = ?");                                    
			ps2.setInt(1,Login.id);
				
			rs2=ps2.executeQuery();
			rs2.next();
			count = rs2.getInt("record_count");	
			}
			catch(ClassNotFoundException | SQLException e1) {
				java.util.logging.Logger.getLogger(TeacherMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);
			}		
		
		tcMenu = new JFrame();
		tcMenu.getContentPane().setForeground(Color.BLACK);
		tcMenu.setForeground(Color.BLACK);
		tcMenu.setResizable(false);
		tcMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		tcMenu.setTitle("STUDENT PERFORMANCE TRACKER - Main Menu (Teacher)");
		tcMenu.setBounds(325, 150, 1280, 720);
		tcMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tcMenu.getContentPane().setLayout(null);
		
		JPanel content = new JPanel();
		content.setForeground(Color.BLACK);
		content.setBounds(0, 0, 1264, 681);
		tcMenu.getContentPane().add(content);
		content.setLayout(null);
		
		JLabel lblGreetings = new JLabel("Teacher:");
		lblGreetings.setForeground(Color.BLACK);
		lblGreetings.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		lblGreetings.setBounds(73, 63, 179, 50);
		content.add(lblGreetings);
		
		JLabel teachName = new JLabel(name);
		teachName.setForeground(Color.BLACK);
		teachName.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		teachName.setBounds(239, 63, 665, 50);
		content.add(teachName);
		
		JLabel lblTeacherID = new JLabel("Teacher ID: ");
		lblTeacherID.setForeground(Color.BLACK);
		lblTeacherID.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblTeacherID.setBounds(73, 124, 141, 25);
		content.add(lblTeacherID);
		
		JLabel teachId = new JLabel(""+id);
		teachId.setForeground(Color.BLACK);
		teachId.setFont(new Font("Segoe UI", Font.ITALIC, 25));
		teachId.setBounds(224, 124, 254, 25);
		content.add(teachId);
		
		JLabel lblstudNum = new JLabel("Number of Students:");
		lblstudNum.setForeground(Color.BLACK);
		lblstudNum.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblstudNum.setBounds(73, 160, 264, 46);
		content.add(lblstudNum);
		
		lblcount = new JLabel(""+count);
		lblcount.setForeground(Color.BLACK);
		lblcount.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblcount.setBounds(340, 160, 90, 46);
		content.add(lblcount);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(tcMenu, "Do you really want to log out?", "Confirm Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(input==0) {
					tcMenu.dispose();
					Login login = new Login();
					login.Login.setVisible(true);
				}
			}
		});
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnLogout.setBounds(1074, 592, 119, 42);
		content.add(btnLogout);
		
		JButton btnStudInfo = new JButton("");
		btnStudInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tcMenu.dispose();
				StudInfo info = new StudInfo();
				info.studInfo.setVisible(true);
			}
		});
		btnStudInfo.setForeground(Color.BLACK);
		btnStudInfo.setIcon(new ImageIcon("img\\student.png"));
		btnStudInfo.setBounds(380, 234, 240, 240);
		content.add(btnStudInfo);
		
		JButton btnManageAcc = new JButton("");
		btnManageAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tcMenu.dispose();
				UpdateTeacher updTc = new UpdateTeacher();
				updTc.UpdateTc.setVisible(true);
			}
		});
		btnManageAcc.setForeground(Color.BLACK);
		btnManageAcc.setIcon(new ImageIcon("img\\account.png"));
		btnManageAcc.setBounds(630, 234, 240, 240);
		content.add(btnManageAcc);
		
		JLabel lblStudentInformation1 = new JLabel("STUDENT");
		lblStudentInformation1.setForeground(Color.BLACK);
		lblStudentInformation1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentInformation1.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblStudentInformation1.setBounds(390, 479, 220, 30);
		content.add(lblStudentInformation1);
		
		JLabel lblStudentInformation2 = new JLabel("INFORMATION");
		lblStudentInformation2.setForeground(Color.BLACK);
		lblStudentInformation2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentInformation2.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblStudentInformation2.setBounds(390, 505, 220, 30);
		content.add(lblStudentInformation2);
		
		JLabel lblManageAcc = new JLabel("MANAGE ACCOUNT");
		lblManageAcc.setForeground(Color.BLACK);
		lblManageAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageAcc.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblManageAcc.setBounds(640, 485, 220, 30);
		content.add(lblManageAcc);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(tcMenu, "Student ID:", "Add a Student", JOptionPane.OK_CANCEL_OPTION);
				if(input.equals("")) {             
					JOptionPane.showMessageDialog(tcMenu,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
				}
                else {
                	Integer studid = Integer.valueOf(input);
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(con,user, pass);
						ps = connect.prepareStatement("select * from student");
						rs = ps.executeQuery();
						rs.next();
		                    if(input.equals("")) {             
								JOptionPane.showMessageDialog(tcMenu,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
							}
		                    else if (studid!=rs.getInt("student_id")){
		                    	JOptionPane.showMessageDialog(tcMenu,"There is no Student with that ID in the record","Record Error",JOptionPane.ERROR_MESSAGE);
		                    }
		                    else{
								Class.forName("com.mysql.jdbc.Driver");
								connect = DriverManager.getConnection(con,user, pass);
		                    	ps = connect.prepareStatement("insert into teacherstudent(teacher_id,student_id) values(?,?)");
		                    	ps.setInt(1,Login.id);
		                    	ps.setInt(2,studid);
		                    	ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(tcMenu,"Student "+studid+" has been added to your class","Success", JOptionPane.INFORMATION_MESSAGE);
		                    	
		                    	ps2 = connect.prepareStatement("select count(*) as record_count from teacherstudent where teacher_id = ?");                                    
		            			ps2.setInt(1,Login.id);
		            				
		            			rs2=ps2.executeQuery();
		            			rs2.next();
		            			count = rs2.getInt("record_count");
		            			lblcount.setText(""+count);
		                    }	
					}catch(ClassNotFoundException | SQLException e1) {
	                        JOptionPane.showMessageDialog(tcMenu,e1,"Error",JOptionPane.ERROR_MESSAGE);
	                        java.util.logging.Logger.getLogger(TeacherMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null,e1); 
						}
                }
			}
		});
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.setIcon(new ImageIcon("img\\addStud.png"));		
		btnAddStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnAddStudent.setBounds(1019, 63, 175, 41);
		content.add(btnAddStudent);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(tcMenu, "Student ID:", "Delete a Student", JOptionPane.OK_CANCEL_OPTION);
                if(input.equals("")) {             
					JOptionPane.showMessageDialog(tcMenu,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
				}
                else {
                	Integer studid = Integer.valueOf(input);
                
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(con,user, pass);
						ps = connect.prepareStatement("select * from teacherstudent where teacher_id=?");
						
						ps.setInt(1,Login.id);
						
						rs = ps.executeQuery();
						rs.next();
						

		                    if (studid!=rs.getInt("student_id")){
		                    	JOptionPane.showMessageDialog(tcMenu,"There is no Student in this class with that ID","Record Error",JOptionPane.ERROR_MESSAGE);
		                    }
		                    else{
								Class.forName("com.mysql.jdbc.Driver");
								connect = DriverManager.getConnection(con,user, pass);
		                    	ps = connect.prepareStatement("delete from teacherstudent where student_id=?");
		                    	ps.setInt(1,studid);
		                    	ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(tcMenu,"Student "+studid+" has been deleted to your class","Success", JOptionPane.INFORMATION_MESSAGE);
		                    	ps2 = connect.prepareStatement("select count(*) as record_count from teacherstudent where teacher_id = ?");                                    
		            			ps2.setInt(1,Login.id);
		            				
		            			rs2=ps2.executeQuery();
		            			rs2.next();
		            			count = rs2.getInt("record_count");
		            			lblcount.setText(""+count);
		                    }	
					}catch(ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
	                        JOptionPane.showMessageDialog(tcMenu,"Database not connected","Database Error",JOptionPane.ERROR_MESSAGE);
							java.util.logging.Logger.getLogger(TeacherMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
				}
			}
		});
		btnDeleteStudent.setIcon(new ImageIcon("img\\deleteStud.png"));
		btnDeleteStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnDeleteStudent.setForeground(Color.BLACK);
		btnDeleteStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnDeleteStudent.setBounds(1019, 108, 175, 41);
		content.add(btnDeleteStudent);
		

	}
}
