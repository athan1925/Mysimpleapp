import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;


public class LogIn extends JFrame {

	private JPanel contentPane;
	Connection connlogin=null;
	private JTextField tfUN;
	private JPasswordField pfPass;
	private JButton btnLogIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		setTitle("Log In");
		connlogin=DbSql.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUN = new JTextField();
		tfUN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				if (tfUN.getText().length() >= 1) {
					btnLogIn.setEnabled(true);
	            } else {
	            	btnLogIn.setEnabled(false);
	            }
		
			}
		});
		tfUN.setBounds(159, 42, 136, 27);
		contentPane.add(tfUN);
		tfUN.setColumns(10);
		
		pfPass = new JPasswordField();
		pfPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){ try{
				 String query="select * from Student where Username=? and Password=?  ";
					PreparedStatement pst=connlogin.prepareStatement(query);
					pst.setString(1, tfUN.getText());
					pst.setString(2, pfPass.getText());
					
					ResultSet rs=pst.executeQuery();
					int count = 0;
					while (rs.next()){
						count=count+1;
						
					}
					if(count ==1)
					{
						//JOptionPane.showMessageDialog(null,"Username and Password is Connected");
						dispose();
						Menu mm=new Menu();
						mm.setVisible(true);
						
						
					
					}
					else if (count>1)
					{
						JOptionPane.showMessageDialog(null,"Duplicate Username and Password ");
					}
					else{
						JOptionPane.showMessageDialog(null,"Not Connected wrong information");
						
					}
					rs.close();
					pst.close();
					
				}catch (Exception e)
				{
					
					JOptionPane.showMessageDialog(null,e);
					
				
			    }}
			}
		});
		pfPass.setBounds(159, 89, 136, 27);
		contentPane.add(pfPass);
		
		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 try{
					 String query="select * from Student where Username=? and Password=?  ";
						PreparedStatement pst=connlogin.prepareStatement(query);
						pst.setString(1, tfUN.getText());
						pst.setString(2, pfPass.getText());
						
						ResultSet rs=pst.executeQuery();
						int count = 0;
						while (rs.next()){
							count=count+1;
							
						}
						if(count ==1)
						{
							//JOptionPane.showMessageDialog(null,"Username and Password is Connected");
							dispose();
							
						
						}
						else if (count>1)
						{
							JOptionPane.showMessageDialog(null,"Duplicate Username and Password ");
						}
						else{
							JOptionPane.showMessageDialog(null,"Not Connected wrong information");
							
						}
						rs.close();
						pst.close();
						
					}catch (Exception e)
					{
						
						JOptionPane.showMessageDialog(null,e);
						
					
				    }
				}
			
		});
		btnLogIn.setBounds(179, 129, 89, 23);
		contentPane.add(btnLogIn);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(42, 44, 89, 22);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(42, 94, 89, 22);
		contentPane.add(lblPassword);
	}
}
