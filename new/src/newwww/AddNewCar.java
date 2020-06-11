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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddNewCar extends JFrame {

	private JPanel contentPane;
	private JTextField txtcarid;
	private JTextField txtcarbrand;
	private JTextField txtstaffid;
	private String username;

	/**
	 * Launch the application.
	 */
	
	
	Connection con;
	private JTextField txtprice;

	
	public void autoID() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery("select Max(car_id) as max from Car");
            rs.next();
            rs.getString("max");

            if (rs.getString("max")== null) 
            {
                txtcarid.setText("V0001");
            } 
            else 
            {
              long id = Long.parseLong(rs.getString("max").substring(2,rs.getString("max").length()));
              id++;
              txtcarid.setText("V0" + String.format("%03d", id));
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
	public AddNewCar(String username) {
		this.username=username;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.setBorder(new TitledBorder(null, "Customer Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 29, 405, 296);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(50, 35, 105, 21);
		panel.add(lblNewLabel);
		
		JLabel lblCustomerName = new JLabel("Staff ID");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerName.setBounds(50, 215, 118, 21);
		panel.add(lblCustomerName);
		
		JLabel lblUsername = new JLabel("Car Brand");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(50, 80, 105, 21);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Car Price");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(50, 125, 105, 21);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Car Type");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirmPassword.setBounds(50, 170, 136, 21);
		panel.add(lblConfirmPassword);
		
		txtcarid = new JTextField();
		txtcarid.setBounds(200, 35, 168, 25);
		panel.add(txtcarid);
		txtcarid.setColumns(10);
		
		txtcarbrand = new JTextField();
		txtcarbrand.setColumns(10);
		txtcarbrand.setBounds(200, 80, 168, 25);
		panel.add(txtcarbrand);
		
		txtstaffid = new JTextField();
		txtstaffid.setColumns(10);
		txtstaffid.setBounds(200, 215, 168, 25);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			PreparedStatement pst10 = con.prepareStatement("select * from Staff where username =?");
			pst10.setString(1, username);
			ResultSet rs10 = pst10.executeQuery();
			
			while(rs10.next()) {
				String staffid = rs10.getString("staff_id");
				txtstaffid.setText(staffid.trim());
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panel.add(txtstaffid);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(200, 125, 168, 25);
		panel.add(txtprice);
		
		JComboBox cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(new String[] {"2", "4", "7"}));
		cbType.setBounds(200, 170, 32, 25);
		panel.add(cbType);
		
		JLabel lblSeats = new JLabel("Seats");
		lblSeats.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSeats.setBounds(259, 170, 90, 21);
		panel.add(lblSeats);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAvailable.setBounds(50, 260, 118, 21);
		panel.add(lblAvailable);
		
		JComboBox cbAvail = new JComboBox();
		cbAvail.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		cbAvail.setBounds(200, 260, 60, 25);
		panel.add(cbAvail);
		
		JButton btnCreate = new JButton("Create Car");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String carid = txtcarid.getText();
				String brand = txtcarbrand.getText();
				String price = txtprice.getText();
				String type = cbType.getSelectedItem().toString();
				String staffid = txtstaffid.getText();
				String avail = cbAvail.getSelectedItem().toString();
				
					try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					PreparedStatement pst = con.prepareStatement("insert into Car(car_id, car_brand, car_price, car_type, staff_id, available) values(?,?,?,?,?,?)");
					
					pst.setString(1, carid);
					pst.setString(2, brand);
					pst.setString(3, price);
					pst.setString(4, type);
					pst.setString(5, staffid);
					pst.setString(6, avail);
					//pst.setString(7, "");
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Added Successfully!!!");
					
					dispose();
					CarInvetory ci = new CarInvetory(username);
					ci.setVisible(true);
					
					   
					} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
					
			
			}
			});
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCreate.setBounds(239, 356, 148, 34);
		contentPane.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				StaffMain SM = new StaffMain(username);
				SM.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(49, 356, 148, 34);
		contentPane.add(btnCancel);
		
		autoID();
	}
}
