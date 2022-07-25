import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GdDash {

	JFrame gdash;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JTable list;
	String value="";
	String id, name,email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GdDash window = new GdDash();
					window.gdash.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GdDash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		gdash = new JFrame();
		gdash.getContentPane().setForeground(Color.BLACK);
		gdash.setForeground(Color.BLACK);
		gdash.setTitle("STUDENT PERFORMANCE TRACKER - gdash(Guardian)");
		gdash.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		gdash.setBounds(325, 150, 1280, 720);
		gdash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gdash.getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setForeground(Color.BLACK);
		topPanel.setBounds(0, 0, 1264, 53);
		gdash.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setForeground(Color.BLACK);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gdash.dispose();
				MainMenu mm = new MainMenu();
				mm.Menu.setVisible(true);
			}
		});
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		lblBack.setBounds(41, 11, 104, 31);
		topPanel.add(lblBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 52, 1264, 629);
		gdash.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbltitle = new JLabel("CHILD INFORMATION");
		lbltitle.setBounds(105, 56, 410, 38);
		panel.add(lbltitle);
		lbltitle.setForeground(Color.BLACK);
		lbltitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 33));
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scroll.setBounds(105, 105, 1049, 421);
		panel.add(scroll);
		
		list = new JTable();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				value = (String) list.getValueAt(list.getSelectedRow(), 0);
			}
		});
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		list.setRowHeight(28);
		scroll.setViewportView(list);
		list.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STUDENT ID", "STUDENT NAME", "EMAIL"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		list.getColumnModel().getColumn(1).setPreferredWidth(93);
		
		JTableHeader header = new JTableHeader();
		header = list.getTableHeader();
		Font head = new Font("Segoe UI Black", Font.PLAIN, 25);
		header.setFont(head);
		header.setForeground(Color.BLACK);
		
		JButton btnAddStudent = new JButton("Add Child");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(gdash, "Student ID:", "Add a Student", JOptionPane.OK_CANCEL_OPTION);
				if(input.equals("")) {             
					JOptionPane.showMessageDialog(gdash,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
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
								JOptionPane.showMessageDialog(gdash,"Please input a Student ID","Field REQUIRED",JOptionPane.ERROR_MESSAGE);
							}
		                    else if (studid!=rs.getInt("student_id")){
		                    	JOptionPane.showMessageDialog(gdash,"There is no Student with that ID in the record","Record Error",JOptionPane.ERROR_MESSAGE);
		                    }
		                    else{
								Class.forName("com.mysql.jdbc.Driver");
								connect = DriverManager.getConnection(con,user, pass);
		                    	ps = connect.prepareStatement("insert into guardianstudent(guardian_id,student_id) values(?,?)");
		                    	ps.setInt(1,Login.id);
		                    	ps.setInt(2,studid);
		                    	ps.executeUpdate();
		                    	
		                    	JOptionPane.showMessageDialog(gdash,"Student "+studid+" has been added as your child","Success", JOptionPane.INFORMATION_MESSAGE);
		                    	
		            			connect = DriverManager.getConnection(con,user, pass);
		            			ps = connect.prepareStatement("select gs.student_id, concat(fname,\" \",left(midname,1),\".\",\" \",surname) as name,email\r\n"
		            					+ "from student s inner join guardianstudent gs on s.student_id = gs.student_id where guardian_id=?");
		            			ps.setInt(1,Login.id);
		            			rs = ps.executeQuery();
		            			DefaultTableModel model = (DefaultTableModel)list.getModel();
		            			while (rs.next()) {
		            				id = rs.getString(1);
		            				name = rs.getString("name");
		            				email = rs.getString("email");
		            				String[] row = {id,name,email};
		            				model.addRow(row);
		            			}
		                    	
		                    }	
					}catch(ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
	                        JOptionPane.showMessageDialog(gdash,e1,"Error",JOptionPane.ERROR_MESSAGE);
							java.util.logging.Logger.getLogger(GdDash.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
                }
			}
		});
		btnAddStudent.setIcon(new ImageIcon("img\\addStud.png"));
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnAddStudent.setBounds(798, 537, 166, 38);
		panel.add(btnAddStudent);
		
		JButton btnDeleteStudent = new JButton("Delete Child");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (value.equals("")) {
					JOptionPane.showMessageDialog(gdash, "No Record Selected", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(con,user, pass);
						ps = connect.prepareStatement("delete from guardianstudent where student_id=?");
						int nValue=Integer.parseInt(value);
						
						ps.setInt(1,nValue);
						ps.executeUpdate();
						
						JOptionPane.showMessageDialog(gdash,"Student "+nValue+" has been deleted here as your child. Add your child now to see how they perform in class","Deletion Success", JOptionPane.INFORMATION_MESSAGE);
						
						connect = DriverManager.getConnection(con,user, pass);
            			ps = connect.prepareStatement("select gs.student_id, concat(fname,\" \",left(midname,1),\".\",\" \",surname) as name,email\r\n"
            					+ "from student s inner join guardianstudent gs on s.student_id = gs.student_id where guardian_id=?");
            			ps.setInt(1,Login.id);
            			rs = ps.executeQuery();
						DefaultTableModel model = (DefaultTableModel)list.getModel();
						while (rs.next()) {
							id = rs.getString(1);
							name = rs.getString("name");
							email = rs.getString("email");
							String[] row = {id,name,email};
							model.addRow(row);
						}
						

					}catch(Exception e1) {
			            JOptionPane.showMessageDialog(null,e1);
						java.util.logging.Logger.getLogger(GdDash.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
					}
				}
			}
		});
		btnDeleteStudent.setIcon(new ImageIcon("img\\deleteStud.png"));
		btnDeleteStudent.setForeground(Color.BLACK);
		btnDeleteStudent.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnDeleteStudent.setBounds(988, 537, 166, 38);
		panel.add(btnDeleteStudent);

	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select gs.student_id, concat(fname,\" \",left(midname,1),\".\",\" \",surname) as name,email\r\n"
					+ "from student s inner join guardianstudent gs on s.student_id = gs.student_id where guardian_id=?");
			ps.setInt(1,Login.id);
			rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel)list.getModel();
			while (rs.next()) {
				id = rs.getString(1);
				name = rs.getString("name");
				email = rs.getString("email");
				String[] row = {id,name,email};
				model.addRow(row);
			}

	}catch(Exception e1) {
            JOptionPane.showMessageDialog(null,e1);
			java.util.logging.Logger.getLogger(GdDash.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}
	}
}
