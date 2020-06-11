package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ChangePW extends JFrame {

	private JPanel contentPane;
	private String username;
	private JPasswordField txtcurpass;
	private JPasswordField txtnewpass;
	private JPasswordField txtconnewpass;
	/**
	 * Launch the application.
	 */
	Connection con;
	/**
	 * Create the frame.
	 */
	public ChangePW(String username) {
		setUndecorated(true);
		this.username=username;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Change Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(24, 27, 399, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 51, 130, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewPassowrd = new JLabel("New password");
		lblNewPassowrd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewPassowrd.setBounds(21, 107, 130, 28);
		panel.add(lblNewPassowrd);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new password");
		lblConfirmNewPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfirmNewPassword.setBounds(21, 169, 147, 28);
		panel.add(lblConfirmNewPassword);
		
		txtcurpass = new JPasswordField();
		txtcurpass.setBounds(198, 51, 175, 25);
		panel.add(txtcurpass);
		
		txtnewpass = new JPasswordField();
		txtnewpass.setBounds(198, 107, 175, 25);
		panel.add(txtnewpass);
		
		txtconnewpass = new JPasswordField();
		txtconnewpass.setBounds(198, 169, 175, 25);
		panel.add(txtconnewpass);
		
		JButton btnNewButton = new JButton("Change password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String curpassword = txtcurpass.getText();
				String newpassword = txtnewpass.getText();
				String conpassword = txtconnewpass.getText();
				
				
				
				try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
						Statement s2 = con.createStatement();
						ResultSet rs2 = s2.executeQuery("select * from Customer");
						
						int flag = 0;
						while(rs2.next()) {
							if(rs2.getString("username").equals(username) && rs2.getString("pass").equals(curpassword))
							{
								if(conpassword.equals(newpassword))
								{
									PreparedStatement pst = con.prepareStatement("update Customer set pass = ? where username =?");
									pst.setString(1, newpassword);
									pst.setString(2, username);
									pst.executeUpdate();
									JOptionPane.showMessageDialog(null, "Changed Successfully!!!");
									dispose();
									Login l = new Login();
									l.setVisible(true);
									flag=0;
									break;
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Confirm password is not matched!!!");
								}
							}
							else
							{
								flag = 1;
							}
						}
						if (flag == 1) {
							JOptionPane.showMessageDialog(null, "Current password is wrong!!!");
						}
						
						
						
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(212, 215, 136, 28);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerMain c = new CustomerMain(username);
				c.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(31, 215, 136, 28);
		panel.add(btnCancel);
	}

}
