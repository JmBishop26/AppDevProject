
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;

public class GuardianRegister {

	JFrame GuardianRegistration;
	private JTextField Surname;
	private JTextField Fname;
	private JTextField Mname;
	private JTextField Address;
	private final ButtonGroup sexType = new ButtonGroup();
	private JTextField Cnum;
	private JTextField Email;
	private JPasswordField CfPword;
	private JPasswordField Pword;
	private String regType = "Guardian";
	Welcome wc = new Welcome();
	
	//variables for sql connection	
		private static final String user = "root";
		private static final String pass = "root";
		private static final String con = "jdbc:mysql://localhost:3306/appdev";
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuardianRegister window = new GuardianRegister();
					window.GuardianRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuardianRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GuardianRegistration = new JFrame();
		GuardianRegistration.setTitle("STUDENT PERFORMANCE TRACKER - Guardian's Registration");
		GuardianRegistration.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		GuardianRegistration.setResizable(false);
		GuardianRegistration.setBounds(325, 150, 1280, 720);
		GuardianRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel title = new JPanel();
		title.setBackground(Color.DARK_GRAY);
		title.setBounds(0, 0, 1264, 119);
		GuardianRegistration.getContentPane().add(title);
		
		JLabel lblTitle = new JLabel("REGISTRATION FORM");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 80));
		title.add(lblTitle);
		
		JPanel content = new JPanel();
		content.setBackground(new Color(176, 196, 222));
		content.setBounds(0, 119, 1264, 562);
		GuardianRegistration.getContentPane().add(content);
		content.setLayout(null);
		
		JLabel lblGuardiansInformation = new JLabel("GUARDIAN'S INFORMATION");
		lblGuardiansInformation.setForeground(Color.BLACK);
		lblGuardiansInformation.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblGuardiansInformation.setBounds(123, 208, 360, 37);
		content.add(lblGuardiansInformation);
		JLabel lblSname = new JLabel("Surname");
		lblSname.setForeground(Color.BLACK);
		lblSname.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblSname.setBounds(316, 289, 109, 24);
		content.add(lblSname);
		
		Surname = new JTextField();
		Surname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Surname.setBounds(316, 317, 183, 25);
		content.add(Surname);
		Surname.setColumns(10);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setForeground(Color.BLACK);
		lblFname.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblFname.setBounds(542, 289, 129, 24);
		content.add(lblFname);
		
		Fname = new JTextField();
		Fname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Fname.setBounds(542, 317, 183, 25);
		content.add(Fname);
		Fname.setColumns(10);
		
		JLabel lblMname = new JLabel("Middle Name");
		lblMname.setForeground(Color.BLACK);
		lblMname.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblMname.setBounds(783, 289, 164, 24);
		content.add(lblMname);
		
		Mname = new JTextField();
		Mname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Mname.setBounds(783, 317, 183, 25);
		content.add(Mname);
		Mname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblAddress.setBounds(231, 367, 121, 24);
		content.add(lblAddress);
		
		Address = new JTextField();
		Address.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Address.setColumns(10);
		Address.setBounds(231, 395, 198, 25);
		content.add(Address);
		
		JLabel lblBdate = new JLabel("Birthdate");
		lblBdate.setForeground(Color.BLACK);
		lblBdate.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblBdate.setBounds(471, 367, 137, 24);
		content.add(lblBdate);
		
		final JDateChooser Birthdate = new JDateChooser();
		Birthdate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Birthdate.setDateFormatString("yyyy-MM-dd");
		Birthdate.setBounds(471, 395, 155, 25);
		content.add(Birthdate);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setForeground(Color.BLACK);
		lblSex.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblSex.setBounds(679, 367, 46, 24);
		content.add(lblSex);
		
		JRadioButton rbtn1 = new JRadioButton("Male");
		rbtn1.setForeground(Color.BLACK);
		sexType.add(rbtn1);
		rbtn1.setActionCommand("Male");
		rbtn1.setVerticalAlignment(SwingConstants.TOP);
		rbtn1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rbtn1.setBackground(new Color(176, 196, 222));
		rbtn1.setBounds(679, 395, 60, 25);
		content.add(rbtn1);
		
		JRadioButton rbtn2 = new JRadioButton("Female");
		rbtn2.setForeground(Color.BLACK);
		sexType.add(rbtn2);
		rbtn2.setActionCommand("Female");
		rbtn2.setVerticalAlignment(SwingConstants.TOP);
		rbtn2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rbtn2.setBackground(new Color(176, 196, 222));
		rbtn2.setBounds(741, 395, 78, 25);
		content.add(rbtn2);
		
		JLabel lblCnum = new JLabel("Contact Number");
		lblCnum.setForeground(Color.BLACK);
		lblCnum.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblCnum.setBounds(868, 367, 166, 24);
		content.add(lblCnum);
		
		Cnum = new JTextField();
		Cnum.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Cnum.setBounds(868, 394, 166, 25);
		content.add(Cnum);
		Cnum.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblEmail.setBounds(342, 448, 84, 23);
		content.add(lblEmail);
		
		Email = new JTextField();
		Email.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Email.setBounds(342, 473, 142, 25);
		content.add(Email);
		Email.setColumns(10);
		
		JLabel lblPword = new JLabel("Password");
		lblPword.setForeground(Color.BLACK);
		lblPword.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblPword.setBounds(531, 445, 129, 23);
		content.add(lblPword);
		
		Pword = new JPasswordField();
		Pword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Pword.setBounds(761, 473, 183, 25);
		content.add(Pword);
		
		JLabel lblCfPword = new JLabel("Confirm Password");
		lblCfPword.setForeground(Color.BLACK);
		lblCfPword.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblCfPword.setBounds(761, 445, 189, 23);
		content.add(lblCfPword);
		
		CfPword = new JPasswordField();
		CfPword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		CfPword.setBounds(531, 472, 183, 26);
		content.add(CfPword);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			try {
                   java.util.Date date1 = Birthdate.getDate();
                                        if(Surname.getText().equals("")||
                                                Fname.getText().equals("")||
                                                Mname.getText().equals("")||
                                                Address.getText().equals("")||
                                                (date1 == null)||
                                                (sexType == null)||
                                                Cnum.getText().equals("")||
                                                Email.getText().equals("")||
                                                new String(Pword.getPassword()).equals("")||
                                                new String(CfPword.getPassword()).equals("")) {
                                            
						JOptionPane.showMessageDialog(GuardianRegistration,"Please input your Information","All Fields are REQUIRED",JOptionPane.ERROR_MESSAGE);
					}
                    else if (!new String(Pword.getPassword()).equals(new String(CfPword.getPassword()))){
						JOptionPane.showMessageDialog(GuardianRegistration,"Password should match","Error on Password",JOptionPane.ERROR_MESSAGE);
					}
                    else{
					Class.forName("com.mysql.jdbc.Driver");
                    SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
					String d1 = Date_Format.format(Birthdate.getDate());
					String bday = null;
					bday=""+d1;
					connect = DriverManager.getConnection(con,user, pass);
					ps = connect.prepareStatement("insert into guardian(surname,fname,midname,"
							+ "address,birthdate,sex,contact_num,type,email,password,cfpword) values(?,?,?,?,?,?,?,?,?,?,?)");
                                                        
					ps.setString(1,Surname.getText());
					ps.setString(2,Fname.getText());
					ps.setString(3,Mname.getText());
					ps.setString(4,Address.getText());
					ps.setString(5,bday);
					ps.setString(6,sexType.getSelection().getActionCommand());
					ps.setString(7,Cnum.getText());
					ps.setString(8,regType);
					ps.setString(9,Email.getText());
					ps.setString(10,new String(Pword.getPassword()));
					ps.setString(11,new String(CfPword.getPassword()));
					
					ps.executeUpdate();
					
					if(new String(Pword.getPassword()).equals(new String(CfPword.getPassword()))) {
							
							JOptionPane.showMessageDialog(GuardianRegistration,"Register Complete","Success", JOptionPane.INFORMATION_MESSAGE);
							Surname.setText("");
							Fname.setText("");
							Mname.setText("");
							Address.setText("");
							Birthdate.setDate(null);
							sexType.clearSelection();
							Cnum.setText("");
							Email.setText("");
							Pword.setText("");
							CfPword.setText("");
							GuardianRegistration.dispose();
							wc.welcome.setVisible(true);
						}
                    }	
				}catch(ClassNotFoundException | SQLException e1) {
                                          if(e1 instanceof java.sql.SQLIntegrityConstraintViolationException){
                                                JOptionPane.showMessageDialog(GuardianRegistration,"Email is already taken!","Duplicate E-mail/Username",JOptionPane.ERROR_MESSAGE);
                                          }
                                          else{
                                                JOptionPane.showMessageDialog(GuardianRegistration,"Database not connected","Database Error",JOptionPane.ERROR_MESSAGE);
						java.util.logging.Logger.getLogger(GuardianRegister.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);
                                          }
					}


}
		});
		btnRegister.setBackground(new Color(204, 255, 204));
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		btnRegister.setBounds(413, 540, 132, 29);
		content.add(btnRegister);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Surname.setText("");
				Fname.setText("");
				Mname.setText("");
				Address.setText("");
				Birthdate.setCalendar(null);
				sexType.clearSelection();
				Cnum.setText("");
				Email.setText("");
				Pword.setText("");
				CfPword.setText("");
			}
		});
		btnReset.setBackground(new Color(153, 255, 255));
		btnReset.setForeground(Color.BLACK);
		btnReset.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		btnReset.setBounds(577, 540, 132, 29);
		content.add(btnReset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBackground(new Color(255, 51, 0));
		btnExit.setForeground(Color.WHITE);
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardianRegistration.dispose();
				Welcome wc = new Welcome();
				wc.welcome.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		btnExit.setBounds(741, 540, 132, 29);
		content.add(btnExit);

	}
}
