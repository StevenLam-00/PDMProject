package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class ManagerMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ManagerMain(String username) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Hi, Manager!!!!", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(31, 32, 324, 356);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnlogout = new JButton("Logout");
		btnlogout.setBounds(42, 284, 235, 37);
		panel.add(btnlogout);
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login l = new Login();
				l.setVisible(true);
			}
		});
		btnlogout.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnnewstaff = new JButton("Create new staff account");
		btnnewstaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				CreateStaff cs = new CreateStaff(username);
				cs.setVisible(true);
				
			}
		});
		btnnewstaff.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnnewstaff.setBounds(42, 155, 235, 37);
		panel.add(btnnewstaff);
		
		JButton btnSeeDataOf = new JButton("See data of Staff");
		btnSeeDataOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				dataStaff ds = new dataStaff(username);
				ds.setVisible(true);
			}
		});
		btnSeeDataOf.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSeeDataOf.setBounds(42, 89, 235, 37);
		panel.add(btnSeeDataOf);
		
		JButton btnSeeDataOff = new JButton("See data of Customer");
		btnSeeDataOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				dataCustomer dc = new dataCustomer(username);
				dc.setVisible(true);
				
			}
		});
		btnSeeDataOff.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSeeDataOff.setBounds(42, 26, 235, 37);
		panel.add(btnSeeDataOff);
		
		JButton btnGetReport = new JButton("Get Report");
		btnGetReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				queryingDatabase qD = new queryingDatabase();
				qD.setVisible(true);
			}
		});
		btnGetReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGetReport.setBounds(42, 216, 235, 37);
		panel.add(btnGetReport);
	}
}
