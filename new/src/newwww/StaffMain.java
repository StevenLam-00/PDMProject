package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffMain extends JFrame {

	private JPanel contentPane;
	private String username;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public StaffMain(String username) {
		this.username = username;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Hi, Staff!!!!!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(36, 42, 384, 274);
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
		btnlogout.setBounds(82, 226, 235, 37);
		panel.add(btnlogout);
		
		JButton btnSee = new JButton("See Booking Details");
		btnSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BookingDetail bd = new BookingDetail(username);
				bd.setVisible(true);
			}
		});
		btnSee.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSee.setBounds(82, 163, 235, 37);
		panel.add(btnSee);
		
		JButton btnSeeCarInventory = new JButton("See Car Inventory");
		btnSeeCarInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CarInvetory ci = new CarInvetory(username);
				ci.setVisible(true);
			}
		});
		btnSeeCarInventory.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSeeCarInventory.setBounds(82, 97, 235, 37);
		panel.add(btnSeeCarInventory);
		
		JButton btnAddNewCars = new JButton("Add new Car's Profile");
		btnAddNewCars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddNewCar anc = new AddNewCar(username);
				anc.setVisible(true);
			}
		});
		btnAddNewCars.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddNewCars.setBounds(82, 34, 235, 37);
		panel.add(btnAddNewCars);
	}

}
