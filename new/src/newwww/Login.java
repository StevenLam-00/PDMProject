package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Point;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setUndecorated(true);
		setTitle("Car Rental System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Car Rental System", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(38, 24, 369, 166);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(49, 47, 90, 29);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(49, 112, 90, 29);
		panel.add(lblPassword);
		
		txtusername = new JTextField();   
		txtusername.setBounds(175, 47, 150, 30);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(175, 112, 150, 30);
		panel.add(txtpassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					Statement s1 = con.createStatement();
					ResultSet rs1 = s1.executeQuery("select * from Staff");
					
					Statement s2 = con.createStatement();
					ResultSet rs2 = s2.executeQuery("select * from Customer");
					
					Statement s3 = con.createStatement();
					ResultSet rs3 = s3.executeQuery("select * from Manager");
					
					int flag = 0;
					
					while(rs1.next()) {
						if(rs1.getString("username").equals(txtusername.getText()) && rs1.getString("pass").equals(txtpassword.getText()))
						{
							dispose();
							StaffMain sm = new StaffMain(txtusername.getText());
							sm.setVisible(true);
							flag = 1;
							break;
						}
					}
					while(rs2.next()) {
						if(rs2.getString("username").equals(txtusername.getText()) && rs2.getString("pass").equals(txtpassword.getText()))
						{
							dispose();
							CustomerMain cm = new CustomerMain(txtusername.getText());
							cm.setVisible(true);
							flag = 1;
							break;
						}
					}
					while(rs3.next()) {
						if(rs3.getString("username").equals(txtusername.getText()) && rs3.getString("pass").equals(txtpassword.getText()))
						{
							dispose();
							ManagerMain mm = new ManagerMain(txtusername.getText());
							mm.setVisible(true);
							flag = 1;
							break;
						}
					}
					if (flag == 0) {
					JOptionPane.showMessageDialog(null, "Username or Password is incorrect!!!");
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
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(172, 220, 101, 36);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(306, 220, 101, 36);
		contentPane.add(btnCancel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registration r = new Registration();
				r.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegister.setBounds(38, 220, 101, 36);
		contentPane.add(btnRegister);
	}
}
