

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import net.miginfocom.swing.MigLayout;

public class CreateHouseDialog extends JFrame 
	
	
{
	JPanel contentPane , top , bottom;
	JButton add , cancel;
	JLabel pname , add1 , add2 , beds , baths ,price,number;
	JTextField  a1 ,a2 , a3 , a4;
	JTextField jtf1 , jtf2 ,jtf3;
	
	
	/* Code goes here */
	CreateHouseDialog(ArrayList<House> houselist)
	{
		this.setTitle("Add House Details");
		contentPane = new JPanel();
		top = new JPanel(new MigLayout());
		bottom = new JPanel();
		pname = new JLabel("Photograph file name");
		add1 = new JLabel("Address Line 1");
		add2 = new JLabel("Address Line 2");
		beds = new JLabel("Number Of Bedrooms");
		baths = new JLabel("Number Of Bathrooms"); 
		price = new JLabel("Price");
		number = new JLabel("Contact Number");
		a1 = new JTextField(10);
		a2= new JTextField(10);
		a3= new JTextField(10);
		a4= new JTextField(10);
		jtf1 = new JTextField(10);
		jtf2 = new JTextField(10);
		jtf3 = new JTextField(10);
		add = new JButton("Add");
		cancel = new JButton("Cancel");
		
		contentPane.setLayout(new BorderLayout());
		
		top.add(pname ,"growx, pushx");
		top.add(a1  ,"growx, pushx, wrap" );
		
		top.add(add1 ,"growx, pushx");
		top.add(a2 ,"growx, pushx, wrap");
		
		top.add(add2 ,"growx, pushx");
		top.add(a3 ,"growx, pushx, wrap");
		
		top.add(beds ,"growx, pushx");
		top.add(jtf1 ,"growx, pushx, wrap");
		
		top.add(baths ,"growx, pushx");
		top.add(jtf2 ,"growx, pushx, wrap");
		
		top.add(price ,"growx, pushx");
		top.add(jtf3 ,"growx, pushx, wrap");
		
		top.add(number ,"growx, pushx");
		top.add(a4 ,"growx, pushx, wrap");
		
		bottom.add(add ,"growx, pushx");
		bottom.add(cancel ,"growx, pushx, wrap");
		
		
		contentPane.add(top , BorderLayout.CENTER);
		contentPane.add(bottom , BorderLayout.SOUTH);
		this.add(contentPane);
		
		add.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
		{
			if(a1.getText().isEmpty() || a2.getText().isEmpty() || a3.getText().isEmpty() || a4.getText().isEmpty() || jtf1.getText().isEmpty() || jtf2.getText().isEmpty() || jtf3.getText().isEmpty())
			{
				JFrame frame = new JFrame("Error");
				frame.setTitle("Error");
				JOptionPane.showMessageDialog(frame, "Please Fill All Fields");
			}
			else
			{
				String Picname = a1.getText() ;
				String streetname =a2.getText() ;
				String cityname = a3.getText();
				int bed = Integer.parseInt(jtf1.getText());
				int bath =Integer.parseInt(jtf2.getText());
				double price  = Double.parseDouble(jtf3.getText());
				String number = a4.getText();
						
				House aHouse = new House(streetname , cityname , bed , bath , price , Picname , number);
				
				houselist.add(aHouse);
				
				JFrame frame = new JFrame("Successfull Addtion");
				JOptionPane.showMessageDialog(frame, "House Was Added Sucessfully");
				dispose();
			}
			
		}
		}
	);

		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Closes window
				setVisible(false); 
				dispose();
			}
		}
		);
		
		setVisible(true);
		pack();
		
}

}