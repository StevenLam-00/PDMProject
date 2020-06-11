package newwww;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingDetail extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	Connection con;
	PreparedStatement pst;
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			pst = con.prepareStatement("select * from BookingDetail");
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData rd = rs.getMetaData();
			c = rd.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				
				Vector v2 = new Vector();
				
				for (int i = 1 ; i < c ; i++)
				{
					v2.add(rs.getString("book_id"));
					v2.add(rs.getString("car_id"));
					v2.add(rs.getString("staff_id"));
					v2.add(rs.getString("cus_id"));
					v2.add(rs.getString("cus_name"));
					v2.add(rs.getDate("pickupdate").toString());
					v2.add(rs.getDate("returndate").toString());
					v2.add(rs.getString("pickuplocation"));
					v2.add(rs.getString("returnlocation"));
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
	public BookingDetail(String username) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 60, 856, 364);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Booking ID", "Car ID", "Staff ID", "Customer ID", "Customer Name", "Pickup Date", "Return Date", "Pickup Location", "Return Location"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(96);
		table.getColumnModel().getColumn(7).setMinWidth(20);
		table.getColumnModel().getColumn(8).setPreferredWidth(92);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StaffMain sm = new StaffMain(username);
				sm.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(418, 445, 139, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Booking Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(334, 11, 273, 38);
		contentPane.add(lblNewLabel);
		table_update();
	}
}
