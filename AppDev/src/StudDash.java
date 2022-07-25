import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudDash {

	JFrame studDB;
	
	String gname=null, gemail=null, gnum=null;
	String tname, temail,tnum;
	int gid=0, tid=0;
	
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
					StudDash window = new StudDash();
					window.studDB.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudDash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select gs.guardian_id, concat(fname,\" \",left(midname,1),\". \",surname) as name, email, contact_num\r\n"
											+ "from guardian g inner join guardianstudent gs on g.guardian_id = gs.guardian_id\r\n"
											+ "where student_id = ?");                                    
			ps.setInt(1,Login.id);
			rs=ps.executeQuery();
			if (rs.next()) {
				gid = rs.getInt("guardian_id");
				gname = rs.getString("name");
				gemail = rs.getString("email");
				gnum = rs.getString("contact_num");
			}
			else {
				JOptionPane.showMessageDialog(studDB, "Please add a Guardian. You can add them on the button at the top right corner.", "Please Connect With Your Guardian", JOptionPane.INFORMATION_MESSAGE);
			}

				
			connect2 = DriverManager.getConnection(con,user, pass);
			ps2 = connect2.prepareStatement("select tc.teacher_id, concat(fname,\" \",left(midname,1),\". \",surname) as name, email,contact_num\r\n"
											+ "from teacher t inner join teacherstudent tc on t.teacher_id = tc.teacher_id\r\n"
											+ "where student_id = ?");                                    
			ps2.setInt(1,Login.id);
			rs2=ps2.executeQuery();
			if (rs2.next()) {
				tname=rs2.getString("name");
				tid=rs2.getInt("teacher_id");
				temail = rs2.getString("email");
				tnum = rs2.getString("contact_num");				
			}
			else {
				JOptionPane.showMessageDialog(studDB, "Please enroll on your Teacher. Your teacher can add you as his/her student once you enrolled to them.", "Please Connect With A Teacher", JOptionPane.INFORMATION_MESSAGE);
			}	
		}catch(ClassNotFoundException | SQLException e1) {
			java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}
		studDB = new JFrame();
		studDB.getContentPane().setForeground(Color.BLACK);
		studDB.setForeground(Color.BLACK);
		studDB.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		studDB.setTitle("STUDENT PERFORMANCE TRACKER - Dashboard (Student)");
		studDB.setBounds(475, 225, 900, 570);
		studDB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studDB.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 884, 531);
		studDB.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setForeground(Color.BLACK);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				studDB.dispose();
				MainMenu menu = new MainMenu();
				menu.Menu.setVisible(true);
			}
		});
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblBack.setBounds(33, 11, 75, 35);
		panel.add(lblBack);
		
		JLabel lblGuardianInformation = new JLabel("GUARDIAN INFORMATION");
		lblGuardianInformation.setForeground(Color.BLACK);
		lblGuardianInformation.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lblGuardianInformation.setBounds(47, 52, 376, 43);
		panel.add(lblGuardianInformation);
		
		JLabel lblgName = new JLabel("Guardian: ");
		lblgName.setForeground(Color.BLACK);
		lblgName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblgName.setBounds(47, 92, 103, 35);
		panel.add(lblgName);
		
		final JLabel gName = new JLabel(gname);
		gName.setForeground(Color.BLACK);
		gName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		gName.setBounds(152, 92, 428, 35);
		panel.add(gName);
		
		JLabel lblgID = new JLabel("Guardian ID:");
		lblgID.setForeground(Color.BLACK);
		lblgID.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblgID.setBounds(47, 138, 123, 35);
		panel.add(lblgID);
		
		final JLabel gID = new JLabel(""+gid);
		gID.setForeground(Color.BLACK);
		gID.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		gID.setBounds(176, 138, 103, 35);
		panel.add(gID);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblEmail.setBounds(47, 184, 61, 35);
		panel.add(lblEmail);
		
		final JLabel gEmail = new JLabel(gemail);
		gEmail.setForeground(Color.BLACK);
		gEmail.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		gEmail.setBounds(118, 184, 253, 35);
		panel.add(gEmail);
		
		JLabel lblgNum = new JLabel("Contact Number:");
		lblgNum.setForeground(Color.BLACK);
		lblgNum.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblgNum.setBounds(47, 230, 146, 35);
		panel.add(lblgNum);
		
		final JLabel gNum = new JLabel(gnum);
		gNum.setForeground(Color.BLACK);
		gNum.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		gNum.setBounds(203, 226, 168, 43);
		panel.add(gNum);
		
		JLabel lblteacher = new JLabel("TEACHER INFORMATION");
		lblteacher.setForeground(Color.BLACK);
		lblteacher.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lblteacher.setBounds(47, 276, 376, 43);
		panel.add(lblteacher);
		
		JLabel lbltName = new JLabel("Teacher:");
		lbltName.setForeground(Color.BLACK);
		lbltName.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lbltName.setBounds(47, 330, 103, 35);
		panel.add(lbltName);
		
		JLabel tName = new JLabel(tname);
		tName.setForeground(Color.BLACK);
		tName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		tName.setBounds(135, 330, 428, 35);
		panel.add(tName);
		
		JLabel lbltID = new JLabel("Teacher ID:");
		lbltID.setForeground(Color.BLACK);
		lbltID.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lbltID.setBounds(47, 376, 95, 35);
		panel.add(lbltID);
		
		JLabel tID = new JLabel(""+tid);
		tID.setForeground(Color.BLACK);
		tID.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		tID.setBounds(148, 376, 103, 35);
		panel.add(tID);
		
		JLabel lbltEmail = new JLabel("Email:");
		lbltEmail.setForeground(Color.BLACK);
		lbltEmail.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lbltEmail.setBounds(47, 422, 61, 35);
		panel.add(lbltEmail);
		
		JLabel tEmail = new JLabel(temail);
		tEmail.setForeground(Color.BLACK);
		tEmail.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		tEmail.setBounds(118, 422, 236, 35);
		panel.add(tEmail);
		
		JLabel lbltNum = new JLabel("Contact Number:");
		lbltNum.setForeground(Color.BLACK);
		lbltNum.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lbltNum.setBounds(47, 464, 168, 35);
		panel.add(lbltNum);
		
		JLabel tNum = new JLabel(tnum);
		tNum.setForeground(Color.BLACK);
		tNum.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		tNum.setBounds(215, 464, 123, 35);
		panel.add(tNum);
		
		JButton btnAddGuardian = new JButton("Add Guardian");
		btnAddGuardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(studDB, "Guardian ID:", "Add Your Guardian", JOptionPane.OK_CANCEL_OPTION);
				if(input.equals("")) {             
					JOptionPane.showMessageDialog(studDB,"Please input Guardian ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
				}
                else {
                	Integer guid = Integer.valueOf(input);
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(con,user, pass);
						ps = connect.prepareStatement("select * from guardian");
						rs = ps.executeQuery();
						rs.next();
		                    if(input.equals("")) {             
								JOptionPane.showMessageDialog(studDB,"Please input Guardian ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
							}
		                    else if (guid!=rs.getInt("guardian_id")){
		                    	JOptionPane.showMessageDialog(studDB,"There is no Guardian with that ID in the record","Record Error",JOptionPane.ERROR_MESSAGE);
		                    }
		                    else{
								Class.forName("com.mysql.jdbc.Driver");
								connect = DriverManager.getConnection(con,user, pass);
		                    	ps = connect.prepareStatement("insert into guardianstudent(guardian_id,student_id) values(?,?)");
		                    	ps.setInt(1,guid);
		                    	ps.setInt(2,Login.id);
		                    	ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(studDB,"Successfully added "+guid+" as your Guardian","Success", JOptionPane.INFORMATION_MESSAGE);
		                    	
		                    	ps2 = connect.prepareStatement("select gs.guardian_id, concat(fname,\" \",left(midname,1),\". \",surname) as name, email, contact_num\r\n"
										+ "from guardian g inner join guardianstudent gs on g.guardian_id = gs.guardian_id\r\n"
										+ "where student_id = ?");                                          
		            			ps2.setInt(1,Login.id);
		            				
		            			rs2=ps2.executeQuery();
		            			rs2.next();
		            			gname = rs2.getString("name");
		            			gid = rs2.getInt("guardian_id");
		            			gemail = rs2.getString("email");
		            			gnum = rs2.getString("contact_num");

		            			gName.setText(gname);
		            			gID.setText(""+gid);
		            			gEmail.setText(gemail);
		            			gNum.setText(gnum);

		                    }	
					}catch(ClassNotFoundException | SQLException e1) {
	                        JOptionPane.showMessageDialog(studDB,e1,"Error",JOptionPane.ERROR_MESSAGE);
	                        java.util.logging.Logger.getLogger(TeacherMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null,e1); 
						}
                }
			}
		});
		btnAddGuardian.setForeground(Color.BLACK);
		btnAddGuardian.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnAddGuardian.setBounds(669, 52, 175, 41);
		panel.add(btnAddGuardian);
		
		JButton btnDeleteGuardian = new JButton("Delete Guardian");
		btnDeleteGuardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(studDB, "Do you realy want to delete "+gid+" as your guardian?", "Delete Guardian Relationship", JOptionPane.OK_CANCEL_OPTION);
                if(input==0) {             
	
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(con,user, pass);

		                ps = connect.prepareStatement("delete from guardianstudent where guardian_id=?");
		                ps.setInt(1,gid);
		                ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(studDB,"Successfully deleted "+gid+" as your guardian here. Add your new Guardian","Success", JOptionPane.INFORMATION_MESSAGE);
		            			gName.setText("");
		            			gID.setText("");
		            			gEmail.setText("");
		            			gNum.setText("");
		                    
					        
					}catch(ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
	                        JOptionPane.showMessageDialog(studDB,"Database not connected","Database Error",JOptionPane.ERROR_MESSAGE);
							java.util.logging.Logger.getLogger(StudDash.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
				}
			}
		});
		btnDeleteGuardian.setForeground(Color.BLACK);
		btnDeleteGuardian.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnDeleteGuardian.setBounds(669, 97, 175, 41);
		panel.add(btnDeleteGuardian);
	}
}
