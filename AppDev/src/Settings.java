import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class Settings {

	JFrame settings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
					window.settings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		settings = new JFrame();
		settings.setResizable(false);
		settings.setTitle("STUDENT PERFORMANCE TRACKER - Settings");
		settings.setBounds(500, 225, 900, 570);
		settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		settings.getContentPane().setLayout(null);
		
		JPanel main = new JPanel();
		main.setBounds(202, 47, 682, 484);
		settings.getContentPane().add(main);
		main.setLayout(null);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(0, 47, 203, 484);
		settings.getContentPane().add(sidePanel);
		sidePanel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(10, 52, 183, 40);
		sidePanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(10, 103, 183, 40);
		sidePanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(10, 154, 183, 40);
		sidePanel.add(btnNewButton_2);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settings.dispose();
			}
		});
		lblBack.setForeground(Color.BLACK);
		lblBack.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblBack.setIcon(new ImageIcon("img\\back.png"));
		lblBack.setBounds(10, 11, 68, 24);
		sidePanel.add(lblBack);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 884, 47);
		settings.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Settings");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(338, 5, 208, 35);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		topPanel.add(lblNewLabel);
	}
}
