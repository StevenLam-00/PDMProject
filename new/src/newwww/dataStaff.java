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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dataStaff extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	Connection con;
	PreparedStatement pst;
	private JTextField txtstaffid;
	private JTextField txtusername;
	private JTextField txtpass;
	private JTextField txtstaffname;
	public void table_update()
	{
		int c;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
			pst = con.prepareStatement("select * from Staff");
			ResultSet rs = pst.executeQuery();
			
			ResultSetMetaData rd = rs.getMetaData();
			c = rd.getColumnCount();
			DefaultTableModel df = (DefaultTableModel)table.getModel();
			df.setRowCount(0);
			
			while(rs.next()) {
				
				Vector v2 = new Vector();
				
				for (int i = 1 ; i < c ; i++)
				{
					v2.add(rs.getString("staff_id"));
					v2.add(rs.getString("username"));
					v2.add(rs.getString("pass"));
					v2.add(rs.getString("staff_name"));
					v2.add(rs.getString("manager_id"));
		
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
	public dataStaff(String username) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 60, 602, 364);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				
				int selectIndex = table.getSelectedRow();
				
				txtstaffid.setText(d1.getValueAt(selectIndex, 0).toString());
				txtusername.setText(d1.getValueAt(selectIndex, 1).toString());
				txtpass.setText(d1.getValueAt(selectIndex, 2).toString());
				txtstaffname.setText(d1.getValueAt(selectIndex, 3).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Staff ID", "Username", "Password", "Staff Name", "Manager ID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagerMain sm = new ManagerMain(username);
				sm.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(268, 435, 139, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Staff Data");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(26, 11, 602, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Staff ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(638, 100, 67, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Username");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(638, 175, 83, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(638, 250, 83, 24);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Staff Name");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(638, 325, 93, 24);
		contentPane.add(lblNewLabel_1_3);
		
		txtstaffid = new JTextField();
		txtstaffid.setBounds(746, 100, 150, 25);
		contentPane.add(txtstaffid);
		txtstaffid.setColumns(10);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(746, 175, 150, 25);
		contentPane.add(txtusername);
		
		txtpass = new JTextField();
		txtpass.setColumns(10);
		txtpass.setBounds(746, 250, 150, 25);
		contentPane.add(txtpass);
		
		txtstaffname = new JTextField();
		txtstaffname.setColumns(10);
		txtstaffname.setBounds(746, 325, 150, 25);
		contentPane.add(txtstaffname);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				
				int selectIndex = table.getSelectedRow();
				
				try {
					
					String staffid = d1.getValueAt(selectIndex, 0).toString();
					String newusername = txtusername.getText();
					String newpass = txtpass.getText();
					String newname = txtstaffname.getText();
					
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
					
					pst = con.prepareStatement("update Staff set username=?,pass=?,staff_name=? where staff_id=?");
					
					pst.setString(1, newusername);
					pst.setString(2, newpass);
					pst.setString(3, newname);
					pst.setString(4, staffid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Edit Updated...");
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
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit.setBounds(687, 373, 139, 37);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel d1 = (DefaultTableModel)table.getModel();
				
				int selectIndex = table.getSelectedRow();
				
				String carno = d1.getValueAt(selectIndex, 0).toString();
				
				int dialogResult = JOptionPane.showConfirmDialog(null,"Do you want to delete the record?", "Warning!!", JOptionPane.YES_NO_OPTION);
				
				if (dialogResult == JOptionPane.YES_OPTION)
				{
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-22EU6NF\\SQLEXPRESS01:1433;databaseName=Assignment;integratedSecurity=true;", "sa", "123");
						
						pst = con.prepareStatement("delete from Staff where staff_id = ?");
						pst.setString(1, carno);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Staff deleted....");
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
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(687, 435, 139, 37);
		contentPane.add(btnDelete);
		table_update();
	}
}
