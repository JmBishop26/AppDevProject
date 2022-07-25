import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Login {

	JFrame Login;
	private JTextField Email;
	private JPasswordField password;
	public static int id;
	public static String type;
	private final ButtonGroup loginType = new ButtonGroup();
	//variables for sql connection	
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
					Login window = new Login();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		Login = new JFrame();
		Login.getContentPane().setForeground(Color.BLACK);
		Login.setForeground(Color.BLACK);
		Login.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		Login.setTitle("STUDENT PERFORMANCE TRACKER - Log In");
		Login.setResizable(false);
		Login.setBounds(475, 225, 900, 570);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		
		JPanel title = new JPanel();
		title.setForeground(Color.BLACK);
		title.setBackground(Color.DARK_GRAY);
		title.setBounds(0, 0, 884, 73);
		Login.getContentPane().add(title);
		
		JLabel lblTitle = new JLabel("Log In to your Account");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 50));
		title.add(lblTitle);
		
		JPanel content = new JPanel();
		content.setForeground(Color.BLACK);
		content.setBackground(new Color(176, 196, 222));
		content.setBounds(0, 72, 884, 459);
		Login.getContentPane().add(content);
		content.setLayout(null);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setForeground(Color.BLACK);
		lblemail.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		lblemail.setBounds(277, 109, 62, 37);
		content.add(lblemail);
		
		Email = new JTextField();
		Email.setForeground(Color.BLACK);
		Email.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Email.setColumns(10);
		Email.setBounds(349, 110, 271, 31);
		content.add(Email);
		
		JLabel lblPword = new JLabel("Password");
		lblPword.setForeground(Color.BLACK);
		lblPword.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		lblPword.setBounds(230, 155, 109, 37);
		content.add(lblPword);
		
		password = new JPasswordField();
		password.setForeground(Color.BLACK);
		password.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		password.setBounds(349, 156, 271, 31);
		content.add(password);
		
		JLabel lblNewLabel = new JLabel("Type of Log In");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		lblNewLabel.setBounds(230, 203, 171, 31);
		content.add(lblNewLabel);
		
		JRadioButton rbtn1 = new JRadioButton("Student");
		loginType.add(rbtn1);
		rbtn1.setActionCommand("Student");
		rbtn1.setForeground(Color.BLACK);
		rbtn1.setBackground(new Color(176, 196, 222));
		rbtn1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rbtn1.setBounds(413, 209, 89, 23);
		content.add(rbtn1);
		
		JRadioButton rbtn2 = new JRadioButton("Teacher");
		loginType.add(rbtn2);
		rbtn2.setActionCommand("Teacher");
		rbtn2.setForeground(Color.BLACK);
		rbtn2.setBackground(new Color(176, 196, 222));
		rbtn2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rbtn2.setBounds(413, 235, 89, 23);
		content.add(rbtn2);
		
		JRadioButton rbtn3 = new JRadioButton("Guardian");
		loginType.add(rbtn3);
		rbtn3.setActionCommand("Guardian");
		rbtn3.setForeground(Color.BLACK);
		rbtn3.setBackground(new Color(176, 196, 222));
		rbtn3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		rbtn3.setBounds(413, 261, 89, 23);
		content.add(rbtn3);
		
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
                    char[] pword = password.getPassword();
                    String passString = new String(pword);
					if (loginType==null) {
						JOptionPane.showMessageDialog(Login,"Log in Type cannot be empty","Credentials Required",JOptionPane.ERROR_MESSAGE);
					}
					else if (Email.getText().equals("")||passString.equals("")) {
						JOptionPane.showMessageDialog(Login,"Email/Password are Required to Log In","Credentials Required",JOptionPane.ERROR_MESSAGE);
					}
					else {
							
							Class.forName("com.mysql.cj.jdbc.Driver");
							connect = DriverManager.getConnection(con,user, pass);
	                    
						if (loginType.getSelection().getActionCommand().equals("Student")) {
							ps = connect.prepareStatement("SELECT * from student WHERE email=? AND password=?");
						}
						else if (loginType.getSelection().getActionCommand().equals("Teacher")) {
							ps = connect.prepareStatement("SELECT * from teacher WHERE email=? AND password=?");
						}
						else if (loginType.getSelection().getActionCommand().equals("Guardian")) {
							ps = connect.prepareStatement("SELECT * from guardian WHERE email=? AND password=?");
						}
							
							ps.setString(1, Email.getText());
							ps.setString(2, passString);
							
							rs = ps.executeQuery();
	
								if (rs.next() == false) {
			                       JOptionPane.showMessageDialog(Login,"Email/Password is Incorrect","Log In Error",JOptionPane.ERROR_MESSAGE);
			                       Email.setText("");
			                       password.setText("");
			                    }
								else {                   
			                            JOptionPane.showMessageDialog(Login,"Login credentials matched","Log In Succesful",JOptionPane.INFORMATION_MESSAGE);
				                        Email.setText("");
				                        password.setText("");
				                        
				                        if (loginType.getSelection().getActionCommand().equals("Teacher")) {
				                        	Login.dispose();
				                        	id = rs.getInt(1);
				                        	TeacherMainMenu tcmenu = new TeacherMainMenu();
				                        	tcmenu.tcMenu.setVisible(true);
				                        }
				                        else if (loginType.getSelection().getActionCommand().equals("Student")||loginType.getSelection().getActionCommand().equals("Guardian")){
				                        	Login.dispose();
				                        	id = rs.getInt(1);
				                        	type = rs.getString("type");
				                        	MainMenu menu = new MainMenu();
				                        	menu.Menu.setVisible(true);
				                        }
				                        
			                         }
							}	
			        }
						catch(ClassNotFoundException | SQLException e1) {
						java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		btnLogin.setBackground(new Color(0, 0, 204));
		btnLogin.setBounds(398, 306, 115, 43);
		content.add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.dispose();
				Welcome wc = new Welcome();
				wc.welcome.setVisible(true);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI Black", Font.ITALIC, 15));
		btnBack.setBackground(new Color(255, 0, 51));
		btnBack.setBounds(32, 11, 89, 23);
		content.add(btnBack);
		
		

	}
}