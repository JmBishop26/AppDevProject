import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Map;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Cursor;

public class MoreInfo {

	JFrame mainFrame;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null, connect2 = null, connect3 = null;
	PreparedStatement ps = null, ps2 = null, ps3 = null, ps4 = null;
	ResultSet rs = null, rs2 = null, rs3 = null, rs4 = null;
	int sId=0;
	int gid=0;
	String Name, gname, Address, sbirth, Email, gemail, sCnum, gcnum;
	int value = Integer.parseInt(StudInfo.value);
	private JTable table;
	private JTextField Ass1;
	private JTextField Ass1_Item;
	private JTextField Ass2;
	private JTextField Ass2_Item;
	private JTextField ass_score_total;
	private JTextField ass_item_total;
	private JTextField Quiz1;
	private JTextField Quiz1_Item;
	private JTextField Quiz2;
	private JTextField Quiz2_Item;
	private JTextField quiz_score_total;
	private JTextField quiz_item_total;
	private JTextField Exam_Item;
	private JTextField Exam_Score;
	private JTextField exam_score_total;
	private JTextField exam_item_total;
	private JTextField Ass_PScore;
	private JTextField Ass_WG;
	private JTextField Quiz_PScore;
	private JTextField Quiz_WG;
	private JTextField Exam_WG;
	private JTextField Exam_PScore;
	private JTextField initGrade;
	private JTextField transGrade;
	String period;

//variables for grades
	String grading;
	String [] choices = {"Choose Grading Period", "First Grading", "Second Grading", "Third Grading", "Fourth Grading"};
	private static final DecimalFormat df = new DecimalFormat("0.00");
	String ass1, ass2, ass1_item, ass2_item;//variables for assignment
	int ass_total_sc, ass_total_item;//variables for assignment
	double ass_percent, ass_weight;//variables for assignment
	String quiz1, quiz2, quiz1_item, quiz2_item;//variables for quiz
	int quiz_total_sc, quiz_total_item;//variables for quiz
	double quiz_percent, quiz_weight;//variables for quiz
	String exam, exam_item;//variables for exam
	int exam_total_sc, exam_total_item;//variables for exam
	double exam_percent, exam_weight;//variables for exam
	double initial; //variables for getting final grades
	int tgrade;//variables for getting final grades
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoreInfo window = new MoreInfo();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MoreInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	private void initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select * from student where student_id = ?");                                    
			ps.setInt(1,value);
			
			rs=ps.executeQuery();
			rs.next();
			sId = rs.getInt("student_id");
			Name = (String.valueOf(rs.getString("fname")+" "+rs.getString("midname").substring(0,1)+". "+rs.getString("surname")));;
			Address = rs.getString("address");
			sbirth = rs.getString("birthdate");
			Email = rs.getString("email");
			sCnum = rs.getString("contact_num");
			
			
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select gs.guardian_id, concat(fname,\", \",left(midname,1),\". \",surname) as name, email,contact_num\r\n"
											+ "from guardian g inner join guardianstudent gs on g.guardian_id = gs.guardian_id\r\n"
											+ "where student_id = ?");                                    
			ps.setInt(1,value);
			rs=ps.executeQuery();
			if (rs.next()) {
				gid = rs.getInt("guardian_id");
				gname = rs.getString("name");
				gemail = rs.getString("email");
				gcnum = rs.getString("contact_num");
			}
			else {
				JOptionPane.showMessageDialog(mainFrame, "This student is not connected to his/her guardian. Please advice him/her to do so.", "Advice This Student", JOptionPane.INFORMATION_MESSAGE);
			}
        	
		}catch(ClassNotFoundException | SQLException e1) {
                                    JOptionPane.showMessageDialog(mainFrame,"No registered student with that ID","Record Error",JOptionPane.ERROR_MESSAGE);
			java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}
		
		mainFrame = new JFrame();
		mainFrame.getContentPane().setForeground(Color.BLACK);
		mainFrame.setForeground(Color.BLACK);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		mainFrame.setTitle("STUDENT PERFORMANCE TRACKER - Student Information (More)");
		mainFrame.setBounds(325, 150, 1280, 720);
		mainFrame.getContentPane().setLayout(null);
		
		JPanel side = new JPanel();
		side.setForeground(Color.BLACK);
		side.setBounds(0, 0, 195, 681);
		mainFrame.getContentPane().add(side);
		side.setLayout(null);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setForeground(Color.BLACK);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.dispose();
				StudInfo sInfo = new StudInfo();
				sInfo.studInfo.setVisible(true);
			}
		});
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblBack.setBounds(41, 26, 92, 27);
		side.add(lblBack);
		
		final JPanel grades = new JPanel();
		grades.setForeground(Color.BLACK);
		grades.setBounds(194, 0, 1070, 681);
		mainFrame.getContentPane().add(grades);
		grades.setVisible(false);
		grades.setLayout(null);

//grades panel
				JLabel lblGrades = new JLabel("GRADES");
				lblGrades.setBounds(58, 33, 137, 36);
				lblGrades.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
				lblGrades.setForeground(Color.BLACK);
				grades.add(lblGrades);
				
				@SuppressWarnings({ "rawtypes" })
				final JComboBox comboBox = new JComboBox(choices);
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grading = comboBox.getSelectedItem().toString();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connect2 = DriverManager.getConnection(con,user, pass);
							
							
				        	if (grading.equals("First Grading")) {
				        		ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, firstGrade from grading1 where student_id = ?");
				        		ps2.setInt(1,value);
				            	rs2 = ps2.executeQuery();
				            	String a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
				    			DefaultTableModel model = (DefaultTableModel)table.getModel();
				    			while (rs2.next()) {
				    				a = rs2.getString(1);
				    				b = rs2.getString(2);
				    				c = rs2.getString(3);
				    				d = rs2.getString(4);
				    				f = rs2.getString(5);
				    				g = rs2.getString(6);
				    				h = rs2.getString(7);
				    				i = rs2.getString(8);
				    				j = rs2.getString(9);
				    				k = rs2.getString(10);
				    				l = rs2.getString(11);
				    				m = rs2.getString(12);
				    				n = rs2.getString(13);
				    				o = rs2.getString(14);
				    				p = rs2.getString(15);
				    				q = rs2.getString(16);
				    				r = rs2.getString(17);
				    				s = rs2.getString(18);
				    				t = rs2.getString(19);
				    				u = rs2.getString(20);
				    				v = rs2.getString(21);
				    				w = rs2.getString(22);
				    				x = rs2.getString(23);
				    				y = grading;
				    				String[] row = {y,a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x};
				    				
				    				if (model.getRowCount()>=1) {
						        		model.removeRow(0);
						        		model.addRow(row);
				    				}
				    				else {
				    					model.addRow(row);
				    				}
				    			}	

				        	}
				        	else if (grading.equals("Second Grading")){
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, secondGrade from grading2 where student_id = ?");
								ps2.setInt(1,value);
					        	rs2 = ps2.executeQuery();
								String a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
								DefaultTableModel model = (DefaultTableModel)table.getModel();
								while (rs2.next()) {
									a = rs2.getString(1);
									b = rs2.getString(2);
									c = rs2.getString(3);
									d = rs2.getString(4);
									f = rs2.getString(5);
									g = rs2.getString(6);
									h = rs2.getString(7);
									i = rs2.getString(8);
									j = rs2.getString(9);
									k = rs2.getString(10);
									l = rs2.getString(11);
									m = rs2.getString(12);
									n = rs2.getString(13);
									o = rs2.getString(14);
									p = rs2.getString(15);
									q = rs2.getString(16);
									r = rs2.getString(17);
									s = rs2.getString(18);
									t = rs2.getString(19);
									u = rs2.getString(20);
									v = rs2.getString(21);
									w = rs2.getString(22);
									x = rs2.getString(23);
									y = grading;
									String[] row = {y,a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x};
				    				if (model.getRowCount()>=1) {
						        		model.removeRow(0);
						        		model.addRow(row);
				    				}
				    				else {
				    					model.addRow(row);
				    				}
								}
				        	}
				        	else if (grading.equals("Third Grading")) {
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, thirdGrade from grading3 where student_id = ?");
								ps2.setInt(1,value);
					        	rs2 = ps2.executeQuery();
								String a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
								DefaultTableModel model = (DefaultTableModel)table.getModel();
								while (rs2.next()) {
									a = rs2.getString(1);
									b = rs2.getString(2);
									c = rs2.getString(3);
									d = rs2.getString(4);
									f = rs2.getString(5);
									g = rs2.getString(6);
									h = rs2.getString(7);
									i = rs2.getString(8);
									j = rs2.getString(9);
									k = rs2.getString(10);
									l = rs2.getString(11);
									m = rs2.getString(12);
									n = rs2.getString(13);
									o = rs2.getString(14);
									p = rs2.getString(15);
									q = rs2.getString(16);
									r = rs2.getString(17);
									s = rs2.getString(18);
									t = rs2.getString(19);
									u = rs2.getString(20);
									v = rs2.getString(21);
									w = rs2.getString(22);
									x = rs2.getString(23);
									y = grading;
									String[] row = {y,a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x};
				    				if (model.getRowCount()>=1) {
						        		model.removeRow(0);
						        		model.addRow(row);
				    				}
				    				else {
				    					model.addRow(row);
				    				}
								}
				        	}
				        	else if (grading.equals("Fourth Grading")) {
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items, ass_total_score, ass_total_items, ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent, exam_weight, initgrade, fourthGrade from grading4 where student_id = ?");
								ps2.setInt(1,value);
					        	rs2 = ps2.executeQuery();
								String a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
								DefaultTableModel model = (DefaultTableModel)table.getModel();
								while (rs2.next()) {
									a = rs2.getString(1);
									b = rs2.getString(2);
									c = rs2.getString(3);
									d = rs2.getString(4);
									f = rs2.getString(5);
									g = rs2.getString(6);
									h = rs2.getString(7);
									i = rs2.getString(8);
									j = rs2.getString(9);
									k = rs2.getString(10);
									l = rs2.getString(11);
									m = rs2.getString(12);
									n = rs2.getString(13);
									o = rs2.getString(14);
									p = rs2.getString(15);
									q = rs2.getString(16);
									r = rs2.getString(17);
									s = rs2.getString(18);
									t = rs2.getString(19);
									u = rs2.getString(20);
									v = rs2.getString(21);
									w = rs2.getString(22);
									x = rs2.getString(23);
									y = grading;
									String[] row = {y,a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x};
				    				if (model.getRowCount()>=1) {
						        		model.removeRow(0);
						        		model.addRow(row);
				    				}
				    				else {
				    					model.addRow(row);
				    				}
				        	}
				        	}
				        	else {
				        		DefaultTableModel model = (DefaultTableModel)table.getModel();
			    				if (model.getRowCount()>=1) {
					        		model.removeRow(0);
			    				}
				        	}
						
						}
						catch(Exception e1) {
							java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
					}
				});
				comboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
				comboBox.setBounds(461, 89, 182, 22);
				comboBox.setSelectedIndex(0);
				grades.add(comboBox);
				
				JScrollPane scroll = new JScrollPane();
				scroll.setBounds(136, 122, 805, 88);
				scroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				grades.add(scroll);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x;
						period = (String) table.getValueAt(table.getSelectedRow(), 0);
						
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connect2 = DriverManager.getConnection(con,user, pass);
				        	if (period.equals("First Grading")) {
				        		ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, firstGrade from grading1 where student_id = ?");
				        	
				        	}	
				        	else if (period.equals("Second Grading")){
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, secondGrade from grading2 where student_id = ?");
				        	
				        	}
				        	else if (period.equals("Third Grading")) {
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, thirdGrade from grading3 where student_id = ?");
							
				        	}
				        	else if (period.equals("Fourth Grading")) {
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items, ass_total_score, ass_total_items, ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent, exam_weight, initgrade, fourthGrade from grading4 where student_id = ?");
				        	
				        	}
			        		ps2.setInt(1,value);
			            	rs2 = ps2.executeQuery();
			            	rs2.next();
		            		b = rs2.getString(2);
							c = rs2.getString(3);
							d = rs2.getString(4);
							f = rs2.getString(5);
							g = rs2.getString(6);
							h = rs2.getString(7);
							i = rs2.getString(8);
							j = rs2.getString(9);
							k = rs2.getString(10);
							l = rs2.getString(11);
							m = rs2.getString(12);
							n = rs2.getString(13);
							o = rs2.getString(14);
							p = rs2.getString(15);
							q = rs2.getString(16);
							r = rs2.getString(17);
							s = rs2.getString(18);
							t = rs2.getString(19);
							u = rs2.getString(20);
							v = rs2.getString(21);
							w = rs2.getString(22);
							x = rs2.getString(23);
							
							Ass1.setText(""+b);
							Ass1_Item.setText(""+c);
							Ass2.setText(""+d);
							Ass2_Item.setText(""+f);
							ass_score_total.setText(""+g);
							ass_item_total.setText(""+h);
							Ass_PScore.setText(""+i);
							Ass_WG.setText(""+j);
							Quiz1.setText(""+k);
							Quiz1_Item.setText(""+l);
							Quiz2.setText(""+m);
							Quiz2_Item.setText(""+n);
							quiz_score_total.setText(""+o);
							quiz_item_total.setText(""+p);
							Quiz_PScore.setText(""+q);
							Quiz_WG.setText(""+r);
							Exam_Score.setText(""+s);
							Exam_Item.setText(t);
							exam_score_total.setText(""+s);
							exam_item_total.setText(""+t);
							Exam_PScore.setText(""+u);
							Exam_WG.setText(""+v);
							initGrade.setText(""+w);
							transGrade.setText(""+x);

						}
						catch(Exception e1) {
							java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}

					}
				});
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Grading Period", "Student ID", "Assignment 1", "Assignment 1 Items", "Assignment 2", "Assignment 2 Items", "Assignment Total Score", "Assignment Total Items", "Assignment Percentage", "Assignment Weighted Grade", "Quiz 1", "Quiz 1 Items", "Quiz 2", "Quiz 2 Items", "Quizzes Total Score", "Quizzes Total Items", "Quizzes Percentage", "Quizzes Weighted Grade", "Quarterly Exam", "Quarterly Exam Items", "Quarterly Exam Percentage", "Quarterly Exam Weighted Grade", "Initial Grade", "Grade"
					}
				) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
					};
					@SuppressWarnings({ "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				table.getColumnModel().getColumn(0).setMinWidth(100);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(1).setPreferredWidth(100);
				table.getColumnModel().getColumn(1).setMinWidth(100);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(2).setPreferredWidth(110);
				table.getColumnModel().getColumn(2).setMinWidth(110);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(3).setPreferredWidth(130);
				table.getColumnModel().getColumn(3).setMinWidth(130);
				table.getColumnModel().getColumn(4).setResizable(false);
				table.getColumnModel().getColumn(4).setPreferredWidth(110);
				table.getColumnModel().getColumn(4).setMinWidth(110);
				table.getColumnModel().getColumn(5).setResizable(false);
				table.getColumnModel().getColumn(5).setPreferredWidth(130);
				table.getColumnModel().getColumn(5).setMinWidth(130);
				table.getColumnModel().getColumn(6).setResizable(false);
				table.getColumnModel().getColumn(6).setPreferredWidth(150);
				table.getColumnModel().getColumn(6).setMinWidth(150);
				table.getColumnModel().getColumn(7).setResizable(false);
				table.getColumnModel().getColumn(7).setPreferredWidth(160);
				table.getColumnModel().getColumn(7).setMinWidth(160);
				table.getColumnModel().getColumn(8).setResizable(false);
				table.getColumnModel().getColumn(8).setPreferredWidth(160);
				table.getColumnModel().getColumn(8).setMinWidth(160);
				table.getColumnModel().getColumn(9).setResizable(false);
				table.getColumnModel().getColumn(9).setPreferredWidth(170);
				table.getColumnModel().getColumn(9).setMinWidth(170);
				table.getColumnModel().getColumn(10).setResizable(false);
				table.getColumnModel().getColumn(10).setMinWidth(75);
				table.getColumnModel().getColumn(11).setResizable(false);
				table.getColumnModel().getColumn(11).setPreferredWidth(90);
				table.getColumnModel().getColumn(11).setMinWidth(90);
				table.getColumnModel().getColumn(12).setResizable(false);
				table.getColumnModel().getColumn(12).setMinWidth(75);
				table.getColumnModel().getColumn(13).setResizable(false);
				table.getColumnModel().getColumn(13).setPreferredWidth(100);
				table.getColumnModel().getColumn(13).setMinWidth(100);
				table.getColumnModel().getColumn(14).setResizable(false);
				table.getColumnModel().getColumn(14).setPreferredWidth(130);
				table.getColumnModel().getColumn(14).setMinWidth(130);
				table.getColumnModel().getColumn(15).setResizable(false);
				table.getColumnModel().getColumn(15).setPreferredWidth(140);
				table.getColumnModel().getColumn(15).setMinWidth(140);
				table.getColumnModel().getColumn(16).setResizable(false);
				table.getColumnModel().getColumn(16).setPreferredWidth(130);
				table.getColumnModel().getColumn(16).setMinWidth(130);
				table.getColumnModel().getColumn(17).setResizable(false);
				table.getColumnModel().getColumn(17).setPreferredWidth(160);
				table.getColumnModel().getColumn(17).setMinWidth(160);
				table.getColumnModel().getColumn(18).setResizable(false);
				table.getColumnModel().getColumn(18).setPreferredWidth(120);
				table.getColumnModel().getColumn(18).setMinWidth(120);
				table.getColumnModel().getColumn(19).setResizable(false);
				table.getColumnModel().getColumn(19).setPreferredWidth(150);
				table.getColumnModel().getColumn(19).setMinWidth(150);
				table.getColumnModel().getColumn(20).setResizable(false);
				table.getColumnModel().getColumn(20).setPreferredWidth(170);
				table.getColumnModel().getColumn(20).setMinWidth(170);
				table.getColumnModel().getColumn(21).setResizable(false);
				table.getColumnModel().getColumn(21).setPreferredWidth(200);
				table.getColumnModel().getColumn(21).setMinWidth(200);
				table.getColumnModel().getColumn(22).setResizable(false);
				table.getColumnModel().getColumn(22).setPreferredWidth(90);
				table.getColumnModel().getColumn(22).setMinWidth(90);
				table.getColumnModel().getColumn(23).setResizable(false);
				table.getColumnModel().getColumn(23).setMinWidth(75);
				table.setRowHeight(18);
				table.setForeground(Color.BLACK);
				table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				scroll.setViewportView(table);
				
				JTableHeader header = new JTableHeader();
				header = table.getTableHeader();
				Font head = new Font("Segoe UI", Font.BOLD, 12);
				header.setFont(head);
				header.setForeground(Color.BLACK);
				
				DefaultTableCellRenderer center = new DefaultTableCellRenderer();
				center.setHorizontalAlignment( JLabel.CENTER );
				table.setDefaultRenderer(String.class, center);
				
				JLabel lblsName = new JLabel(""+Name);
				lblsName.setForeground(Color.BLACK);
				lblsName.setHorizontalAlignment(SwingConstants.CENTER);
				lblsName.setFont(new Font("Segoe UI", Font.BOLD, 20));
				lblsName.setBounds(391, 214, 316, 36);
				grades.add(lblsName);
				
				JLabel lblAss_Score = new JLabel("Score");
				lblAss_Score.setForeground(Color.BLACK);
				lblAss_Score.setHorizontalAlignment(SwingConstants.CENTER);
				lblAss_Score.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Score.setBounds(275, 250, 51, 25);
				grades.add(lblAss_Score);
				
				JLabel lblAss_Items = new JLabel("Items");
				lblAss_Items.setForeground(Color.BLACK);
				lblAss_Items.setHorizontalAlignment(SwingConstants.CENTER);
				lblAss_Items.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Items.setBounds(351, 250, 40, 25);
				grades.add(lblAss_Items);
				
				JLabel lblAss1 = new JLabel("Assignment 1");
				lblAss1.setForeground(Color.BLACK);
				lblAss1.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAss1.setBounds(148, 283, 97, 25);
				grades.add(lblAss1);
				
				Ass1 = new JTextField();
				Ass1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass1.setForeground(Color.BLACK);
				Ass1.setBounds(270, 287, 61, 20);
				grades.add(Ass1);
				Ass1.setColumns(10);
				
				Ass1_Item = new JTextField();
				Ass1_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass1_Item.setForeground(Color.BLACK);
				Ass1_Item.setColumns(10);
				Ass1_Item.setBounds(341, 287, 61, 20);
				grades.add(Ass1_Item);
				
				JLabel lblAss2 = new JLabel("Assignment 2");
				lblAss2.setForeground(Color.BLACK);
				lblAss2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAss2.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss2.setBounds(148, 319, 97, 25);
				grades.add(lblAss2);
				
				Ass2 = new JTextField();
				Ass2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass2.setForeground(Color.BLACK);
				Ass2.setColumns(10);
				Ass2.setBounds(270, 323, 61, 20);
				grades.add(Ass2);
				
				Ass2_Item = new JTextField();
				Ass2_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass2_Item.setForeground(Color.BLACK);
				Ass2_Item.setColumns(10);
				Ass2_Item.setBounds(341, 323, 61, 20);
				grades.add(Ass2_Item);
				
				JLabel lblAss_Total = new JLabel("Total");
				lblAss_Total.setForeground(Color.BLACK);
				lblAss_Total.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAss_Total.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Total.setBounds(148, 354, 97, 25);
				grades.add(lblAss_Total);
				
				ass_score_total = new JTextField();
				ass_score_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				ass_score_total.setForeground(Color.BLACK);
				ass_score_total.setEditable(false);
				ass_score_total.setColumns(10);
				ass_score_total.setBounds(270, 359, 61, 20);
				grades.add(ass_score_total);
				
				ass_item_total = new JTextField();
				ass_item_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				ass_item_total.setForeground(Color.BLACK);
				ass_item_total.setEditable(false);
				ass_item_total.setColumns(10);
				ass_item_total.setBounds(341, 359, 61, 20);
				grades.add(ass_item_total);
				
				JLabel lblQuiz_Score = new JLabel("Score");
				lblQuiz_Score.setHorizontalAlignment(SwingConstants.CENTER);
				lblQuiz_Score.setForeground(Color.BLACK);
				lblQuiz_Score.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Score.setBounds(513, 250, 51, 25);
				grades.add(lblQuiz_Score);
				
				JLabel lblQuiz_Items = new JLabel("Items");
				lblQuiz_Items.setHorizontalAlignment(SwingConstants.CENTER);
				lblQuiz_Items.setForeground(Color.BLACK);
				lblQuiz_Items.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Items.setBounds(589, 250, 40, 25);
				grades.add(lblQuiz_Items);
				
				JLabel lblQuiz1 = new JLabel("Quiz 1");
				lblQuiz1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblQuiz1.setForeground(Color.BLACK);
				lblQuiz1.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz1.setBounds(422, 287, 61, 25);
				grades.add(lblQuiz1);
				
				Quiz1 = new JTextField();
				Quiz1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz1.setForeground(Color.BLACK);
				Quiz1.setColumns(10);
				Quiz1.setBounds(508, 287, 61, 20);
				grades.add(Quiz1);
				
				Quiz1_Item = new JTextField();
				Quiz1_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz1_Item.setForeground(Color.BLACK);
				Quiz1_Item.setColumns(10);
				Quiz1_Item.setBounds(579, 287, 61, 20);
				grades.add(Quiz1_Item);
				
				JLabel lblQuiz2 = new JLabel("Quiz 2");
				lblQuiz2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblQuiz2.setForeground(Color.BLACK);
				lblQuiz2.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz2.setBounds(422, 319, 61, 25);
				grades.add(lblQuiz2);
				
				Quiz2 = new JTextField();
				Quiz2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz2.setForeground(Color.BLACK);
				Quiz2.setColumns(10);
				Quiz2.setBounds(508, 323, 61, 20);
				grades.add(Quiz2);
				
				Quiz2_Item = new JTextField();
				Quiz2_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz2_Item.setForeground(Color.BLACK);
				Quiz2_Item.setColumns(10);
				Quiz2_Item.setBounds(579, 323, 61, 20);
				grades.add(Quiz2_Item);
				
				JLabel lblQuiz_Total = new JLabel("Total");
				lblQuiz_Total.setHorizontalAlignment(SwingConstants.RIGHT);
				lblQuiz_Total.setForeground(Color.BLACK);
				lblQuiz_Total.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Total.setBounds(422, 354, 61, 25);
				grades.add(lblQuiz_Total);
				
				quiz_score_total = new JTextField();
				quiz_score_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				quiz_score_total.setForeground(Color.BLACK);
				quiz_score_total.setEditable(false);
				quiz_score_total.setColumns(10);
				quiz_score_total.setBounds(508, 359, 61, 20);
				grades.add(quiz_score_total);
				
				quiz_item_total = new JTextField();
				quiz_item_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				quiz_item_total.setForeground(Color.BLACK);
				quiz_item_total.setEditable(false);
				quiz_item_total.setColumns(10);
				quiz_item_total.setBounds(579, 359, 61, 20);
				grades.add(quiz_item_total);
				
				JLabel lblExam_Score = new JLabel("Score");
				lblExam_Score.setHorizontalAlignment(SwingConstants.CENTER);
				lblExam_Score.setForeground(Color.BLACK);
				lblExam_Score.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Score.setBounds(804, 250, 51, 25);
				grades.add(lblExam_Score);
				
				JLabel lblExam_Items = new JLabel("Items");
				lblExam_Items.setHorizontalAlignment(SwingConstants.CENTER);
				lblExam_Items.setForeground(Color.BLACK);
				lblExam_Items.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Items.setBounds(880, 250, 40, 25);
				grades.add(lblExam_Items);
				
				Exam_Score = new JTextField();
				Exam_Score.setForeground(Color.BLACK);
				Exam_Score.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_Score.setColumns(10);
				Exam_Score.setBounds(799, 287, 61, 20);
				grades.add(Exam_Score);
				
				Exam_Item = new JTextField();
				Exam_Item.setForeground(Color.BLACK);
				Exam_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_Item.setColumns(10);
				Exam_Item.setBounds(870, 287, 61, 20);
				grades.add(Exam_Item);
				
				JLabel lblExam = new JLabel("Quarterly Exam");
				lblExam.setHorizontalAlignment(SwingConstants.RIGHT);
				lblExam.setForeground(Color.BLACK);
				lblExam.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam.setBounds(659, 287, 115, 25);
				grades.add(lblExam);
				
				JLabel lblExam_Total = new JLabel("Total");
				lblExam_Total.setHorizontalAlignment(SwingConstants.RIGHT);
				lblExam_Total.setForeground(Color.BLACK);
				lblExam_Total.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Total.setBounds(713, 319, 61, 25);
				grades.add(lblExam_Total);
				
				exam_score_total = new JTextField();
				exam_score_total.setForeground(Color.BLACK);
				exam_score_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				exam_score_total.setEditable(false);
				exam_score_total.setColumns(10);
				exam_score_total.setBounds(799, 324, 61, 20);
				grades.add(exam_score_total);
				
				exam_item_total = new JTextField();
				exam_item_total.setForeground(Color.BLACK);
				exam_item_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				exam_item_total.setEditable(false);
				exam_item_total.setColumns(10);
				exam_item_total.setBounds(870, 324, 61, 20);
				grades.add(exam_item_total);
				
				JLabel lblComputation = new JLabel("COMPUTATIONS");
				lblComputation.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
				lblComputation.setBounds(58, 441, 214, 36);
				grades.add(lblComputation);
				
				JLabel lblAss_Compute = new JLabel("Assignment (25%)");
				lblAss_Compute.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Compute.setBounds(58, 480, 137, 20);
				grades.add(lblAss_Compute);
				
				JLabel lblPScore_Ass2 = new JLabel("Percentage Score:");
				lblPScore_Ass2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Ass2.setBounds(58, 529, 103, 20);
				grades.add(lblPScore_Ass2);
				
				Ass_PScore = new JTextField("");
				Ass_PScore.setEditable(false);
				Ass_PScore.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass_PScore.setBounds(167, 531, 51, 16);
				grades.add(Ass_PScore);
				
				JLabel lblPScore_Ass1 = new JLabel("PS = (Total Score / Total Item) * 100");
				lblPScore_Ass1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Ass1.setBounds(58, 511, 214, 18);
				grades.add(lblPScore_Ass1);
				
				JLabel lblWG_Ass1 = new JLabel("WG = PS * 25%");
				lblWG_Ass1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Ass1.setBounds(58, 552, 88, 20);
				grades.add(lblWG_Ass1);
				
				JLabel lblWG_Ass2 = new JLabel("Weighted Grade: ");
				lblWG_Ass2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Ass2.setBounds(58, 574, 103, 20);
				grades.add(lblWG_Ass2);
				
				Ass_WG = new JTextField("");
				Ass_WG.setEditable(false);
				Ass_WG.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass_WG.setBounds(161, 576, 51, 16);
				grades.add(Ass_WG);
				
				JLabel lblQuiz_Compute = new JLabel("Quizzes (35%)");
				lblQuiz_Compute.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Compute.setBounds(275, 480, 137, 20);
				grades.add(lblQuiz_Compute);
				
				JLabel lblPScore_Quiz1 = new JLabel("PS = (Total Score / Total Item) * 100");
				lblPScore_Quiz1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Quiz1.setBounds(275, 511, 214, 18);
				grades.add(lblPScore_Quiz1);
				
				JLabel lblPScore_Quiz2 = new JLabel("Percentage Score:");
				lblPScore_Quiz2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Quiz2.setBounds(275, 529, 103, 20);
				grades.add(lblPScore_Quiz2);
				
				Quiz_PScore = new JTextField("");
				Quiz_PScore.setEditable(false);
				Quiz_PScore.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz_PScore.setBounds(384, 531, 55, 16);
				grades.add(Quiz_PScore);
				
				JLabel lblWG_Quiz1 = new JLabel("WG = PS * 35%");
				lblWG_Quiz1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Quiz1.setBounds(275, 552, 88, 20);
				grades.add(lblWG_Quiz1);
				
				JLabel lblWG_Quiz2 = new JLabel("Weighted Grade: ");
				lblWG_Quiz2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Quiz2.setBounds(275, 574, 103, 20);
				grades.add(lblWG_Quiz2);
				
				Quiz_WG = new JTextField("");
				Quiz_WG.setEditable(false);
				Quiz_WG.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz_WG.setBounds(378, 576, 61, 16);
				grades.add(Quiz_WG);
				
				JLabel lblExam_Compute = new JLabel("Quarterly Exam (40%)");
				lblExam_Compute.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Compute.setBounds(493, 480, 164, 20);
				grades.add(lblExam_Compute);
				
				JLabel lblPScore_Exam1 = new JLabel("PS = (Total Score / Total Item) * 100");
				lblPScore_Exam1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Exam1.setBounds(493, 511, 214, 18);
				grades.add(lblPScore_Exam1);
				
				JLabel lblPScore_Exam2 = new JLabel("Percentage Score:");
				lblPScore_Exam2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Exam2.setBounds(493, 529, 103, 20);
				grades.add(lblPScore_Exam2);
				
				JLabel lblWG_Exam1 = new JLabel("WG = PS * 40%");
				lblWG_Exam1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Exam1.setBounds(493, 552, 88, 20);
				grades.add(lblWG_Exam1);
				
				JLabel lblWG_Exam2 = new JLabel("Weighted Grade: ");
				lblWG_Exam2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Exam2.setBounds(493, 574, 103, 20);
				grades.add(lblWG_Exam2);
				
				Exam_WG = new JTextField("");
				Exam_WG.setEditable(false);
				Exam_WG.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_WG.setBounds(596, 576, 51, 16);
				grades.add(Exam_WG);
				
				Exam_PScore = new JTextField("");
				Exam_PScore.setEditable(false);
				Exam_PScore.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_PScore.setBounds(602, 531, 51, 16);
				grades.add(Exam_PScore);
				
				JLabel lblinitGrades = new JLabel("Initial Grade");
				lblinitGrades.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblinitGrades.setBounds(717, 464, 97, 20);
				grades.add(lblinitGrades);
				
				JLabel lblinitGrade1 = new JLabel("IG = WG(Assign) + WG(Quiz) + WG(Exam)");
				lblinitGrade1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblinitGrade1.setBounds(717, 495, 232, 20);
				grades.add(lblinitGrade1);
				
				JLabel lblinitGrade2 = new JLabel("Initial Grade:");
				lblinitGrade2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblinitGrade2.setBounds(717, 515, 81, 20);
				grades.add(lblinitGrade2);
				
				initGrade = new JTextField("");
				initGrade.setEditable(false);
				initGrade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				initGrade.setBounds(796, 517, 40, 16);
				grades.add(initGrade);
				
				JLabel lbltransGrades = new JLabel("Transmuted Grade");
				lbltransGrades.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lbltransGrades.setBounds(717, 543, 137, 20);
				grades.add(lbltransGrades);
				
				JLabel lbltransGrade = new JLabel("TG = Equivalent (IG) in Transmuted Grades");
				lbltransGrade.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lbltransGrade.setBounds(717, 568, 243, 20);
				grades.add(lbltransGrade);
				
				JLabel lbltransGrade2 = new JLabel("Transmuted Grade:");
				lbltransGrade2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lbltransGrade2.setBounds(717, 588, 115, 20);
				grades.add(lbltransGrade2);
				
				transGrade = new JTextField("");
				transGrade.setEditable(false);
				transGrade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				transGrade.setBounds(829, 590, 40, 16);
				grades.add(transGrade);
				
				JLabel transmuted = new JLabel("Transmuted Grade Table");
				transmuted.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						TransmutedGrade tg = new TransmutedGrade();
						tg.EquiTable.setVisible(true);
					}
				});
				transmuted.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				transmuted.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
				transmuted.setBounds(771, 629, 189, 20);
				grades.add(transmuted);
				
				Font font = transmuted.getFont();
				@SuppressWarnings("rawtypes")
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				transmuted.setFont(font.deriveFont(attributes));
				
			
				JButton btnSave = new JButton("SAVE");
				btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(Ass1.getText().equals("")||Ass1_Item.getText().equals("")){
						ass1 = ""+0;
						ass1_item = ""+0;
					}else {
						ass1 = Ass1.getText();
						ass1_item = Ass1_Item.getText();
					}
					
					if (Ass2.getText().equals("")||Ass2_Item.getText().equals("")) {
						ass2 = ""+0;
						ass2_item = ""+0;
					}else {
						ass2 = Ass2.getText();
						ass2_item = Ass2_Item.getText();
					}
					
					if(Quiz1.getText().equals("")||Quiz1_Item.getText().equals("")){
						quiz1 = ""+0;
						quiz1_item = ""+0;
					}else{
						quiz1 = Quiz1.getText();
						quiz1_item = Quiz1_Item.getText();
					}
					
					if (Quiz2.getText().equals("")||Quiz2_Item.getText().equals("")) {
						quiz2 = ""+0;
						quiz2_item = ""+0;
					}else {
						quiz2 = Quiz2.getText();
						quiz2_item = Quiz2_Item.getText();
					}
					
					if(Exam_Score.getText().equals("") ||Exam_Item.getText().equals("")) {
						exam = ""+0;
						exam_item = ""+0;
					}else {
						exam = Exam_Score.getText();
						exam_item = Exam_Item.getText();
					}
						
						
						if(ass1.equals("")|| ass2.equals("")|| ass1_item.equals("0")|| ass2_item.equals("0")) {
							Ass_PScore.setText("0");
							Ass_WG.setText("0");
							ass_weight = 0;
						}
						else {
							ass_total_sc = Integer.parseInt(ass1)+Integer.parseInt(ass2);
							ass_score_total.setText(""+ass_total_sc);
								//item
							ass_total_item = Integer.parseInt(ass1_item)+Integer.parseInt(ass2_item);
							ass_item_total.setText(""+ass_total_item);
								//percent and weighted
							ass_percent = (Double.valueOf(ass_total_sc)/Double.valueOf(ass_total_item))*100;
							Ass_PScore.setText(""+df.format(ass_percent));
							ass_weight = ass_percent*0.25;
							Ass_WG.setText(""+df.format(ass_weight));
						}
	
					
						//quiz
							//score
						if(quiz1.equals("")|| quiz2.equals("")|| quiz1_item.equals("0")|| quiz2_item.equals("0")) {
							Quiz_PScore.setText("0");
							Quiz_WG.setText("0");
							quiz_weight = 0;
						}
						else {
							quiz_total_sc = Integer.parseInt(quiz1)+Integer.parseInt(quiz2);
							quiz_score_total.setText(""+quiz_total_sc);
								//item
							quiz_total_item = Integer.parseInt(quiz1_item)+Integer.parseInt(quiz2_item);
							quiz_item_total.setText(""+quiz_total_item);
								//percent and weighted
							quiz_percent = (Double.valueOf(quiz_total_sc)/Double.valueOf(quiz_total_item))*100;
							Quiz_PScore.setText(""+df.format(quiz_percent));
							quiz_weight = quiz_percent*0.35;
							Quiz_WG.setText(""+df.format(quiz_weight));
						}
						
						
						//exam
							//score
						if(exam.equals("")|| exam_item.equals("0")) {
							Exam_PScore.setText("0");
							Exam_WG.setText("0");
							exam_weight = 0;
						}
						else {
							exam_total_sc = Integer.parseInt(exam);
							exam_score_total.setText(""+exam_total_sc);
								//item
							exam_total_item = Integer.parseInt(exam_item);
							exam_item_total.setText(""+exam_total_item);
								//percent and weighted
							exam_percent = (Double.valueOf(exam_total_sc)/Double.valueOf(exam_total_item))*100;
							Exam_PScore.setText(""+df.format(exam_percent));
							exam_weight = exam_percent*0.4;
							Exam_WG.setText(""+df.format(exam_weight));
						}
					
					
						//initial and trans grade
						initial = ass_weight + quiz_weight + exam_weight;
						initGrade.setText(""+df.format(initial));
						tGrade tg = new tGrade();
						tgrade = (int)tg.equivalent(initial);
						transGrade.setText(""+tgrade);
						
						if (Integer.parseInt(ass1) >Integer.parseInt(ass1_item)||Integer.parseInt(ass2) >Integer.parseInt(ass2_item)||Integer.parseInt(quiz1) >Integer.parseInt(quiz1_item)||Integer.parseInt(quiz2) >Integer.parseInt(quiz2_item)||
							Integer.parseInt(exam) >Integer.parseInt(exam_item)) {
							JOptionPane.showMessageDialog(grades, "Score Cannot be Greater than the Number of Items", "Grades Input Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(con,user, pass);
							connect2 = DriverManager.getConnection(con,user, pass);
	                    	if (grading.equals("First Grading")) {
	                    		ps = connect.prepareStatement("insert into grading1 (student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
	                    			+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items, exam_total_score, exam_total_items, exam_percent,"
	                    			+ "exam_weight, initgrade, firstGrade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	                    		ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
	                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, firstGrade from grading1 where student_id = ?");
	                    	}
	                    	else if (grading.equals("Second Grading")){
								ps = connect.prepareStatement("insert into grading2 (student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
		                    			+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items, exam_total_score, exam_total_items, exam_percent,"
		                    			+ "exam_weight, initgrade, secondGrade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
	                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, secondGrade from grading2 where student_id = ?");
	                    	}
	                    	else if (grading.equals("Third Grading")) {
								ps = connect.prepareStatement("insert into grading3 (student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
		                    			+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items, exam_total_score, exam_total_items, exam_percent,"
		                    			+ "exam_weight, initgrade, thirdGrade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
	                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, thirdGrade from grading3 where student_id = ?");
	                    	}
	                    	else if (grading.equals("Fourth Grading")) {
								ps = connect.prepareStatement("insert into grading4 (student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
		                    			+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items, exam_total_score, exam_total_items, exam_percent,"
		                    			+ "exam_weight, initgrade, fourthGrade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items, ass_total_score, ass_total_items, ass_percent, ass_weight,"
	                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent, exam_weight, initgrade, fourthGrade from grading4 where student_id = ?");
	                    	}
	                    	else {
	                    		JOptionPane.showMessageDialog(grades, "The chosen grading period does not exist", "Error!", JOptionPane.ERROR_MESSAGE);
	                    	}
	                    	
	                    	ps2.setInt(1,value);
	                    	rs2 = ps2.executeQuery();
	                    	
				            	if (rs2.next()) {
				            		if (period.equals("First Grading")) {
				            			ps3 = connect2.prepareStatement("update grading1 set ass1=?, ass1_items=?, ass2=?, ass2_items=?, ass_total_score=?, ass_total_items=?, ass_percent=?, ass_weight=?,"
		    			                    	+ "quiz1=?, quiz1_items=?, quiz2=?, quiz2_items=?, quiz_total_score=?, quiz_total_items=?, quiz_percent=?, quiz_weight=?, exam=?, exam_items=?, exam_total_score=?, exam_total_items=?, exam_percent=?,"
		    			                    	+ "exam_weight=?, initgrade=?, firstGrade=? where student_id = ?");
				            			ps4 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
			                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, firstGrade from grading1 where student_id = ?");
			                    	
				            		}
				            		else if (period.equals("Second Grading")) {
				            			ps3 = connect2.prepareStatement("update grading2 set ass1=?, ass1_items=?, ass2=?, ass2_items=?, ass_total_score=?, ass_total_items=?, ass_percent=?, ass_weight=?,"
		    			                    	+ "quiz1=?, quiz1_items=?, quiz2=?, quiz2_items=?, quiz_total_score=?, quiz_total_items=?, quiz_percent=?, quiz_weight=?, exam=?, exam_items=?, exam_total_score=?, exam_total_items=?, exam_percent=?,"
		    			                    	+ "exam_weight=?, initgrade=?, secondGrade=? where student_id = ?");
				            			ps4 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
			                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, secondGrade from grading2 where student_id = ?");
			                    	
				            		}
				            		else if (period.equals("Third Grading")) {
				            			ps3 = connect2.prepareStatement("update grading3 set ass1=?, ass1_items=?, ass2=?, ass2_items=?, ass_total_score=?, ass_total_items=?, ass_percent=?, ass_weight=?,"
		    			                    	+ "quiz1=?, quiz1_items=?, quiz2=?, quiz2_items=?, quiz_total_score=?, quiz_total_items=?, quiz_percent=?, quiz_weight=?, exam=?, exam_items=?, exam_total_score=?, exam_total_items=?, exam_percent=?,"
		    			                    	+ "exam_weight=?, initgrade=?, thirdGrade=? where student_id = ?");
				            			ps4 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
			                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, thirdGrade from grading3 where student_id = ?");
			                    	
				            		}
				            		else if (period.equals("Fourth Grading")) {
				            			ps3 = connect2.prepareStatement("update grading4 set ass1=?, ass1_items=?, ass2=?, ass2_items=?, ass_total_score=?, ass_total_items=?, ass_percent=?, ass_weight=?,"
		    			                    	+ "quiz1=?, quiz1_items=?, quiz2=?, quiz2_items=?, quiz_total_score=?, quiz_total_items=?, quiz_percent=?, quiz_weight=?, exam=?, exam_items=?, exam_total_score=?, exam_total_items=?, exam_percent=?,"
		    			                    	+ "exam_weight=?, initgrade=?, fourthGrade=? where student_id = ?");
				            			ps4 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items, ass_total_score, ass_total_items, ass_percent, ass_weight,"
			                    				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent, exam_weight, initgrade, fourthGrade from grading4 where student_id = ?");
			                    	
				            		}

			            			ps3.setInt(25,value);
	    	                    	ps3.setInt(1,Integer.parseInt(ass1));
	    	                    	ps3.setInt(2,Integer.parseInt(ass1_item));
	    	                    	ps3.setInt(3,Integer.parseInt(ass2));
	    	                    	ps3.setInt(4,Integer.parseInt(ass2_item));
	    	                    	ps3.setInt(5,ass_total_sc);
	    	                    	ps3.setInt(6,ass_total_item);
	    	                    	ps3.setString(7,Ass_PScore.getText());
	    	                    	ps3.setString(8,Ass_WG.getText());
	    	                    	ps3.setInt(9,Integer.parseInt(quiz1));
	    	                    	ps3.setInt(10,Integer.parseInt(quiz1_item));
	    	                    	ps3.setInt(11,Integer.parseInt(quiz2));
	    	                    	ps3.setInt(12,Integer.parseInt(quiz2_item));
	    	                    	ps3.setInt(13,quiz_total_sc);
	    	                    	ps3.setInt(14,quiz_total_item);
	    	                    	ps3.setString(15,Quiz_PScore.getText());
	    	                    	ps3.setString(16,Quiz_WG.getText());
	    	                    	ps3.setInt(17,Integer.parseInt(exam));
	    	                    	ps3.setInt(18,Integer.parseInt(exam_item));
	    	                    	ps3.setInt(19,exam_total_sc);
	    	                    	ps3.setInt(20,exam_total_item);
	    	                    	ps3.setString(21,Exam_PScore.getText());
	    	                    	ps3.setString(22,Ass_WG.getText());
	    	                    	ps3.setString(23,initGrade.getText());
	    	                    	ps3.setInt(24,tgrade);
	    	                    	ps3.executeUpdate();
	    	                    	
	    	                    	JOptionPane.showMessageDialog(grades,"Successfully updated grades for student "+value+".","Success", JOptionPane.INFORMATION_MESSAGE);
	    	                    	
	    	                    	Ass1.setText("");
			                    	Ass2.setText("");
			                    	Ass1_Item.setText("");
			                    	Ass2_Item.setText("");
			                    	ass_score_total.setText("");
			                    	ass_item_total.setText("");
			                    	Quiz1.setText("");
			                    	Quiz2.setText("");
			                    	Quiz1_Item.setText("");
			                    	Quiz2_Item.setText("");
			                    	quiz_score_total.setText("");
			                    	quiz_item_total.setText("");
			                    	Exam_Score.setText("");
			                    	Exam_Item.setText("");
			                    	exam_score_total.setText("");
			                    	exam_item_total.setText("");
			                    	Ass_PScore.setText("");
			                    	Ass_WG.setText("");
			                    	Quiz_PScore.setText("");
			                    	Quiz_WG.setText("");
			                    	Exam_PScore.setText("");
			                    	Exam_WG.setText("");
			                    	initGrade.setText("");
			                    	transGrade.setText("");
			                    	
			                    	
			                    	ps4.setInt(1,value);
			                    	rs4 = ps4.executeQuery();
			                    	DefaultTableModel model = (DefaultTableModel)table.getModel();
									String a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
			            			while (rs4.next()) {
			            				a = rs4.getString(1);
			            				b = rs4.getString(2);
			            				c = rs4.getString(3);
			            				d = rs4.getString(4);
			            				f = rs4.getString(5);
			            				g = rs4.getString(6);
			            				h = rs4.getString(7);
			            				i = rs4.getString(8);
			            				j = rs4.getString(9);
			            				k = rs4.getString(10);
			            				l = rs4.getString(11);
			            				m = rs4.getString(12);
			            				n = rs4.getString(13);
			            				o = rs4.getString(14);
			            				p = rs4.getString(15);
			            				q = rs4.getString(16);
			            				r = rs4.getString(17);
			            				s = rs4.getString(18);
			            				t = rs4.getString(19);
			            				u = rs4.getString(20);
			            				v = rs4.getString(21);
			            				w = rs4.getString(22);
			            				x = rs4.getString(23);
			            				y = grading;
			            				String[] row = {y,a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x};
			            				if (model.getRowCount()>=1) {
							        		model.removeRow(0);
							        		model.addRow(row);
					    				}
					    				else {
					    					model.addRow(row);
					    				}
			            			}
				            	}	
				            	else {
				            		
				            		ps.setInt(1,value);
			                    	ps.setInt(2,Integer.parseInt(ass1));
			                    	ps.setInt(3,Integer.parseInt(ass1_item));
			                    	ps.setInt(4,Integer.parseInt(ass2));
			                    	ps.setInt(5,Integer.parseInt(ass2_item));
			                    	ps.setInt(6,ass_total_sc);
			                    	ps.setInt(7,ass_total_item);
			                    	ps.setString(8,Ass_PScore.getText());
			                    	ps.setString(9,Ass_WG.getText());
			                    	ps.setInt(10,Integer.parseInt(quiz1));
			                    	ps.setInt(11,Integer.parseInt(quiz1_item));
			                    	ps.setInt(12,Integer.parseInt(quiz2));
			                    	ps.setInt(13,Integer.parseInt(quiz2_item));
			                    	ps.setInt(14,quiz_total_sc);
			                    	ps.setInt(15,quiz_total_item);
			                    	ps.setString(16,Quiz_PScore.getText());
			                    	ps.setString(17,Quiz_WG.getText());
			                    	ps.setInt(18,Integer.parseInt(exam));
			                    	ps.setInt(19,Integer.parseInt(exam_item));
			                    	ps.setInt(20,exam_total_sc);
			                    	ps.setInt(21,exam_total_item);
			                    	ps.setString(22,Exam_PScore.getText());
			                    	ps.setString(23,Ass_WG.getText());
			                    	ps.setString(24,initGrade.getText());
			                    	ps.setInt(25,tgrade);
			                    	
			                    	ps.executeUpdate();
			                    	JOptionPane.showMessageDialog(grades,"Successfully inputted grades for student "+value+".","Success", JOptionPane.INFORMATION_MESSAGE);
			                    	
			                    	Ass1.setText("");
			                    	Ass2.setText("");
			                    	Ass1_Item.setText("");
			                    	Ass2_Item.setText("");
			                    	ass_score_total.setText("");
			                    	ass_item_total.setText("");
			                    	Quiz1.setText("");
			                    	Quiz2.setText("");
			                    	Quiz1_Item.setText("");
			                    	Quiz2_Item.setText("");
			                    	quiz_score_total.setText("");
			                    	quiz_item_total.setText("");
			                    	Exam_Score.setText("");
			                    	Exam_Item.setText("");
			                    	exam_score_total.setText("");
			                    	exam_item_total.setText("");
			                    	Ass_PScore.setText("");
			                    	Ass_WG.setText("");
			                    	Quiz_PScore.setText("");
			                    	Quiz_WG.setText("");
			                    	Exam_PScore.setText("");
			                    	Exam_WG.setText("");
			                    	initGrade.setText("");
			                    	transGrade.setText("");
			                    	
			                    	ps2.setInt(1,value);
			                    	rs2 = ps2.executeQuery();
			                    	DefaultTableModel model = (DefaultTableModel)table.getModel();
									String a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y;
			            			while (rs2.next()) {
			            				a = rs2.getString(1);
			            				b = rs2.getString(2);
			            				c = rs2.getString(3);
			            				d = rs2.getString(4);
			            				f = rs2.getString(5);
			            				g = rs2.getString(6);
			            				h = rs2.getString(7);
			            				i = rs2.getString(8);
			            				j = rs2.getString(9);
			            				k = rs2.getString(10);
			            				l = rs2.getString(11);
			            				m = rs2.getString(12);
			            				n = rs2.getString(13);
			            				o = rs2.getString(14);
			            				p = rs2.getString(15);
			            				q = rs2.getString(16);
			            				r = rs2.getString(17);
			            				s = rs2.getString(18);
			            				t = rs2.getString(19);
			            				u = rs2.getString(20);
			            				v = rs2.getString(21);
			            				w = rs2.getString(22);
			            				x = rs2.getString(23);
			            				y = grading;
			            				String[] row = {y,a,b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x};
			            				if (model.getRowCount()>=1) {
							        		model.removeRow(0);
							        		model.addRow(row);
					    				}
					    				else {
					    					model.addRow(row);
					    				}
				            	}
	            		}
						}
						catch(Exception e1) {
				            JOptionPane.showMessageDialog(null,e1);
							java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
					}
				}		
				});
				btnSave.setForeground(Color.BLACK);
				btnSave.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
				btnSave.setBounds(514, 400, 115, 25);
				grades.add(btnSave);		
				
//information panel
		final JPanel info = new JPanel();
		info.setForeground(Color.BLACK);
		info.setBounds(194, 0, 1070, 681);
		mainFrame.getContentPane().add(info);
		info.setLayout(null);
		
		JLabel lblStudentInformation = new JLabel("STUDENT INFORMATION");
		lblStudentInformation.setForeground(Color.BLACK);
		lblStudentInformation.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblStudentInformation.setBounds(32, 29, 326, 42);
		info.add(lblStudentInformation);
		
		JLabel sID = new JLabel("Student ID:");
		sID.setForeground(Color.BLACK);
		sID.setFont(new Font("Segoe UI", Font.BOLD, 18));
		sID.setBounds(32, 95, 104, 34);
		info.add(sID);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblName.setBounds(32, 140, 59, 34);
		info.add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblAddress.setBounds(32, 185, 83, 34);
		info.add(lblAddress);
		
		JLabel lblBirth = new JLabel("Birthdate:");
		lblBirth.setForeground(Color.BLACK);
		lblBirth.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblBirth.setBounds(32, 230, 92, 34);
		info.add(lblBirth);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblEmail.setBounds(32, 275, 59, 34);
		info.add(lblEmail);
		
		JLabel lblCnum = new JLabel("Contact Number:");
		lblCnum.setForeground(Color.BLACK);
		lblCnum.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCnum.setBounds(32, 320, 155, 34);
		info.add(lblCnum);
		
		JLabel lblGuardianInformation = new JLabel("GUARDIAN INFORMATION");
		lblGuardianInformation.setForeground(Color.BLACK);
		lblGuardianInformation.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblGuardianInformation.setBounds(32, 392, 345, 42);
		info.add(lblGuardianInformation);
		
		JLabel lblGuardianId = new JLabel("Guardian ID:");
		lblGuardianId.setForeground(Color.BLACK);
		lblGuardianId.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblGuardianId.setBounds(32, 445, 115, 34);
		info.add(lblGuardianId);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setForeground(Color.BLACK);
		lblName_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblName_1.setBounds(32, 490, 66, 34);
		info.add(lblName_1);
		
		JLabel lblGemail = new JLabel("Email:");
		lblGemail.setForeground(Color.BLACK);
		lblGemail.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblGemail.setBounds(32, 535, 59, 34);
		info.add(lblGemail);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setForeground(Color.BLACK);
		lblContactNumber.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblContactNumber.setBounds(32, 580, 160, 34);
		info.add(lblContactNumber);
		
		JLabel id = new JLabel(""+sId);
		id.setForeground(Color.BLACK);
		id.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		id.setBounds(146, 95, 155, 34);
		info.add(id);
		
		JLabel name = new JLabel(Name);
		name.setForeground(Color.BLACK);
		name.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		name.setBounds(101, 140, 391, 34);
		info.add(name);
		
		JLabel address = new JLabel(Address);
		address.setForeground(Color.BLACK);
		address.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		address.setBounds(125, 185, 231, 34);
		info.add(address);
		
		JLabel birth = new JLabel(sbirth);
		birth.setForeground(Color.BLACK);
		birth.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		birth.setBounds(134, 230, 231, 34);
		info.add(birth);
		
		JLabel email = new JLabel(Email);
		email.setForeground(Color.BLACK);
		email.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		email.setBounds(101, 275, 298, 34);
		info.add(email);
		
		JLabel Cnum = new JLabel(sCnum);
		Cnum.setForeground(Color.BLACK);
		Cnum.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Cnum.setBounds(197, 320, 215, 34);
		info.add(Cnum);
		
		JLabel gID = new JLabel(""+gid);
		gID.setForeground(Color.BLACK);
		gID.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		gID.setBounds(157, 445, 167, 34);
		info.add(gID);
		
		JLabel gName = new JLabel(gname);
		gName.setForeground(Color.BLACK);
		gName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		gName.setBounds(125, 490, 391, 34);
		info.add(gName);
		
		JLabel gEmail = new JLabel(gemail);
		gEmail.setForeground(Color.BLACK);
		gEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		gEmail.setBounds(90, 535, 322, 34);
		info.add(gEmail);
		
		JLabel gCnum = new JLabel(gcnum);
		gCnum.setForeground(Color.BLACK);
		gCnum.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		gCnum.setBounds(189, 580, 223, 34);
		info.add(gCnum);
		
		JButton btnInfo = new JButton("Information");
		btnInfo.setForeground(Color.BLACK);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grades.setVisible(false);
				info.setVisible(true);
			}
		});
		btnInfo.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnInfo.setBounds(10, 88, 175, 38);
		side.add(btnInfo);
		 
		JButton btnGrades = new JButton("Grades");
		btnGrades.setForeground(Color.BLACK);
		btnGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.setVisible(false);
				grades.setVisible(true);
			}
		});
		btnGrades.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnGrades.setBounds(10, 137, 175, 38);
		side.add(btnGrades);
	}
}