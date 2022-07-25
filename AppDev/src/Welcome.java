import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Welcome{
	JFrame welcome;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.welcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Welcome() {
		initialize();
	}

	private void initialize() {
		welcome = new JFrame();
		welcome.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		welcome.getContentPane().setBackground(new Color(176, 196, 222));
		welcome.setTitle("STUDENT PERFORMANCE TRACKER - Welcome");
		welcome.setResizable(false);
		welcome.setBounds(725, 375, 495, 217);
		welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcome.getContentPane().setLayout(null);
		
		JPanel title = new JPanel();
		title.setBackground(Color.DARK_GRAY);
		title.setBounds(0, 0, 484, 45);
		welcome.getContentPane().add(title);
		
		JLabel lblTitle = new JLabel("STUDENT PERFORMANCE TRACKER");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		title.add(lblTitle);
		
		JPanel create = new JPanel();
		create.setBackground(new Color(176, 196, 222));
		create.setBounds(0, 44, 479, 45);
		welcome.getContentPane().add(create);
		
		JLabel lblCreate = new JLabel("CREATE AN ACCOUNT FOR:");
		lblCreate.setForeground(Color.BLACK);
		lblCreate.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
		create.add(lblCreate);
		
		JButton btnStudent = new JButton("STUDENT");
		btnStudent.setForeground(Color.BLACK);
		btnStudent.setBackground(new Color(153, 255, 255));
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRegister student = new StudentRegister();
				student.studentRegistration.setVisible(true);
				welcome.dispose();
			}
		});
		
		btnStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnStudent.setBounds(10, 89, 155, 49);
		welcome.getContentPane().add(btnStudent);
		
		JButton btnGuardian = new JButton("GUARDIAN");
		btnGuardian.setForeground(Color.BLACK);
		btnGuardian.setBackground(new Color(204, 255, 204));
		btnGuardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardianRegister guardian = new GuardianRegister();
				guardian.GuardianRegistration.setVisible(true);
				welcome.dispose();
			}
		});
		btnGuardian.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnGuardian.setBounds(314, 89, 155, 49);
		welcome.getContentPane().add(btnGuardian);
		
		JButton Teacher = new JButton("TEACHER");
		Teacher.setForeground(Color.BLACK);
		Teacher.setBackground(new Color(255, 255, 204));
		Teacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherRegister teacher = new TeacherRegister();
				teacher.teacherRegistration.setVisible(true);
				welcome.dispose();
			}
		});
		Teacher.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		Teacher.setBounds(163, 89, 155, 49);
		welcome.getContentPane().add(Teacher);
		
		JPanel login = new JPanel();
		login.setBackground(new Color(176, 196, 222));
		login.setBounds(0, 141, 484, 37);
		welcome.getContentPane().add(login);
		
		JLabel lblLogin = new JLabel("Already have an account? Log In");
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				welcome.dispose();
				Login log = new Login();
				log.Login.setVisible(true);
			}
		});
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		login.add(lblLogin);
	}
}
