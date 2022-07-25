import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class MainMenu {

	JFrame Menu;
	String name;
	String id;
	JLabel lblGreetings, lblID;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.Menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			if (Login.type.equals("Student")) {
				ps = connect.prepareStatement("SELECT * from student WHERE student_id=?");
			}
			else {
				ps = connect.prepareStatement("SELECT * from guardian WHERE guardian_id=?");
			}
			
			ps.setInt(1, Login.id);
			
			rs = ps.executeQuery();
			rs.next();
				name = (String.valueOf(rs.getString("surname")+", "+rs.getString("fname"))+" "+rs.getString("midname").substring(0,1)+". ");
				
				if (Login.type.equals("Student")) {
					id = String.valueOf(rs.getInt("student_id"));
				}
				else {
					id = String.valueOf(rs.getInt("guardian_id"));
				}
				
		}
			catch(ClassNotFoundException | SQLException e1) {
			java.util.logging.Logger.getLogger(TeacherMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);
		}
		
		Menu = new JFrame();
		Menu.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		Menu.setTitle("STUDENT PERFORMANCE TRACKER - Main Menu");
		Menu.setBounds(325, 150, 1280, 720);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu.getContentPane().setLayout(null);
		
		JPanel content = new JPanel();
		content.setBounds(0, 0, 1264, 681);
		Menu.getContentPane().add(content);
		content.setLayout(null);

		if (Login.type.equals("Student")) {
			lblGreetings = new JLabel("Student:");
		}
		else {
			lblGreetings = new JLabel("Guardian:");
		}
		lblGreetings.setBounds(73, 63, 200, 50);
		lblGreetings.setForeground(Color.BLACK);
		lblGreetings.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		content.add(lblGreetings);
		
		JLabel Name = new JLabel(name);
		Name.setForeground(Color.BLACK);
		Name.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		if (Login.type.equals("Student")) {
			Name.setBounds(250, 63, 620, 50);
		}
		else {
			Name.setBounds(278, 63, 620, 50);
		}

		content.add(Name);

		if (Login.type.equals("Student")) {
			lblID = new JLabel("Student ID: ");
		}
		else {
			lblID = new JLabel("Guardian ID: ");
		}
		lblID.setForeground(Color.BLACK);
		lblID.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
		lblID.setBounds(73, 124, 159, 25);
		content.add(lblID);
		
		JLabel ID = new JLabel(""+id);
		ID.setForeground(Color.BLACK);
		ID.setFont(new Font("Segoe UI", Font.ITALIC, 25));
		if (Login.type.equals("Student")) {
			ID.setBounds(220, 124, 240, 25);
		}
		else {
			ID.setBounds(237, 124, 240, 25);
		}
		content.add(ID);
		
		JButton btnDashboard = new JButton("");
		if (Login.type.equals("Student")) {
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.dispose();
				StudDash sdb = new StudDash();
				sdb.studDB.setVisible(true);
			}
		});
		}
		else {
			btnDashboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menu.dispose();
					GdDash gd = new GdDash();
					gd.gdash.setVisible(true);
				}
			});
		}
		btnDashboard.setIcon(new ImageIcon("img\\dashboard.png"));
		btnDashboard.setForeground(Color.BLACK);
		btnDashboard.setBounds(261, 219, 240, 240);
		content.add(btnDashboard);
		
		JLabel lblDashboard = new JLabel("DASHBOARD");
		lblDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashboard.setForeground(Color.BLACK);
		lblDashboard.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblDashboard.setBounds(271, 470, 220, 30);
		content.add(lblDashboard);

		JButton btnReports = new JButton("");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.dispose();
				Grades grd = new Grades();
				grd.Report.setVisible(true);
			}
		});
		btnReports.setIcon(new ImageIcon("img\\report.png"));
		btnReports.setForeground(Color.BLACK);
		btnReports.setBounds(511, 219, 240, 240);
		content.add(btnReports);
		
		JLabel lblGrades = new JLabel("GRADES");
		lblGrades.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrades.setForeground(Color.BLACK);
		lblGrades.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblGrades.setBounds(521, 470, 220, 30);
		content.add(lblGrades);
		
		JButton btnManageAcc = new JButton("");
		if (Login.type.equals("Student")) {
		btnManageAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.dispose();
				UpdateStudent us = new UpdateStudent();
				us.UpdateSt.setVisible(true);
			}
		});
		}
		else {
			btnManageAcc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Menu.dispose();
					UpdateGuardian ug = new UpdateGuardian();
					ug.UpdateGd.setVisible(true);
				}
			});
		}
		btnManageAcc.setIcon(new ImageIcon("img\\account.png"));
		btnManageAcc.setForeground(Color.BLACK);
		btnManageAcc.setBounds(761, 219, 240, 240);
		content.add(btnManageAcc);
		
		JLabel lblManageAcc = new JLabel("MANAGE ACCOUNT");
		lblManageAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageAcc.setForeground(Color.BLACK);
		lblManageAcc.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblManageAcc.setBounds(771, 470, 220, 30);
		content.add(lblManageAcc);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(Menu, "Do you really want to log out?", "Confirm Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(input==0) {
					Menu.dispose();
					Login login = new Login();
					login.Login.setVisible(true);
				}
			}
		});
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnLogout.setBounds(1082, 584, 119, 42);
		content.add(btnLogout);
		
	}
}
