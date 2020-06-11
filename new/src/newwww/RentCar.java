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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RentCar extends JFrame {

	private JPanel contentPane;
	private String username;
	private JTextField txtcarid;
	private JTextField txtstaffid;
	private JTextField txtcusid;
	private JTextField txtcusname;
	private JTextField txtpickloca;
	private JTextField txtretnloca;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	Connection con;
	PreparedStatement pst;
	private JTextField txtbookid;
	
	public void autoID() {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery("select Max(book_id) as max from BookingDetail");
            rs.next();
            rs.getString("max");

            if (rs.getString("max")== null) 
            {
                txtbookid.setText("B0001");
            } 
            else 
            {
              long id = Long.parseLong(rs.getString("max").substring(2,rs.getString("max").length()));
              id++;
              txtbookid.setText("B0" + String.format("%03d", id));
            }
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			pst = con.prepareStatement("select * from Car");
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData rd = rs.getMetaData();
			c = rd.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				
				Vector v2 = new Vector();
				
				for (int i = 1 ; i < c ; i++)
				{
					v2.add(rs.getString("car_id"));
					v2.add(rs.getString("car_brand"));
					v2.add(rs.getString("car_price"));
					v2.add(rs.getString("car_type"));
					v2.add(rs.getString("staff_id"));
					v2.add(rs.getString("available"));
				}
				df.addRow(v2);
			}
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Create the frame.
	 */
	public RentCar(String username) {
		setUndecorated(true);
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 28, 417, 376);
		panel.setBorder(new TitledBorder(null, "Rental", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(36, 30, 101, 28);
		panel.add(lblNewLabel);
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStaffId.setBounds(36, 70, 101, 28);
		panel.add(lblStaffId);
		
		JLabel lblNewLabel_2 = new JLabel("Customer ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(36, 110, 101, 28);
		panel.add(lblNewLabel_2);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCustomerName.setBounds(36, 150, 123, 28);
		panel.add(lblCustomerName);
		
		JLabel lblPickUpDate = new JLabel("Pick up Date");
		lblPickUpDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPickUpDate.setBounds(36, 190, 101, 28);
		panel.add(lblPickUpDate);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnDate.setBounds(36, 230, 101, 28);
		panel.add(lblReturnDate);
		
		JLabel lblPickupLocation = new JLabel("Pickup location");
		lblPickupLocation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPickupLocation.setBounds(36, 270, 123, 28);
		panel.add(lblPickupLocation);
		
		JLabel lblReturnLocation = new JLabel("Return location");
		lblReturnLocation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReturnLocation.setBounds(36, 310, 123, 28);
		panel.add(lblReturnLocation);
		
		txtcarid = new JTextField();
		txtcarid.setBounds(196, 30, 200, 25);
		panel.add(txtcarid);
		txtcarid.setColumns(10);
		
		txtstaffid = new JTextField();
		txtstaffid.setColumns(10);
		txtstaffid.setBounds(196, 70, 200, 25);
		panel.add(txtstaffid);
		
		txtcusid = new JTextField();
		txtcusid.setColumns(10);
		txtcusid.setBounds(196, 110, 200, 25);
		panel.add(txtcusid);
		
		txtcusname = new JTextField();
		txtcusname.setColumns(10);
		txtcusname.setBounds(196, 150, 200, 25);
		panel.add(txtcusname);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			PreparedStatement pst = con.prepareStatement("select * from Customer where  username=?");
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()==false)
			{
				JOptionPane.showMessageDialog(null, "Customer No Not Found!!");
			}
			else 
			{
				String cusname = rs.getString("cus_name");
				txtcusname.setText(cusname.trim());
				String cusid = rs.getString("cus_id");
				txtcusid.setText(cusid.trim());
			}
			
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		txtpickloca = new JTextField();
		txtpickloca.setColumns(10);
		txtpickloca.setBounds(196, 270, 200, 25);
		panel.add(txtpickloca);
		
		txtretnloca = new JTextField();
		txtretnloca.setColumns(10);
		txtretnloca.setBounds(196, 310, 200, 25);
		panel.add(txtretnloca);
		
		JDateChooser txtpickupdate = new JDateChooser();
		txtpickupdate.setBounds(196, 190, 200, 25);
		panel.add(txtpickupdate);
		
		JDateChooser txtreturndate = new JDateChooser();
		txtreturndate.setBounds(196, 230, 200, 25);
		panel.add(txtreturndate);
		
		txtbookid = new JTextField();
		txtbookid.setBounds(136, 345, 86, 20);
		panel.add(txtbookid);
		txtbookid.setColumns(10);
		
		JButton btnNewButton = new JButton("Rent");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String bookid = txtbookid.getText();
					String carid = txtcarid.getText();
					String staffid = txtstaffid.getText();
					String cusid = txtcusid.getText();
					String cusname = txtcusname.getText();
					
					SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
					String pickupdate = Date_Format.format(txtpickupdate.getDate());
					
					SimpleDateFormat Due_Format1 = new SimpleDateFormat("yyyy-MM-dd");
					String returndate = Due_Format1.format(txtreturndate.getDate());
					
					String pickloca = txtpickloca.getText();
					String retnloca = txtretnloca.getText();
					
					
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					PreparedStatement pst = con.prepareStatement("insert into BookingDetail(book_id, car_id, staff_id, cus_id, cus_name, pickupdate, returndate, pickuplocation, returnlocation) values(?,?,?,?,?,?,?,?,?)" );
					pst.setString(1, bookid);
					pst.setString(2, carid);
					pst.setString(3, staffid);
					pst.setString(4, cusid);
					pst.setString(5, cusname);
					
					pst.setString(6, pickupdate);
					pst.setString(7, returndate);
					pst.setString(8, pickloca);
					pst.setString(9, retnloca);
					
					pst.executeUpdate();
					
					PreparedStatement pst1 = con.prepareStatement("update Car set available='No', cus_id =? where car_id = ?");
					pst1.setString(1, cusid);
					pst1.setString(2, carid);
					pst1.executeUpdate();
					
					dispose();
					CustomerMain c = new CustomerMain(username);
					c.setVisible(true);
					
					JOptionPane.showMessageDialog(null, "Rented Successfully!!");
					
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(229, 426, 171, 40);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(40, 426, 171, 40);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerMain c = new CustomerMain(username);
				c.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 27, 554, 459);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				int selectIndex = table.getSelectedRow();
				
				txtstaffid.setText(d1.getValueAt(selectIndex, 4).toString());
				txtcarid.setText(d1.getValueAt(selectIndex, 0).toString());
				
				String carid = txtcarid.getText();
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					
					PreparedStatement pst = con.prepareStatement("select * from Car where car_id = ?");
					pst.setString(1, carid);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==false)
					{
						JOptionPane.showMessageDialog(null, "Car No Not Found!!");
					}
					else 
					{
						String aval = rs.getString("available");
						
						if (aval.trim().equals("No"))
						{
							txtcusid.setEnabled(false);
							txtcusname.setEnabled(false);
							txtpickupdate.setEnabled(false);
							txtreturndate.setEnabled(false);
							txtpickloca.setEnabled(false);
							txtretnloca.setEnabled(false);
						}
						else
						{
							txtcusid.setEnabled(true);
							txtcusname.setEnabled(true);
							txtpickupdate.setEnabled(true);
							txtreturndate.setEnabled(true);
							txtpickloca.setEnabled(true);
							txtretnloca.setEnabled(true);
						}
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Car ID", "Brand", "Price", "Type", "Staff ID", "Available"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		autoID();
		table_update();
	}
}
