import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Dashboard {

	JFrame dashboard;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int count = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.dashboard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void update() {

	}
	
	private void initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select count(*) as record_count from teacherstudent where teacher_id = ?");                                    
			ps.setInt(1,Login.id);
			
			rs=ps.executeQuery();
			rs.next();
			count = rs.getInt("record_count");
			
        	
		}catch(ClassNotFoundException | SQLException e1) {
                                    JOptionPane.showMessageDialog(dashboard,"No registered student with that ID","Record Error",JOptionPane.ERROR_MESSAGE);
			java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}

		dashboard = new JFrame();
		dashboard.setResizable(false);
		dashboard.setTitle("STUDENT PERFORMANCE TRACKER - Dashboard (Teacher)");
		dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\JM Obispo\\eclipse-workspace\\AppDev\\img\\SP.png"));
		dashboard.setBounds(500, 350, 930, 202);
		dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dashboard.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 62, 914, 101);
		dashboard.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblstudNum = new JLabel("Number of Students:");
		lblstudNum.setForeground(Color.BLACK);
		lblstudNum.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblstudNum.setBounds(36, 25, 316, 46);
		mainPanel.add(lblstudNum);
		
		JLabel lblNewLabel = new JLabel(""+count);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblNewLabel.setBounds(362, 25, 111, 46);
		mainPanel.add(lblNewLabel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 914, 63);
		dashboard.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(dashboard, "Student ID:", "Add a Student", JOptionPane.OK_CANCEL_OPTION);
				if(input.equals("")) {             
					JOptionPane.showMessageDialog(dashboard,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
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
								JOptionPane.showMessageDialog(dashboard,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
							}
		                    else if (studid!=rs.getInt("student_id")){
		                    	JOptionPane.showMessageDialog(dashboard,"There is no Student with that ID in the record","Record Error",JOptionPane.ERROR_MESSAGE);
		                    }
		                    else{
								Class.forName("com.mysql.jdbc.Driver");
								connect = DriverManager.getConnection(con,user, pass);
		                    	ps = connect.prepareStatement("insert into teacherstudent(teacher_id,student_id) values(?,?)");
		                    	ps.setInt(1,Login.id);
		                    	ps.setInt(2,studid);
		                    	ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(dashboard,"Student "+studid+" has been added to your class","Success", JOptionPane.INFORMATION_MESSAGE);
		                    	
		                    }	
					}catch(ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
	                        JOptionPane.showMessageDialog(dashboard,e1,"Error",JOptionPane.ERROR_MESSAGE);
							java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
                }
			}
		});
		btnAddStudent.setIcon(new ImageIcon("img\\addStud.png"));
		btnAddStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnAddStudent.setBounds(502, 11, 171, 41);
		topPanel.add(btnAddStudent);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setForeground(Color.BLACK);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard.dispose();
				TeacherMainMenu tcm = new TeacherMainMenu();
				tcm.tcMenu.setVisible(true);
			}
		});
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblBack.setBounds(30, 21, 79, 24);
		topPanel.add(lblBack);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.setBounds(323, 11, 172, 41);
		topPanel.add(btnDeleteStudent);
		btnDeleteStudent.setForeground(Color.BLACK);
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(dashboard, "Student ID:", "Delete a Student", JOptionPane.OK_CANCEL_OPTION);
                if(input.equals("")) {             
					JOptionPane.showMessageDialog(dashboard,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
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
		                    	JOptionPane.showMessageDialog(dashboard,"There is no Student in this class with that ID","Record Error",JOptionPane.ERROR_MESSAGE);
		                    }
		                    else{
								Class.forName("com.mysql.jdbc.Driver");
								connect = DriverManager.getConnection(con,user, pass);
		                    	ps = connect.prepareStatement("delete from teacherstudent where student_id=?");
		                    	ps.setInt(1,studid);
		                    	ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(dashboard,"Student "+studid+" has been deleted to your class","Success", JOptionPane.INFORMATION_MESSAGE);
		                    	dashboard.revalidate();
		                    }	
					}catch(ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
	                        JOptionPane.showMessageDialog(dashboard,"Database not connected","Database Error",JOptionPane.ERROR_MESSAGE);
							java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
				}
			}
		});
		btnDeleteStudent.setIcon(new ImageIcon("img\\deleteStud.png"));
		btnDeleteStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
	}
}
