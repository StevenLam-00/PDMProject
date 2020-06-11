package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JTextField;
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

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField txtcusid;
	private JTextField txtusername;
	private JTextField txtcusname;
	private JTextField txtemail;
	private JTextField txtmobile;
	private JPasswordField txtpass;
	private JPasswordField txtconpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection con;
	PreparedStatement pst;

	
	public void autoID() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery("select Max(cus_id) as maxcusid from Customer");
            rs.next();
            rs.getString("maxcusid");

            if (rs.getString("maxcusid")== null) 
            {
                txtcusid.setText("C0001");
            } 
            else 
            {
              long id = Long.parseLong(rs.getString("maxcusid").substring(2,rs.getString("maxcusid").length()));
              id++;
              txtcusid.setText("C0" + String.format("%03d", id));
            }
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Registration() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.setBorder(new TitledBorder(null, "Customer Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 29, 405, 362);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(50, 35, 105, 21);
		panel.add(lblNewLabel);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerName.setBounds(50, 215, 118, 21);
		panel.add(lblCustomerName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(50, 80, 105, 21);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(50, 125, 105, 21);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirmPassword.setBounds(50, 170, 136, 21);
		panel.add(lblConfirmPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(50, 260, 105, 21);
		panel.add(lblEmail);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMobile.setBounds(50, 305, 105, 21);
		panel.add(lblMobile);
		
		txtcusid = new JTextField();
		txtcusid.setBounds(200, 35, 168, 25);
		panel.add(txtcusid);
		txtcusid.setColumns(10);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(200, 80, 168, 25);
		panel.add(txtusername);
		
		txtcusname = new JTextField();
		txtcusname.setColumns(10);
		txtcusname.setBounds(200, 215, 168, 25);
		panel.add(txtcusname);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(200, 260, 168, 25);
		panel.add(txtemail);
		
		txtmobile = new JTextField();
		txtmobile.setColumns(10);
		txtmobile.setBounds(200, 305, 168, 25);
		panel.add(txtmobile);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(200, 125, 168, 25);
		panel.add(txtpass);
		
		txtconpass = new JPasswordField();
		txtconpass.setBounds(200, 170, 168, 25);
		panel.add(txtconpass);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String cusid = txtcusid.getText();
				String username = txtusername.getText();
				String password = txtpass.getText();
				String conpass = txtconpass.getText();
				String name = txtcusname.getText();
				String email = txtemail.getText();
				String mobile = txtmobile.getText();
				
				if (password.equals(conpass))
				{
					try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					pst = con.prepareStatement("insert into Customer(cus_id, username, pass, cus_name, cus_email, cus_mobile) values(?,?,?,?,?,?)");
					
					pst.setString(1, cusid);
					pst.setString(2, username);
					pst.setString(3, password);
					pst.setString(4, name);
					pst.setString(5, email);
					pst.setString(6, mobile);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Registered Successfully!!!");
					
					dispose();
					Login l = new Login();
					l.setVisible(true);
					
					   
					} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Confirm password is not matched!!!");
				}
			}
			});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegister.setBounds(241, 402, 148, 34);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login l = new Login();
				l.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(53, 402, 148, 34);
		contentPane.add(btnCancel);
		
		autoID();
	}
}
