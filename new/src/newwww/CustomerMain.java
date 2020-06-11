package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMain extends JFrame {

	private JPanel contentPane;
	private String username;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CustomerMain(String username) {
		this.username = username;
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Welcome, Customer!!!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 37, 333, 244);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnlogout = new JButton("Logout");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login l = new Login();
				l.setVisible(true);
			}
		});
		btnlogout.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnlogout.setBounds(46, 174, 235, 37);
		panel.add(btnlogout);
		
		JButton btnRentCar = new JButton("Rent Car");
		btnRentCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RentCar r = new RentCar(username);
				r.setVisible(true);
			}
		});
		btnRentCar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRentCar.setBounds(46, 38, 235, 37);
		panel.add(btnRentCar);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChangePW c = new ChangePW(username);
				c.setVisible(true);
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChangePassword.setBounds(46, 105, 235, 37);
		panel.add(btnChangePassword);
	}

}
