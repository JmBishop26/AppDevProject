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
import java.util.Map;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Cursor;
import javax.swing.JTabbedPane;

import java.awt.BasicStroke;
import java.awt.BorderLayout;

public class Grades {

	JFrame Report;
	private static final String user = "root";
	private static final String pass = "root";
	private static final String con = "jdbc:mysql://localhost:3306/appdev";
	Connection connect = null,connect2 = null;
	PreparedStatement ps = null, ps2 = null;
	ResultSet rs = null, rs2 = null;
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
//variables for grades
	String grading;
	String [] choices = {"Choose Grading Period", "First Grading", "Second Grading", "Third Grading", "Fourth Grading"};
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
//variables for chart
	private DefaultCategoryDataset data, line;
	private JFreeChart chart, chart1;
	private CategoryPlot plot, plot1;
	private ChartPanel chartpan, linepan;
	double assigns1, quizzes1, exams1;
	double assigns2, quizzes2, exams2;
	double assigns3, quizzes3, exams3;
	double assigns4, quizzes4, exams4;
	double first, second,third, fourth;
	double init1, init2, init3, init4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grades window = new Grades();
					window.Report.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Grades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		
		Report = new JFrame();
		Report.getContentPane().setForeground(Color.BLACK);
		Report.setForeground(Color.BLACK);
		Report.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\SP.png"));
		Report.setTitle("STUDENT PERFORMANCE TRACKER - Grades and Reports");
		Report.setBounds(325, 150, 1280, 720);
		Report.getContentPane().setLayout(null);
		
		JPanel side = new JPanel();
		side.setForeground(Color.BLACK);
		side.setBounds(0, 0, 195, 681);
		Report.getContentPane().add(side);
		side.setLayout(null);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setForeground(Color.BLACK);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Report.dispose();
				MainMenu mm = new MainMenu();
				mm.Menu.setVisible(true);
			}
		});
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblBack.setBounds(41, 26, 92, 27);
		side.add(lblBack);
		
		final JPanel grades = new JPanel();
		grades.setForeground(Color.BLACK);
		grades.setBounds(194, 0, 1070, 681);
		Report.getContentPane().add(grades);
		grades.setVisible(false);
		grades.setLayout(null);
		
		final JPanel report = new JPanel();
		report.setBounds(194, 0, 1070, 681);
		Report.getContentPane().add(report);
		report.setLayout(null);
		report.setVisible(false);

//grades panel
				JLabel lblGrades = new JLabel("GRADES");
				lblGrades.setBounds(58, 33, 137, 36);
				lblGrades.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
				lblGrades.setForeground(Color.BLACK);
				grades.add(lblGrades);
				
				@SuppressWarnings({})
				final JComboBox comboBox = new JComboBox(choices);
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						grading = comboBox.getSelectedItem().toString();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							connect2 = DriverManager.getConnection(con,user, pass);
							
							
				        	if (grading.equals("First Grading")) {
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
				        		ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, firstGrade from grading1 where student_id = ?");
				        		ps2.setInt(1,Login.id);
				            	rs2 = ps2.executeQuery();
				    			
				        	}
				        	else if (grading.equals("Second Grading")){
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
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, secondGrade from grading2 where student_id = ?");
								ps2.setInt(1,Login.id);
					        	rs2 = ps2.executeQuery();

								
				        	}
				        	else if (grading.equals("Third Grading")) {
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
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items,ass_total_score,ass_total_items,ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent,exam_weight, initgrade, thirdGrade from grading3 where student_id = ?");
								ps2.setInt(1,Login.id);
					        	rs2 = ps2.executeQuery();
								
				        	}
				        	else if (grading.equals("Fourth Grading")) {
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
								ps2 = connect2.prepareStatement("select student_id, ass1, ass1_items, ass2, ass2_items, ass_total_score, ass_total_items, ass_percent, ass_weight,"
				        				+ "quiz1, quiz1_items, quiz2, quiz2_items, quiz_total_score, quiz_total_items, quiz_percent, quiz_weight, exam, exam_items,exam_percent, exam_weight, initgrade, fourthGrade from grading4 where student_id = ?");
								ps2.setInt(1,Login.id);
					        	rs2 = ps2.executeQuery();
				        	}
				        	else {
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
				        		
				        	}
				        	
							String b,c,d,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x;
							while (rs2.next()) {
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
						}catch(Exception e1) {
							java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
						}
					}
				});
				comboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
				comboBox.setBounds(461, 89, 182, 22);
				comboBox.setSelectedIndex(0);
				grades.add(comboBox);
				
				JTableHeader header = new JTableHeader();
				Font head = new Font("Segoe UI", Font.BOLD, 12);
				header.setFont(head);
				header.setForeground(Color.BLACK);
				
				DefaultTableCellRenderer center = new DefaultTableCellRenderer();
				center.setHorizontalAlignment( JLabel.CENTER );
				
				JLabel lblAss_Score = new JLabel("Score");
				lblAss_Score.setForeground(Color.BLACK);
				lblAss_Score.setHorizontalAlignment(SwingConstants.CENTER);
				lblAss_Score.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Score.setBounds(258, 144, 51, 25);
				grades.add(lblAss_Score);
				
				JLabel lblAss_Items = new JLabel("Items");
				lblAss_Items.setForeground(Color.BLACK);
				lblAss_Items.setHorizontalAlignment(SwingConstants.CENTER);
				lblAss_Items.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Items.setBounds(334, 144, 40, 25);
				grades.add(lblAss_Items);
				
				JLabel lblAss1 = new JLabel("Assignment 1");
				lblAss1.setForeground(Color.BLACK);
				lblAss1.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAss1.setBounds(131, 177, 97, 25);
				grades.add(lblAss1);
				
				Ass1 = new JTextField("");
				Ass1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass1.setForeground(Color.BLACK);
				Ass1.setBounds(253, 181, 61, 20);
				grades.add(Ass1);
				Ass1.setColumns(10);
				
				Ass1_Item = new JTextField("");
				Ass1_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass1_Item.setForeground(Color.BLACK);
				Ass1_Item.setColumns(10);
				Ass1_Item.setBounds(324, 181, 61, 20);
				grades.add(Ass1_Item);
				
				JLabel lblAss2 = new JLabel("Assignment 2");
				lblAss2.setForeground(Color.BLACK);
				lblAss2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAss2.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss2.setBounds(131, 213, 97, 25);
				grades.add(lblAss2);
				
				Ass2 = new JTextField("");
				Ass2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass2.setForeground(Color.BLACK);
				Ass2.setColumns(10);
				Ass2.setBounds(253, 217, 61, 20);
				grades.add(Ass2);
				
				Ass2_Item = new JTextField("");
				Ass2_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass2_Item.setForeground(Color.BLACK);
				Ass2_Item.setColumns(10);
				Ass2_Item.setBounds(324, 217, 61, 20);
				grades.add(Ass2_Item);
				
				JLabel lblAss_Total = new JLabel("Total");
				lblAss_Total.setForeground(Color.BLACK);
				lblAss_Total.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAss_Total.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Total.setBounds(131, 248, 97, 25);
				grades.add(lblAss_Total);
				
				ass_score_total = new JTextField("");
				ass_score_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				ass_score_total.setForeground(Color.BLACK);
				ass_score_total.setEditable(false);
				ass_score_total.setColumns(10);
				ass_score_total.setBounds(253, 253, 61, 20);
				grades.add(ass_score_total);
				
				ass_item_total = new JTextField("");
				ass_item_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				ass_item_total.setForeground(Color.BLACK);
				ass_item_total.setEditable(false);
				ass_item_total.setColumns(10);
				ass_item_total.setBounds(324, 253, 61, 20);
				grades.add(ass_item_total);
				
				JLabel lblQuiz_Score = new JLabel("Score");
				lblQuiz_Score.setHorizontalAlignment(SwingConstants.CENTER);
				lblQuiz_Score.setForeground(Color.BLACK);
				lblQuiz_Score.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Score.setBounds(496, 144, 51, 25);
				grades.add(lblQuiz_Score);
				
				JLabel lblQuiz_Items = new JLabel("Items");
				lblQuiz_Items.setHorizontalAlignment(SwingConstants.CENTER);
				lblQuiz_Items.setForeground(Color.BLACK);
				lblQuiz_Items.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Items.setBounds(572, 144, 40, 25);
				grades.add(lblQuiz_Items);
				
				JLabel lblQuiz1 = new JLabel("Quiz 1");
				lblQuiz1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblQuiz1.setForeground(Color.BLACK);
				lblQuiz1.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz1.setBounds(405, 181, 61, 25);
				grades.add(lblQuiz1);
				
				Quiz1 = new JTextField();
				Quiz1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz1.setForeground(Color.BLACK);
				Quiz1.setColumns(10);
				Quiz1.setBounds(491, 181, 61, 20);
				grades.add(Quiz1);
				
				Quiz1_Item = new JTextField("");
				Quiz1_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz1_Item.setForeground(Color.BLACK);
				Quiz1_Item.setColumns(10);
				Quiz1_Item.setBounds(562, 181, 61, 20);
				grades.add(Quiz1_Item);
				
				JLabel lblQuiz2 = new JLabel("Quiz 2");
				lblQuiz2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblQuiz2.setForeground(Color.BLACK);
				lblQuiz2.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz2.setBounds(405, 213, 61, 25);
				grades.add(lblQuiz2);
				
				Quiz2 = new JTextField("");
				Quiz2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz2.setForeground(Color.BLACK);
				Quiz2.setColumns(10);
				Quiz2.setBounds(491, 217, 61, 20);
				grades.add(Quiz2);
				
				Quiz2_Item = new JTextField("");
				Quiz2_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz2_Item.setForeground(Color.BLACK);
				Quiz2_Item.setColumns(10);
				Quiz2_Item.setBounds(562, 217, 61, 20);
				grades.add(Quiz2_Item);
				
				JLabel lblQuiz_Total = new JLabel("Total");
				lblQuiz_Total.setHorizontalAlignment(SwingConstants.RIGHT);
				lblQuiz_Total.setForeground(Color.BLACK);
				lblQuiz_Total.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Total.setBounds(405, 248, 61, 25);
				grades.add(lblQuiz_Total);
				
				quiz_score_total = new JTextField("");
				quiz_score_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				quiz_score_total.setForeground(Color.BLACK);
				quiz_score_total.setEditable(false);
				quiz_score_total.setColumns(10);
				quiz_score_total.setBounds(491, 253, 61, 20);
				grades.add(quiz_score_total);
				
				quiz_item_total = new JTextField("");
				quiz_item_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				quiz_item_total.setForeground(Color.BLACK);
				quiz_item_total.setEditable(false);
				quiz_item_total.setColumns(10);
				quiz_item_total.setBounds(562, 253, 61, 20);
				grades.add(quiz_item_total);
				
				JLabel lblExam_Score = new JLabel("Score");
				lblExam_Score.setHorizontalAlignment(SwingConstants.CENTER);
				lblExam_Score.setForeground(Color.BLACK);
				lblExam_Score.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Score.setBounds(787, 144, 51, 25);
				grades.add(lblExam_Score);
				
				JLabel lblExam_Items = new JLabel("Items");
				lblExam_Items.setHorizontalAlignment(SwingConstants.CENTER);
				lblExam_Items.setForeground(Color.BLACK);
				lblExam_Items.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Items.setBounds(863, 144, 40, 25);
				grades.add(lblExam_Items);
				
				Exam_Score = new JTextField("");
				Exam_Score.setForeground(Color.BLACK);
				Exam_Score.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_Score.setColumns(10);
				Exam_Score.setBounds(782, 181, 61, 20);
				grades.add(Exam_Score);
				
				Exam_Item = new JTextField("");
				Exam_Item.setForeground(Color.BLACK);
				Exam_Item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_Item.setColumns(10);
				Exam_Item.setBounds(853, 181, 61, 20);
				grades.add(Exam_Item);
				
				JLabel lblExam = new JLabel("Quarterly Exam");
				lblExam.setHorizontalAlignment(SwingConstants.RIGHT);
				lblExam.setForeground(Color.BLACK);
				lblExam.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam.setBounds(642, 181, 115, 25);
				grades.add(lblExam);
				
				JLabel lblExam_Total = new JLabel("Total");
				lblExam_Total.setHorizontalAlignment(SwingConstants.RIGHT);
				lblExam_Total.setForeground(Color.BLACK);
				lblExam_Total.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Total.setBounds(696, 213, 61, 25);
				grades.add(lblExam_Total);
				
				exam_score_total = new JTextField("");
				exam_score_total.setForeground(Color.BLACK);
				exam_score_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				exam_score_total.setEditable(false);
				exam_score_total.setColumns(10);
				exam_score_total.setBounds(782, 218, 61, 20);
				grades.add(exam_score_total);
				
				exam_item_total = new JTextField("");
				exam_item_total.setForeground(Color.BLACK);
				exam_item_total.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				exam_item_total.setEditable(false);
				exam_item_total.setColumns(10);
				exam_item_total.setBounds(853, 218, 61, 20);
				grades.add(exam_item_total);
				
				JLabel lblComputation = new JLabel("COMPUTATIONS");
				lblComputation.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
				lblComputation.setBounds(58, 344, 214, 36);
				grades.add(lblComputation);
				
				JLabel lblAss_Compute = new JLabel("Assignment (25%)");
				lblAss_Compute.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblAss_Compute.setBounds(186, 400, 137, 20);
				grades.add(lblAss_Compute);
				
				JLabel lblPScore_Ass2 = new JLabel("Percentage Score:");
				lblPScore_Ass2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Ass2.setBounds(186, 449, 103, 20);
				grades.add(lblPScore_Ass2);
				
				Ass_PScore = new JTextField("");
				Ass_PScore.setEditable(false);
				Ass_PScore.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass_PScore.setBounds(295, 451, 51, 16);
				grades.add(Ass_PScore);
				
				JLabel lblPScore_Ass1 = new JLabel("PS = (Total Score / Total Item) * 100");
				lblPScore_Ass1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Ass1.setBounds(186, 431, 214, 18);
				grades.add(lblPScore_Ass1);
				
				JLabel lblWG_Ass1 = new JLabel("WG = PS * 25%");
				lblWG_Ass1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Ass1.setBounds(186, 472, 88, 20);
				grades.add(lblWG_Ass1);
				
				JLabel lblWG_Ass2 = new JLabel("Weighted Grade: ");
				lblWG_Ass2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Ass2.setBounds(186, 494, 103, 20);
				grades.add(lblWG_Ass2);
				
				Ass_WG = new JTextField("");
				Ass_WG.setEditable(false);
				Ass_WG.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Ass_WG.setBounds(289, 496, 51, 16);
				grades.add(Ass_WG);
				
				JLabel lblQuiz_Compute = new JLabel("Quizzes (35%)");
				lblQuiz_Compute.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblQuiz_Compute.setBounds(451, 400, 137, 20);
				grades.add(lblQuiz_Compute);
				
				JLabel lblPScore_Quiz1 = new JLabel("PS = (Total Score / Total Item) * 100");
				lblPScore_Quiz1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Quiz1.setBounds(451, 431, 214, 18);
				grades.add(lblPScore_Quiz1);
				
				JLabel lblPScore_Quiz2 = new JLabel("Percentage Score:");
				lblPScore_Quiz2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Quiz2.setBounds(451, 449, 103, 20);
				grades.add(lblPScore_Quiz2);
				
				Quiz_PScore = new JTextField("");
				Quiz_PScore.setEditable(false);
				Quiz_PScore.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz_PScore.setBounds(560, 451, 55, 16);
				grades.add(Quiz_PScore);
				
				JLabel lblWG_Quiz1 = new JLabel("WG = PS * 35%");
				lblWG_Quiz1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Quiz1.setBounds(451, 472, 88, 20);
				grades.add(lblWG_Quiz1);
				
				JLabel lblWG_Quiz2 = new JLabel("Weighted Grade: ");
				lblWG_Quiz2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Quiz2.setBounds(451, 494, 103, 20);
				grades.add(lblWG_Quiz2);
				
				Quiz_WG = new JTextField();
				Quiz_WG.setEditable(false);
				Quiz_WG.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Quiz_WG.setBounds(554, 496, 61, 16);
				grades.add(Quiz_WG);
				
				JLabel lblExam_Compute = new JLabel("Quarterly Exam (40%)");
				lblExam_Compute.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblExam_Compute.setBounds(727, 400, 164, 20);
				grades.add(lblExam_Compute);
				
				JLabel lblPScore_Exam1 = new JLabel("PS = (Total Score / Total Item) * 100");
				lblPScore_Exam1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Exam1.setBounds(727, 431, 214, 18);
				grades.add(lblPScore_Exam1);
				
				JLabel lblPScore_Exam2 = new JLabel("Percentage Score:");
				lblPScore_Exam2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblPScore_Exam2.setBounds(727, 449, 103, 20);
				grades.add(lblPScore_Exam2);
				
				JLabel lblWG_Exam1 = new JLabel("WG = PS * 40%");
				lblWG_Exam1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Exam1.setBounds(727, 472, 88, 20);
				grades.add(lblWG_Exam1);
				
				JLabel lblWG_Exam2 = new JLabel("Weighted Grade: ");
				lblWG_Exam2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblWG_Exam2.setBounds(727, 494, 103, 20);
				grades.add(lblWG_Exam2);
				
				Exam_WG= new JTextField("");
				Exam_WG.setEditable(false);
				Exam_WG.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_WG.setBounds(830, 496, 51, 16);
				grades.add(Exam_WG);
				
				Exam_PScore = new JTextField();
				Exam_PScore.setEditable(false);
				Exam_PScore.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				Exam_PScore.setBounds(836, 451, 51, 16);
				grades.add(Exam_PScore);
				
				JLabel lblinitGrades = new JLabel("Initial Grade");
				lblinitGrades.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lblinitGrades.setBounds(289, 544, 97, 20);
				grades.add(lblinitGrades);
				
				JLabel lblinitGrade1 = new JLabel("IG = WG(Assign) + WG(Quiz) + WG(Exam)");
				lblinitGrade1.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblinitGrade1.setBounds(289, 569, 232, 20);
				grades.add(lblinitGrade1);
				
				JLabel lblinitGrade2 = new JLabel("Initial Grade:");
				lblinitGrade2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lblinitGrade2.setBounds(289, 589, 81, 20);
				grades.add(lblinitGrade2);
				
				initGrade= new JTextField("");
				initGrade.setEditable(false);
				initGrade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				initGrade.setBounds(368, 591, 40, 16);
				grades.add(initGrade);
				
				JLabel lbltransGrades = new JLabel("Transmuted Grade");
				lbltransGrades.setFont(new Font("Segoe UI", Font.BOLD, 15));
				lbltransGrades.setBounds(600, 544, 137, 20);
				grades.add(lbltransGrades);
				
				JLabel lbltransGrade = new JLabel("TG = Equivalent (IG) in Transmuted Grades");
				lbltransGrade.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lbltransGrade.setBounds(600, 569, 243, 20);
				grades.add(lbltransGrade);
				
				JLabel lbltransGrade2 = new JLabel("Transmuted Grade:");
				lbltransGrade2.setFont(new Font("Segoe UI", Font.BOLD, 12));
				lbltransGrade2.setBounds(600, 589, 115, 20);
				grades.add(lbltransGrade2);
				
				transGrade = new JTextField("");
				transGrade.setEditable(false);
				transGrade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				transGrade.setBounds(712, 591, 40, 16);
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
				transmuted.setBounds(752, 631, 189, 20);
				grades.add(transmuted);
				
				Font font = transmuted.getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				transmuted.setFont(font.deriveFont(attributes));
					
		data = new DefaultCategoryDataset();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select g1.ass_percent, g1.quiz_percent, g1.exam_percent, g2.ass_percent, g2.quiz_percent, g2.exam_percent, g3.ass_percent, g3.quiz_percent, g3.exam_percent, g4.ass_percent, g4.quiz_percent, g4.exam_percent\r\n"
					+ "from grading1 g1 inner join grading2 g2 on g1.student_id = g2.student_id \r\n"
					+ "inner join grading3 g3 on g1.student_id = g3.student_id\r\n"
					+ "inner join grading4 g4 on g1.student_id = g4.student_id\r\n"
					+ "where g1.student_id = ?");
			ps.setInt(1, Login.id);
			rs = ps.executeQuery();
			rs.next();
			assigns1 = rs.getDouble("g1.ass_percent");
			quizzes1 = rs.getDouble("g1.quiz_percent");
			exams1 = rs.getDouble("g1.exam_percent");
			
			assigns2 = rs.getDouble("g2.ass_percent");
			quizzes2 = rs.getDouble("g2.quiz_percent");
			exams2 = rs.getDouble("g2.exam_percent");
			
			assigns3 = rs.getDouble("g3.ass_percent");
			quizzes3 = rs.getDouble("g3.quiz_percent");
			exams3 = rs.getDouble("g3.exam_percent");
			
			assigns4 = rs.getDouble("g4.ass_percent");
			quizzes4 = rs.getDouble("g4.quiz_percent");
			exams4 = rs.getDouble("g4.exam_percent");
			
			
			
		}catch(Exception e1) {
            JOptionPane.showMessageDialog(null,e1);
			java.util.logging.Logger.getLogger(GdDash.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}
		
		data.addValue(assigns1, "Assignment", "First Grading");
		data.addValue(quizzes1, "Quizzes", "First Grading");
		data.addValue(exams1, "Quarterly Examination", "First Grading");
		
		data.addValue(assigns2, "Assignment", "Second Grading");
		data.addValue(quizzes2, "Quizzes", "Second Grading");
		data.addValue(exams2, "Quarterly Examination", "Second Grading");
		
		data.addValue(assigns3, "Assignment", "Third Grading");
		data.addValue(quizzes3, "Quizzes", "Third Grading");
		data.addValue(exams3, "Quarterly Examination", "Third Grading");
		
		data.addValue(assigns4, "Assignment", "Fourth Grading");
		data.addValue(quizzes4, "Quizzes", "Fourth Grading");
		data.addValue(exams4, "Quarterly Examination", "Fourth Grading");
		
		chart = ChartFactory.createBarChart("Assessment Comparison Per Grading Period", "Activities","Grades",data, PlotOrientation.VERTICAL, true, false, false);
		plot = chart.getCategoryPlot();
		BarRenderer bar = new BarRenderer();
		plot.setRenderer(bar);
//change color of bars in bar chart		
		bar.setSeriesPaint(0, new Color(1, 119, 180));
		bar.setSeriesPaint(1, new Color(56, 7, 105));
		bar.setSeriesPaint(2, new Color(38, 24, 21));
//change color of background outline		
		plot.setOutlinePaint(new Color(203, 86, 0));
		plot.setOutlineStroke(new BasicStroke(2.0f));
//change color of background	
		plot.setBackgroundPaint(new Color(255, 204, 153));
//Gridlines color		
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
		
		line = new DefaultCategoryDataset(); 

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(con,user, pass);
			ps = connect.prepareStatement("select firstGrade, secondGrade, thirdGrade, fourthGrade, g1.initgrade, g2.initgrade, g3.initgrade, g4.initgrade \r\n"
					+ "from grading1 g1 inner join grading2 g2 on g1.student_id = g2.student_id \r\n"
					+ "inner join grading3 g3 on g1.student_id = g3.student_id\r\n"
					+ "inner join grading4 g4 on g1.student_id = g4.student_id\r\n"
					+ "where g1.student_id = ?");
			ps.setInt(1, Login.id);
			rs = ps.executeQuery();
			rs.next();
			first = rs.getDouble("firstGrade");
			second = rs.getDouble("secondGrade");
			third = rs.getDouble("thirdGrade");
			fourth = rs.getDouble("fourthGrade");
			init1 = rs.getDouble("g1.initgrade");
			init2 = rs.getDouble("g2.initgrade");
			init3 = rs.getDouble("g3.initgrade");
			init4 = rs.getDouble("g4.initgrade");

		}catch(Exception e1) {
            JOptionPane.showMessageDialog(null,e1);
			java.util.logging.Logger.getLogger(GdDash.class.getName()).log(java.util.logging.Level.SEVERE, null,e1);                  
		}
		
		line.addValue(init1, "Initial Grades", "First Grading");
		line.addValue(init2, "Initial Grades", "Second Grading");
		line.addValue(init3, "Initial Grades", "Third Grading");
		line.addValue(init4, "Initial Grades", "Fourth Grading");
		
		line.addValue(first, "Transmuted Grades", "First Grading");
		line.addValue(second, "Transmuted Grades", "Second Grading");
		line.addValue(third, "Transmuted Grades", "Third Grading");
		line.addValue(fourth, "Transmuted Grades", "Fourth Grading");
		
		chart1 = ChartFactory.createLineChart("Grades Comparison Per Grading Period", "Transmuted Grade Per Grading Period", "Grades", line, PlotOrientation.VERTICAL, true, false,false);
		plot1 = chart1.getCategoryPlot();
		
		LineAndShapeRenderer renderer = new LineAndShapeRenderer(); 
		plot1.setRenderer(renderer);
//change color of lines	and thickness of line
		renderer.setSeriesPaint(0, new Color(1, 119, 180));
		renderer.setSeriesPaint(1, new Color(56, 7, 105));
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(4.0f));
//change color of background, outline, and outline thickness 		
		plot1.setOutlinePaint(new Color(203, 86, 0));
		plot1.setOutlineStroke(new BasicStroke(2.0f));
		plot1.setBackgroundPaint(new Color(255, 204, 153));
//Gridlines color	
		plot1.setRangeGridlinesVisible(true);
		plot1.setRangeGridlinePaint(Color.BLACK);
		plot1.setDomainGridlinesVisible(true);
		plot1.setDomainGridlinePaint(Color.BLACK);
		
		
		JTabbedPane assessment = new JTabbedPane(JTabbedPane.TOP);
		assessment.setBounds(10, 11, 1050, 659);
		report.add(assessment);
		
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		assessment.addTab("Assessments Comparison Per Grading Period", null, panel1, null);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel2 = new JPanel();
		assessment.addTab("Grades Comparison Per Grading Period", null, panel2, null);
		panel2.setLayout(new BorderLayout(0, 0));
		panel1.validate();
		
		chartpan = new ChartPanel(chart);
		chartpan.setForeground(Color.BLACK);
		panel1.removeAll();
		panel1.add(chartpan, BorderLayout.CENTER);
		
		linepan = new ChartPanel(chart1);
		linepan.setForeground(Color.BLACK);
		panel2.removeAll();
		panel2.add(linepan, BorderLayout.CENTER);
		panel2.validate();

		JButton btnGrades = new JButton("Grades");
		btnGrades.setForeground(Color.BLACK);
		btnGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report.setVisible(false);
				grades.setVisible(true);
				
			}
		});
		btnGrades.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnGrades.setBounds(10, 96, 175, 38);
		side.add(btnGrades);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grades.setVisible(false);
				report.setVisible(true);
				
			}
		});
		btnReport.setForeground(Color.BLACK);
		btnReport.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnReport.setBounds(10, 145, 175, 38);
		side.add(btnReport);
		
	}
}