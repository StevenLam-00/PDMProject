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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarInvetory extends JFrame {

	private JPanel contentPane;
	private JTextField txtcarid;
	private JTextField txtbrand;
	private JTextField txtprice;
	private JComboBox ComAvl;
	private JTable table1;
	private String username;

	/**
	 * Launch the application.
	 */
	Connection con;
	PreparedStatement pst;
	private JTextField txtstaffid;
	
	
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			pst = con.prepareStatement("select * from Car");
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData rd = rs.getMetaData();
			c = rd.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table1.getModel();
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
					v2.add(rs.getString("cus_id"));
					
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
	public CarInvetory(String username) {
		this.username = username;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Car Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 332, 439);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(36, 50, 96, 28);
		panel.add(lblNewLabel);
		
		txtcarid = new JTextField();
		txtcarid.setBounds(137, 50, 185, 25);
		panel.add(txtcarid);
		txtcarid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Brand");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(36, 100, 96, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(36, 150, 96, 28);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Available");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(36, 290, 96, 28);
		panel.add(lblNewLabel_3);
		
		txtbrand = new JTextField();
		txtbrand.setColumns(10);
		txtbrand.setBounds(137, 100, 185, 25);
		panel.add(txtbrand);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(137, 150, 185, 25);
		panel.add(txtprice);
		
		ComAvl = new JComboBox();
		ComAvl.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		ComAvl.setBounds(137, 294, 185, 25);
		panel.add(ComAvl);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AddNewCar anc = new AddNewCar(username);
				anc.setVisible(true);
				
			}
		});
		btnAdd.setBounds(36, 349, 96, 34);
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table1.getModel();
				
				int selectIndex = table1.getSelectedRow();
				
				String carid = d1.getValueAt(selectIndex, 0).toString();
				
				int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to delete the record?", "Warning!!", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION)
				{
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
						
						pst = con.prepareStatement("delete from Car where car_id = ?");
						pst.setString(1, carid);
						ResultSet rs = pst.executeQuery();
						
						JOptionPane.showMessageDialog(null, "record deleted....");
						table_update();
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnDelete.setBounds(36, 394, 96, 34);
		panel.add(btnDelete);
		
		JComboBox ComType = new JComboBox();
		ComType.setModel(new DefaultComboBoxModel(new String[] {"2", "4", "7"}));
		ComType.setBounds(137, 195, 185, 25);
		panel.add(ComType);
		
		txtstaffid = new JTextField();
		txtstaffid.setColumns(10);
		txtstaffid.setBounds(137, 245, 185, 25);
		panel.add(txtstaffid);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table1.getModel();
				
				int selectIndex = table1.getSelectedRow();
				
				try {
					
					String carid = d1.getValueAt(selectIndex, 0).toString();
					String newbrand = txtbrand.getText();
					String newprice = txtprice.getText();
					String newtype = ComType.getSelectedItem().toString();
					String newstaffid = txtstaffid.getText();
					String newavail = ComAvl.getSelectedItem().toString();
					
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					
					pst = con.prepareStatement("update Car set car_brand=?,car_price=?,car_type=?,staff_id=?,available=? where car_id=?");
					
					pst.setString(1, newbrand);
					pst.setString(2, newprice);
					pst.setString(3, newtype);
					pst.setString(4, newstaffid);
					pst.setString(5, newavail);
					pst.setString(6, carid);
					//ResultSet rs = pst.executeQuery();
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Updated...");
					table_update();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEdit.setBounds(179, 349, 96, 34);
		panel.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StaffMain sm = new StaffMain(username);
				sm.setVisible(true);
			}
		});
		btnCancel.setBounds(179, 394, 96, 34);
		panel.add(btnCancel);
		
		
		
		JLabel lblNewLabel_3_1 = new JLabel("Type");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(36, 200, 96, 28);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Staff ID");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(36, 239, 96, 28);
		panel.add(lblNewLabel_3_2);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 11, 574, 439);
		contentPane.add(scrollPane);
		
		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table1.getModel();
				
				int selectIndex = table1.getSelectedRow();
				
				txtcarid.setText(d1.getValueAt(selectIndex, 0).toString());
				txtbrand.setText(d1.getValueAt(selectIndex, 1).toString());
				txtprice.setText(d1.getValueAt(selectIndex, 2).toString());
				txtstaffid.setText(d1.getValueAt(selectIndex, 4).toString());
				ComAvl.setSelectedItem(d1.getValueAt(selectIndex, 3).toString());
				
				
				
			}
		});
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Car ID", "Brand", "Price", "Type", "Staff ID", "Available", "Customer ID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table1);
		
		table_update();
	}
}
