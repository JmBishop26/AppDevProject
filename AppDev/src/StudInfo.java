import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class StudInfo {

	JFrame studInfo;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JTable list;
	public static String value="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudInfo window = new StudInfo();
					window.studInfo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		
		studInfo = new JFrame();
		studInfo.getContentPane().setForeground(Color.BLACK);
		studInfo.setForeground(Color.BLACK);
		studInfo.setTitle("STUDENT PERFORMANCE TRACKER - Student Information");
		studInfo.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\JM Obispo\\eclipse-workspace\\AppDev\\img\\SP.png"));
		studInfo.setBounds(325, 150, 1280, 720);
		studInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studInfo.getContentPane().setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setForeground(Color.BLACK);
		topPanel.setBounds(0, 0, 1264, 53);
		studInfo.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setForeground(Color.BLACK);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				studInfo.dispose();
				TeacherMainMenu tcm = new TeacherMainMenu();
				tcm.tcMenu.setVisible(true);
			}
		});
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		lblBack.setBounds(41, 11, 104, 31);
		topPanel.add(lblBack);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 52, 1264, 629);
		studInfo.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbltitle = new JLabel("STUDENTS' INFORMATION");
		lbltitle.setBounds(105, 30, 410, 38);
		panel.add(lbltitle);
		lbltitle.setForeground(Color.BLACK);
		lbltitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scroll.setBounds(105, 79, 1049, 421);
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
				"STUDENT ID", "STUDENT NAME"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JTableHeader header = new JTableHeader();
		header = list.getTableHeader();
		Font head = new Font("Segoe UI Black", Font.PLAIN, 25);
		header.setFont(head);
		header.setForeground(Color.BLACK);
		
		JButton btnMore = new JButton("More Info");
		btnMore.setForeground(Color.BLACK);
		btnMore.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (value.equals("")) {
					JOptionPane.showMessageDialog(studInfo, "No Record Selected", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					studInfo.dispose();
					MoreInfo more = new MoreInfo();
					more.mainFrame.setVisible(true);
				}
			}
		});
		btnMore.setBounds(1012, 511, 142, 38);
		panel.add(btnMore);
		list.getColumnModel().getColumn(1).setPreferredWidth(93);

	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select tc.student_id, concat(surname,\", \",fname,\" \",left(midname,1),\".\") as name\r\n"
					+ "from student s inner join teacherstudent tc on s.student_id = tc.student_id\r\n"
					+ "where tc.teacher_id = ?;");
			ps.setInt(1,Login.id);
			rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel)list.getModel();
			String id, name;
			while (rs.next()) {
				id = rs.getString(1);
				name = rs.getString("name");
				String[] row = {id,name};
				model.addRow(row);
			}

	}catch(Exception e1) {
            JOptionPane.showMessageDialog(null,e1);
			java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}
		
	}
}
