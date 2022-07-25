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

public class UpdateStudent {


	JFrame UpdateSt;
	private JTextField Surname;
	private JTextField Fname;
	private JTextField Mname;
	private JTextField Address;
	private JDateChooser Birthdate;
	private final ButtonGroup sexType = new ButtonGroup();
	private JRadioButton rbtn1;
	private JRadioButton rbtn2 = new JRadioButton("Female");
	private JTextField Cnum;
	private JTextField Email;
	private JPasswordField CfPword;
	private JPasswordField Pword;
	
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
					UpdateStudent window = new UpdateStudent();
					window.UpdateSt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		UpdateSt = new JFrame();
		UpdateSt.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		UpdateSt.setTitle("STUDENT PERFORMANCE TRACKER - Manage Account (Student)");
		UpdateSt.setResizable(false);
		UpdateSt.setBounds(470, 250, 975, 464);
		UpdateSt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new JPanel();
		content.setForeground(Color.BLACK);
		content.setBackground(new Color(176, 196, 222));
		content.setBounds(0, 119, 1264, 562);
		UpdateSt.getContentPane().add(content);
		content.setLayout(null);
		
		JLabel lblTeachersInformation = new JLabel("Manage Account");
		lblTeachersInformation.setForeground(Color.BLACK);
		lblTeachersInformation.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblTeachersInformation.setBounds(70, 27, 212, 37);
		content.add(lblTeachersInformation);		
		
		JLabel lblSname = new JLabel("Surname");
		lblSname.setForeground(Color.BLACK);
		lblSname.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblSname.setBounds(254, 105, 109, 24);
		content.add(lblSname); 
		
		Surname = new JTextField();
		Surname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Surname.setBounds(254, 133, 183, 25);
		content.add(Surname);
		Surname.setColumns(10);
		
		JLabel lblFname = new JLabel("First Name");
		lblFname.setForeground(Color.BLACK);
		lblFname.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblFname.setBounds(480, 105, 129, 24);
		content.add(lblFname);
		
		Fname = new JTextField();
		Fname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Fname.setBounds(480, 133, 183, 25);
		content.add(Fname);
		Fname.setColumns(10);
		
		JLabel lblMname = new JLabel("Middle Name");
		lblMname.setForeground(Color.BLACK);
		lblMname.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblMname.setBounds(705, 105, 164, 24);
		content.add(lblMname);
		
		Mname = new JTextField();
		Mname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Mname.setBounds(705, 133, 183, 25);
		content.add(Mname);
		Mname.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblAddress.setBounds(176, 182, 121, 24);
		content.add(lblAddress);
		
		Address = new JTextField();
		Address.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Address.setColumns(10);
		Address.setBounds(176, 210, 198, 25);
		content.add(Address);
		
		JLabel lblBdate = new JLabel("Birthdate");
		lblBdate.setForeground(Color.BLACK);
		lblBdate.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblBdate.setBounds(426, 182, 137, 24);
		content.add(lblBdate);
		
		Birthdate = new JDateChooser();
		Birthdate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Birthdate.setDateFormatString("yyyy-MM-dd");
		Birthdate.setBounds(426, 210, 155, 25);
		content.add(Birthdate);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setForeground(Color.BLACK);
		lblSex.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblSex.setBounds(649, 182, 46, 24);
		content.add(lblSex);
		
		rbtn1 = new JRadioButton("Male");
		rbtn1.setForeground(Color.BLACK);
		sexType.add(rbtn1);
		rbtn1.setActionCommand("Male");
		rbtn1.setVerticalAlignment(SwingConstants.TOP);
		rbtn1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rbtn1.setBackground(new Color(176, 196, 222));
		rbtn1.setBounds(649, 210, 60, 25);
		content.add(rbtn1);
		
		rbtn2 = new JRadioButton("Female");
		rbtn2.setForeground(Color.BLACK);
		sexType.add(rbtn2);
		rbtn2.setActionCommand("Female");
		rbtn2.setVerticalAlignment(SwingConstants.TOP);
		rbtn2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rbtn2.setBackground(new Color(176, 196, 222));
		rbtn2.setBounds(711, 210, 78, 25);
		content.add(rbtn2);
		
		JLabel lblCnum = new JLabel("Contact Number");
		lblCnum.setForeground(Color.BLACK);
		lblCnum.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblCnum.setBounds(176, 260, 166, 24);
		content.add(lblCnum);
		
		Cnum = new JTextField();
		Cnum.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Cnum.setBounds(176, 289, 166, 25);
		content.add(Cnum);
		Cnum.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblEmail.setBounds(70, 108, 84, 23);
		content.add(lblEmail);
		
		Email = new JTextField();
		Email.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Email.setBounds(70, 133, 142, 25);
		content.add(Email);
		Email.setEditable(false);
		Email.setColumns(10);
		
		JLabel lblPword = new JLabel("Password");
		lblPword.setForeground(Color.BLACK);
		lblPword.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblPword.setBounds(391, 261, 129, 23);
		content.add(lblPword);
		
		Pword = new JPasswordField();
		Pword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Pword.setBounds(621, 289, 183, 25);
		content.add(Pword);
		
		JLabel lblCfPword = new JLabel("Confirm Password");
		lblCfPword.setForeground(Color.BLACK);
		lblCfPword.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		lblCfPword.setBounds(621, 261, 189, 23);
		content.add(lblCfPword);
		
		CfPword = new JPasswordField();
		CfPword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		CfPword.setBounds(391, 288, 183, 26);
		content.add(CfPword);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                   java.util.Date date1 = Birthdate.getDate();
	                    if(Surname.getText().equals("")||Fname.getText().equals("")||Mname.getText().equals("")||Address.getText().equals("")||
	                       (date1 == null)||(sexType == null)||Cnum.getText().equals("")) {             
							JOptionPane.showMessageDialog(UpdateSt,"Please input your Information","All Fields are REQUIRED",JOptionPane.ERROR_MESSAGE);
						}
	                    else if (!new String(Pword.getPassword()).equals(new String(CfPword.getPassword()))){
							JOptionPane.showMessageDialog(UpdateSt,"Password should match","Error on Password",JOptionPane.ERROR_MESSAGE);
							Pword.setText("");
							CfPword.setText("");
						}
	                    else{
							Class.forName("com.mysql.jdbc.Driver");
		                    SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
							String d1 = Date_Format.format(Birthdate.getDate());
							String bday = null;
							bday=""+d1;
							connect = DriverManager.getConnection(con,user, pass);
							ps = connect.prepareStatement("update student set surname=?,fname=?,midname=?,"
									+ "address=?,birthdate=?,sex=?,contact_num=?,password=?,cfpword=?");
		                                                        
							ps.setString(1,Surname.getText());
							ps.setString(2,Fname.getText());
							ps.setString(3,Mname.getText());
							ps.setString(4,Address.getText());
							ps.setString(5,bday);
							ps.setString(6,sexType.getSelection().getActionCommand());
							ps.setString(7,Cnum.getText());
							ps.setString(8,new String(Pword.getPassword()));
							ps.setString(9,new String(CfPword.getPassword()));
							
							ps.executeUpdate();
						
						if(new String(Pword.getPassword()).equals(new String(CfPword.getPassword()))) {
								JOptionPane.showMessageDialog(UpdateSt,"Information Updated","Success", JOptionPane.INFORMATION_MESSAGE);
								Pword.setText("");
								CfPword.setText("");
							}
	                    }	
					}catch(ClassNotFoundException | SQLException e1) {
	                                                JOptionPane.showMessageDialog(UpdateSt,"Database not connected","Database Error",JOptionPane.ERROR_MESSAGE);
							java.util.logging.Logger.getLogger(TeacherRegister.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
			}
		});
		btnUpdate.setBackground(new Color(204, 255, 204));
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		btnUpdate.setBounds(362, 356, 132, 29);
		content.add(btnUpdate);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBackground(new Color(255, 51, 0));
		btnExit.setForeground(Color.WHITE);
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateSt.dispose();
				MainMenu menu = new MainMenu();
				menu.Menu.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Segoe UI Black", Font.PLAIN, 17));
		btnExit.setBounds(507, 356, 132, 29);
		content.add(btnExit);

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select * from student where student_id = ?");                      
			
			ps.setInt(1,Login.id);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Email.setText(rs.getString("email"));
				Surname.setText(rs.getString("surname"));
				Fname.setText(rs.getString("fname"));
				Mname.setText(rs.getString("midname"));
				Address.setText(rs.getString("address"));
				Birthdate.setDate(rs.getDate("birthdate"));
				{if (rs.getString("sex").equals("Male")) {
					rbtn1.setSelected(true);
				}
				else{
					rbtn2.setSelected(true);
				}
				}
				Cnum.setText(rs.getString("contact_num"));

			}
			

         }catch(ClassNotFoundException | SQLException e1) {
        	 			JOptionPane.showMessageDialog(UpdateSt,"Database not connected","Database Error",JOptionPane.ERROR_MESSAGE);
        	 			java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);
                               
			}
	}
}
