import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFileChooser;
import javax.swing.JDesktopPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	private JPanel contentPane;
	Connection connection = DbSql.dbConnector();
	private JTextField tfSID;
	private JTextField tfName;
	private JTextField tfLName;
	private JTextField tfAge;
	private JTextField tfUN;
	private JTextField tfsearch;
	private JTable tablesearch;
	private JTable table_edit;
	private JTextField tfeditSID;
	private JTextField tfeditName;
	private JTextField tfeditLName;
	private JTextField tfeditAge;
	private JTable table_delete;
	private JTextField tfdeleteSID;
    private JTable table_add;
	private JTextField tfdeleteLName;
	private JTextField tfdeleteName;
	private JTextField tfdeleteAge;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String gender;
	private JPasswordField pfPass;
	private ImageIcon format = null;
	String filename =null;
	private JLabel labeldeleteimage;
	private JLabel labelimage;
	private JTextField tfpath;
	byte[] person_image=null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
     
			

	/**
	 * Create the frame.
	 */
	
	public void refreshTable()
	{

		try{
			String query="select SID,Name,Lastname,Age,Username,Password from Student";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table_edit.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception r){
			r.printStackTrace();
			
		}
	}
	
	public void refreshTabledel()
	{


		try{
			String query="select SID,Name,Lastname,Age from Student";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table_delete.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception r){
			r.printStackTrace();
			
		}
	}
	
	public void refreshTableadd()
	{
		try{
			String query="select SID,Name,Lastname,Age,Gender from Student";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table_add.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception r){
			r.printStackTrace();
			
		}
	}
	
	
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setTitle("Main Menu");
		connection=DbSql.dbConnector();
		setBounds(100, 100, 869, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 26, 829, 387);
		contentPane.add(tabbedPane);
		
		JPanel paneledit = new JPanel();
		tabbedPane.addTab("EDIT", null, paneledit, null);
		paneledit.setLayout(null);
		
		JButton btnNewButton = new JButton("Load data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select SID,Name,Lastname,Age,Username,Password from Student";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table_edit.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception r){
					r.printStackTrace();
					
				}
			}
		});
		btnNewButton.setBounds(349, 295, 98, 26);
		paneledit.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(235, 12, 410, 271);
		paneledit.add(scrollPane_1);
		
		table_edit = new JTable();
		table_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				 int row = table_edit.getSelectedRow();
			     String Clickmoko = (table_edit.getModel().getValueAt(row, 0).toString());
			   
				
			  
			     
					try{
			     
						String query="select Image from Student where SID='"+Clickmoko+"'";
					    PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						   if(rs.next()){
							   byte[] imagedata=rs.getBytes("Image");
							   
							   format = new ImageIcon(imagedata);
							   labelimage.setIcon(format);
							   
							   
						   }
				  }
						
					
					catch(Exception g){
						
						g.printStackTrace();
					}
			     
			     
			     
			     
				try{
		     
		      String query="select * from Student where SID='"+Clickmoko+"'";
		      
		      PreparedStatement pst=connection.prepareStatement(query);
			  ResultSet rs=pst.executeQuery();
			  if(rs.next()){
				  String add1 = rs.getString("SID");
				  tfeditSID.setText(add1);
				  String add2 = rs.getString("Name");
				  tfeditName.setText(add2);
				  String add3 = rs.getString("LastName");
				  tfeditLName.setText(add3);
				  String add4 = rs.getString("Age");
				  tfeditAge.setText(add4);
				  
			  }
					
				}
				catch(Exception g){
					
					g.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(table_edit);
		
		tfeditSID = new JTextField();
		tfeditSID.setBounds(100, 43, 98, 26);
		paneledit.add(tfeditSID);
		tfeditSID.setColumns(10);
		
		JLabel lblSid_1 = new JLabel("SID");
		lblSid_1.setBounds(40, 48, 55, 16);
		paneledit.add(lblSid_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="Update Student set SID='"+tfeditSID.getText()+"' ,Name='"+tfeditName.getText()+"' ,LastName='"+tfeditLName.getText()+"' ,Age='"+tfeditAge.getText()+"' where SID='"+tfeditSID.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Succesfully Update");
					tfeditSID.setText("");
					tfeditName.setText("");
					tfeditLName.setText("");
					tfeditAge.setText("");
					refreshTable();
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(100, 222, 98, 26);
		paneledit.add(btnUpdate);
		
		
		tfeditName = new JTextField();
		tfeditName.setColumns(10);
		tfeditName.setBounds(100, 81, 98, 26);
		paneledit.add(tfeditName);
		
		tfeditLName = new JTextField();
		tfeditLName.setColumns(10);
		tfeditLName.setBounds(100, 119, 98, 26);
		paneledit.add(tfeditLName);
		
		tfeditAge = new JTextField();
		tfeditAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					try{
					String query="Update Student set SID='"+tfeditSID.getText()+"' ,Name='"+tfeditName.getText()+"' ,LastName='"+tfeditLName.getText()+"' ,Age='"+tfeditAge.getText()+"' where SID='"+tfeditSID.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Succesfully Update");
					tfeditSID.setText("");
					tfeditName.setText("");
					tfeditLName.setText("");
					tfeditAge.setText("");
					
				}catch(Exception e){
					e.printStackTrace();
				}}
			}
		});
		tfeditAge.setColumns(10);
		tfeditAge.setBounds(100, 164, 98, 26);
		paneledit.add(tfeditAge);
		
		JLabel lblName_2 = new JLabel("Name");
		lblName_2.setBounds(40, 89, 55, 16);
		paneledit.add(lblName_2);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(12, 123, 80, 16);
		paneledit.add(lblLastName);
		
		JLabel lblName_1 = new JLabel("Age");
		lblName_1.setBounds(40, 169, 55, 16);
		paneledit.add(lblName_1);
		
		labelimage = new JLabel("");
		labelimage.setBounds(670, 12, 142, 163);
		paneledit.add(labelimage);
		
		JPanel panelsearch = new JPanel();
		tabbedPane.addTab("Search", null, panelsearch, null);
		
		tfsearch = new JTextField();
		tfsearch.setBounds(12, 40, 129, 27);
		tfsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
				
				try{
					String query="select SID,Name,LastName,Age,Gender from Student where Name=?";
					PreparedStatement pst=connection.prepareStatement(query);
				
					pst.setString(1,tfsearch.getText());
					ResultSet rs=pst.executeQuery();
					tablesearch.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception r){
					r.printStackTrace();
					
				}
				
			
			}
			
		});
		panelsearch.setLayout(null);
		panelsearch.add(tfsearch);
		tfsearch.setColumns(10);
		
		JButton btnViewData = new JButton("View Data");
		btnViewData.setBounds(295, 298, 98, 26);
		btnViewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select SID,Name,Lastname,Age,Gender from Student";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					tablesearch.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception r){
					r.printStackTrace();
					
				}
				
			}
		});
		panelsearch.add(btnViewData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 30, 447, 240);
		panelsearch.add(scrollPane);
		
		tablesearch = new JTable();
		scrollPane.setViewportView(tablesearch);
		
		
		
		JPanel paneldelete = new JPanel();
		tabbedPane.addTab("Delete", null, paneldelete, null);
		paneldelete.setLayout(null);
		
		JButton btnViewDataTable = new JButton("View Data Table");
		btnViewDataTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query="select SID,Name,Lastname,Age from Student";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table_delete.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception r){
					r.printStackTrace();
					
				}
			}
		});
		btnViewDataTable.setBounds(291, 281, 138, 26);
		paneldelete.add(btnViewDataTable);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(171, 34, 432, 235);
		paneldelete.add(scrollPane_2);
		
		table_delete = new JTable();
		table_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 int row = table_delete.getSelectedRow();
			     String Clickmoko = (table_delete.getModel().getValueAt(row, 0).toString());

					try{
			     
						String query="select Image from Student where SID='"+Clickmoko+"'";
					    PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						   if(rs.next()){
							   byte[] imagedata=rs.getBytes("Image");
							   
							   format = new ImageIcon(imagedata);
							   labeldeleteimage.setIcon(format);
							   
							   
						   }
				  }
						
					
					catch(Exception g){
						
						g.printStackTrace();
					}
			     
				try{

				
				      String query="select * from Student where SID='"+Clickmoko+"'";
				      
				      PreparedStatement pst=connection.prepareStatement(query);
					  ResultSet rs=pst.executeQuery();
					  if(rs.next()){
						  String add1 = rs.getString("SID");
						  tfdeleteSID.setText(add1);
						  String add2 = rs.getString("Name");
						  tfdeleteName.setText(add2);
						  String add3 = rs.getString("LastName");
						  tfdeleteLName.setText(add3);
						  String add4 = rs.getString("Age");
						  tfdeleteAge.setText(add4);
						  
					  }
							
						}
						catch(Exception g){
							
							g.printStackTrace();
						}
			}
		});
		scrollPane_2.setViewportView(table_delete);
		
		tfdeleteSID = new JTextField();
		tfdeleteSID.setBounds(50, 57, 97, 26);
		paneldelete.add(tfdeleteSID);
		tfdeleteSID.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int action=JOptionPane.showConfirmDialog(null, "Are sure to Delete this Data","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try{
				String query="delete from Student where SID='"+tfdeleteSID.getText()+"' ";
				PreparedStatement pst=connection.prepareStatement(query);
				
				pst.execute();
				JOptionPane.showMessageDialog(null,"Succesfully Deleted");
				tfdeleteSID.setText("");
				tfdeleteName.setText("");
				tfdeleteLName.setText("");
				tfdeleteAge.setText("");
				refreshTabledel();
				}
				catch (Exception h)
				{
					h.printStackTrace();
				}
				}
				
			}
		});
		btnDelete.setBounds(50, 223, 98, 26);
		paneldelete.add(btnDelete);
		
		tfdeleteLName = new JTextField();
		tfdeleteLName.setColumns(10);
		tfdeleteLName.setBounds(50, 133, 97, 26);
		paneldelete.add(tfdeleteLName);
		
		tfdeleteName = new JTextField();
		tfdeleteName.setColumns(10);
		tfdeleteName.setBounds(50, 95, 97, 26);
		paneldelete.add(tfdeleteName);
		
		tfdeleteAge = new JTextField();
		tfdeleteAge.setColumns(10);
		tfdeleteAge.setBounds(50, 171, 97, 26);
		paneldelete.add(tfdeleteAge);
		
		labeldeleteimage = new JLabel("");
		labeldeleteimage.setBounds(621, 34, 191, 163);
		paneldelete.add(labeldeleteimage);
		
		JPanel paneladd = new JPanel();
		tabbedPane.addTab("ADD", null, paneladd, null);
		paneladd.setLayout(null);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="insert into Student (SID,Name,LastName,Age,Username,Password,Gender,Image) values (?,?,?,?,?,?,?,?)  ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, tfSID.getText());
					pst.setString(2, tfName.getText());
					pst.setString(3, tfLName.getText());
					pst.setString(4, tfAge.getText());
					pst.setString(5, tfUN.getText());
					pst.setString(6, pfPass.getText());
					pst.setString(7, gender);
					pst.setBytes(8, person_image);
					
				   pst.execute();
				   JOptionPane.showMessageDialog(null, "Data is Saved");
				   tfSID.setText("");
				   tfName.setText("");
				   tfLName.setText("");
				   tfAge.setText("");
				   tfUN.setText("");
				   pfPass.setText("");
				   tfpath.setText("");
				   refreshTableadd();
					
				}catch(Exception r){
					
					JOptionPane.showMessageDialog(null,r);
				}
			}
		});
		btnSave.setBounds(102, 321, 98, 26);
		paneladd.add(btnSave);
		
		tfSID = new JTextField();
		tfSID.setBounds(99, 29, 118, 26);
		paneladd.add(tfSID);
		tfSID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(99, 67, 118, 26);
		paneladd.add(tfName);
		
		tfLName = new JTextField();
		tfLName.setColumns(10);
		tfLName.setBounds(99, 105, 118, 26);
		paneladd.add(tfLName);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		tfAge.setBounds(99, 144, 118, 26);
		paneladd.add(tfAge);
		
		tfUN = new JTextField();
		tfUN.setColumns(10);
		tfUN.setBounds(99, 184, 118, 26);
		paneladd.add(tfUN);
		
		JLabel lblSid = new JLabel("SID");
		lblSid.setBounds(12, 34, 55, 16);
		paneladd.add(lblSid);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(12, 72, 55, 16);
		paneladd.add(lblName);
		
		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setBounds(12, 110, 69, 16);
		paneladd.add(lblLastname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(12, 149, 55, 16);
		paneladd.add(lblAge);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 189, 69, 16);
		paneladd.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 227, 77, 16);
		paneladd.add(lblPassword);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(279, 12, 383, 208);
		paneladd.add(scrollPane_3);
		
		table_add = new JTable();
		scrollPane_3.setViewportView(table_add);
		
		JButton btnViewData_1 = new JButton("View DAta");
		btnViewData_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					try{
						String query="select SID,Name,Lastname,Age,Gender from Student";
						PreparedStatement pst=connection.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table_add.setModel(DbUtils.resultSetToTableModel(rs));
					}catch(Exception r){
						r.printStackTrace();
						
					}
			}
		});
		btnViewData_1.setBounds(444, 255, 98, 26);
		paneladd.add(btnViewData_1);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(12, 260, 77, 16);
		paneladd.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gender="Male";
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(88, 256, 69, 24);
		paneladd.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gender="Female";
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(161, 256, 85, 24);
		paneladd.add(rdbtnFemale);
		
		pfPass = new JPasswordField();
		pfPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					try{
						String query="insert into Student (SID,Name,LastName,Age,Username,Password,Gender) values (?,?,?,?,?,?,?)  ";
						PreparedStatement pst=connection.prepareStatement(query);
						pst.setString(1, tfSID.getText());
						pst.setString(2, tfName.getText());
						pst.setString(3, tfLName.getText());
						pst.setString(4, tfAge.getText());
						pst.setString(5, tfUN.getText());
						pst.setString(6, pfPass.getText());
						pst.setString(7, gender);
						
					
					   pst.execute();
					   JOptionPane.showMessageDialog(null, "Data is Saved");
					   tfSID.setText("");
					   tfName.setText("");
					   tfLName.setText("");
					   tfAge.setText("");
					   tfUN.setText("");
					   pfPass.setText("");
						
					}catch(Exception r){
						
						JOptionPane.showMessageDialog(null,r);
					}
				}
			}
		});
		pfPass.setEchoChar('*');
		pfPass.setBounds(102, 222, 115, 26);
		paneladd.add(pfPass);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(302, 369, 271, 2);
		paneladd.add(fileChooser);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(chooser);
				File f=chooser.getSelectedFile();
				String filename=f.getAbsolutePath();
				tfpath.setText(filename);
				try{
					File image=new File(filename);
					FileInputStream fis= new FileInputStream(image);
					
					ByteArrayOutputStream bos = new ByteArrayOutputStream ();
					byte[] buf= new byte[1024];
					for(int readNum; (readNum=fis.read(buf))!=-1;){
						
						bos.write(buf,0,readNum);
						
						
					}
					person_image=bos.toByteArray();
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		});
		btnBrowse.setBounds(279, 288, 98, 26);
		paneladd.add(btnBrowse);
		
		tfpath = new JTextField();
		tfpath.setBounds(22, 288, 252, 26);
		paneladd.add(tfpath);
		tfpath.setColumns(10);
	
		
		
		
		
	
	}
}
