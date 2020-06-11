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

public class CreateStaff extends JFrame {

	private JPanel contentPane;
	private JTextField txtstaffid;
	private JTextField txtusername;
	private JTextField txtstaffname;
	private JPasswordField txtpass;
	private JPasswordField txtconpass;
	private String mid;
	/**
	 * Launch the application.
	 */
	
	Connection con;

	
	public void autoID() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery("select Max(staff_id) as max from Staff");
            rs.next();
            rs.getString("max");

            if (rs.getString("max")== null) 
            {
                txtstaffid.setText("S0001");
            } 
            else 
            {
              long id = Long.parseLong(rs.getString("max").substring(2,rs.getString("max").length()));
              id++;
              txtstaffid.setText("S0" + String.format("%03d", id));
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
	public CreateStaff(String username) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.setBorder(new TitledBorder(null, "Customer Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 29, 405, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Staff ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(50, 35, 105, 21);
		panel.add(lblNewLabel);
		
		JLabel lblCustomerName = new JLabel("Staff Name");
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
		
		txtstaffid = new JTextField();
		txtstaffid.setBounds(200, 35, 168, 25);
		panel.add(txtstaffid);
		txtstaffid.setColumns(10);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(200, 80, 168, 25);
		panel.add(txtusername);
		
		txtstaffname = new JTextField();
		txtstaffname.setColumns(10);
		txtstaffname.setBounds(200, 215, 168, 25);
		panel.add(txtstaffname);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(200, 125, 168, 25);
		panel.add(txtpass);
		
		txtconpass = new JPasswordField();
		txtconpass.setBounds(200, 170, 168, 25);
		panel.add(txtconpass);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			PreparedStatement pst = con.prepareStatement("select * from Manager where  username=?");
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()==false)
			{
				JOptionPane.showMessageDialog(null, "Customer No Not Found!!");
			}
			else 
			{
				mid = rs.getString("manager_id");
			}
			
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnCreate = new JButton("Create account");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String cusid = txtstaffid.getText();
				String Musername = txtusername.getText();
				String password = txtpass.getText();
				String conpass = txtconpass.getText();
				String name = txtstaffname.getText();
				
				if (password.equals(conpass))
				{
					try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					PreparedStatement pst = con.prepareStatement("insert into Staff(staff_id, username, pass, staff_name, manager_id) values(?,?,?,?,?)");
					
					pst.setString(1, cusid);
					pst.setString(2, Musername);
					pst.setString(3, password);
					pst.setString(4, name);
					pst.setString(5, mid.trim());
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Created Successfully!!!");
					
					dispose();
					ManagerMain MM = new ManagerMain(username);
					MM.setVisible(true);
					
					   
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
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreate.setBounds(239, 309, 148, 34);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				ManagerMain MM = new ManagerMain(username);
				MM.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(49, 309, 148, 34);
		contentPane.add(btnCancel);
		
		autoID();
	}
}
