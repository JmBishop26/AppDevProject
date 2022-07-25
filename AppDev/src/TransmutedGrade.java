import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class TransmutedGrade {

	JFrame EquiTable;
	private JTable list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransmutedGrade window = new TransmutedGrade();
					window.EquiTable.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TransmutedGrade() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		EquiTable = new JFrame();
		EquiTable.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\JM Obispo\\eclipse-workspace\\AppDev\\img\\SP.png"));
		EquiTable.setTitle("STUDENT PERFORMANCE TRACKER - Transmuted Grade Equivalents");
		EquiTable.setBounds(430, 150, 810, 720);
		EquiTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EquiTable.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 794, 681);
		EquiTable.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(145, 143, 496, 503);
		panel.add(scroll);
		
		JLabel lblTitle = new JLabel("Transmuted Grades Equivalents");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblTitle.setBounds(145, 77, 496, 39);
		panel.add(lblTitle);
		
		list = new JTable();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		list.setRowHeight(28);
		scroll.setViewportView(list);
		list.setModel(new DefaultTableModel(
			new Object[][] {
				{"100", "100"},
				{"98.40 - 99.99", "99"},
				{"96.80 - 98.39", "98"},
				{"95.20 - 96.79", "97"},
				{"93.60 - 95.19", "96"},
				{"92.00 - 93.59", "95"},
				{"90.40 - 91.99", "94"},
				{"88.80 - 90.39", "93"},
				{"87.20 - 88.79", "92"},
				{"85.60 - 97.19", "91"},
				{"84.00 - 85.59", "90"},
				{"82.40 - 83.99", "89"},
				{"80.80 - 82.39", "88"},
				{"79.20 - 80.79", "87"},
				{"77.60 - 79.19", "86"},
				{"76.00 - 77.59", "85"},
				{"74.40 - 75.99", "84"},
				{"72.80 - 74.39", "83"},
				{"71.20 - 72.79", "82"},
				{"69.60 - 71.19", "81"},
				{"68.00 - 69.59", "80"},
				{"66.40 - 67.99", "79"},
				{"64.80 - 66.39", "78"},
				{"63.20 - 64.79", "77"},
				{"61.60 - 63.19", "76"},
				{"60.00 - 61.59", "75"},
				{"56.00 - 59.99", "74"},
				{"52.00 - 55.99", "73"},
				{"48.00 - 51.99", "72"},
				{"44.00 - 47.99", "71"},
				{"40.00 - 43.99", "70"},
				{"36.00 - 39.99", "69"},
				{"32.00 - 35.99", "68"},
				{"28.00 - 31.99", "67"},
				{"24.00 - 27.99", "66"},
				{"20.00 - 23.99", "65"},
				{"16.00 - 19.99", "64"},
				{"12.00 - 15.99", "63"},
				{"8.00 - 11.99", "62"},
				{"4.00 - 7.99", "61"},
				{"0.00 - 3.99", "60"},
			},
			new String[] {
				"INITIAL GRADE", "TRANSMUTED GRADE"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel Back = new JLabel("Back");
		Back.setForeground(Color.BLACK);
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EquiTable.dispose();
			}
		});
		Back.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		Back.setIcon(new ImageIcon("img\\back.png"));
		Back.setBounds(47, 34, 82, 28);
		panel.add(Back);
		list.getColumnModel().getColumn(0).setResizable(false);
		list.getColumnModel().getColumn(0).setPreferredWidth(130);
		list.getColumnModel().getColumn(0).setMinWidth(130);
		list.getColumnModel().getColumn(1).setResizable(false);
		list.getColumnModel().getColumn(1).setPreferredWidth(130);
		list.getColumnModel().getColumn(1).setMinWidth(130);
		
		JTableHeader header = new JTableHeader();
		header = list.getTableHeader();
		Font head = new Font("Segoe UI Black", Font.PLAIN, 20);
		header.setFont(head);
		header.setForeground(Color.BLACK);
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment( JLabel.CENTER );
		list.setDefaultRenderer(String.class, center);
	}
}
