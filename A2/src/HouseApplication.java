

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.text.NumberFormat;
import java.util.*;

public class HouseApplication extends JFrame 
{

	ArrayList<House> houseList = new ArrayList<House>();
	JMenuBar menuBar;
	JMenu modifyMenu, reportMenu, closeMenu;
	JMenuItem createItem,  deleteItem,  searchItem, summaryItem, closeApp;
	JButton firstItemButton, nextItemButton, prevItemButton, lastItemButton, editItemButton;
	JLabel houseImageLabel, idLabel, streetLabel, cityLabel, bedroomsLabel, bathroomsLabel, priceLabel, changeLabel, contactNoLabel;
	JTextField idTextField, streetTextField, cityTextField, bedroomsTextField, bathroomsTextField, priceTextField, changeTextField, contactNoTextField;
	String[][] records = { {"113 The Maltings", "Dublin 8", "2", "1", "155500.00", "House1.jpg", "(087) 9011135"},
			{"78 Newington Lodge", "Dublin 14", "3", "2", "310000.00", "House2.jpg", "(087) 9010580"},
			{"62 Bohernabreena Road", "Dublin 24", "3", "1", "220000.00", "House3.jpg", "(087) 6023159"},
			{"18 Castledevitt Park", "Dublin 15", "3", "3", "325000.00", "House4.jpg", "(087) 9010580"},
			{"40 Dunsawny Road", "Swords", "3", "19", "245000.00", "House5.jpg", "(087) 9011135"}
	};
	int currentItem;
	ActionListener first, next, prev, last, edit; 
	String password = "3175";
	
	public HouseApplication() 
	{
		super("Estate Agent Application");
		for (int i = 0; i < records.length; i++)
		{
			houseList.add(new House(records[i][0], records[i][1], Integer.parseInt(records[i][2]), 
					Integer.parseInt(records[i][3]), Double.parseDouble(records[i][4]), records[i][5], records[i][6]));
		}
		currentItem = 0;
		initComponents();	
	}

	public void initComponents() 
	{
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel(new MigLayout());
		
		// Ensures that image is centred in label
		houseImageLabel = new JLabel(new ImageIcon(), SwingConstants.CENTER);
		displayPanel.add(houseImageLabel, "push, grow, span 2, wrap");
		
		idLabel = new JLabel("House ID: ");
		idTextField = new JTextField(3);
		idTextField.setEditable(false);
		
		displayPanel.add(idLabel, "growx, pushx");
		displayPanel.add(idTextField, "growx, pushx, wrap");

		streetLabel = new JLabel("Address Line 1: ");
		streetTextField = new JTextField(15);
		streetTextField.setEditable(false);
		
		displayPanel.add(streetLabel, "growx, pushx");
		displayPanel.add(streetTextField, "growx, pushx, wrap");

		cityLabel = new JLabel("Address Line 2: ");
		cityTextField = new JTextField(15);
		cityTextField.setEditable(false);
		
		displayPanel.add(cityLabel, "growx, pushx");
		displayPanel.add(cityTextField, "growx, pushx, wrap");

		bedroomsLabel = new JLabel("Number of bedrooms: ");
		bedroomsTextField = new JTextField(3);
		bedroomsTextField.setEditable(false);
		
		displayPanel.add(bedroomsLabel, "growx, pushx");
		displayPanel.add(bedroomsTextField, "growx, pushx, wrap");

		bathroomsLabel = new JLabel("Number of bathrooms: ");
		bathroomsTextField = new JTextField(3);
		bathroomsTextField.setEditable(false);
		
		displayPanel.add(bathroomsLabel, "growx, pushx");
		displayPanel.add(bathroomsTextField, "growx, pushx, wrap");

		priceLabel = new JLabel("Price: ");
		priceTextField = new JTextField(10);
		priceTextField.setEditable(false);
		
		displayPanel.add(priceLabel, "growx, pushx");
		displayPanel.add(priceTextField, "growx, pushx, wrap");
		
		changeLabel = new JLabel("Price change: ");
		changeTextField = new JTextField(10);
		changeTextField.setEditable(false);
		
		displayPanel.add(changeLabel, "growx, pushx");
		displayPanel.add(changeTextField, "growx, pushx, wrap");
		
		contactNoLabel = new JLabel("Contact number: ");
		contactNoTextField = new JTextField(15);
		contactNoTextField.setEditable(false);
		
		displayPanel.add(contactNoLabel, "growx, pushx");
		displayPanel.add(contactNoTextField, "growx, pushx, wrap");
		add(displayPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 5));

		firstItemButton = new JButton(new ImageIcon("first.png"));
		nextItemButton = new JButton(new ImageIcon("next.png"));
		editItemButton = new JButton("Edit");
		prevItemButton = new JButton(new ImageIcon("prev.png"));
		lastItemButton = new JButton(new ImageIcon("last.png"));
		
		buttonPanel.add(firstItemButton);
		buttonPanel.add(prevItemButton);
		buttonPanel.add(editItemButton);
		buttonPanel.add(nextItemButton);
		buttonPanel.add(lastItemButton);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(buttonPanel);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/* Set up your menus and menu items here */
		//Modify Menu
		modifyMenu = new JMenu("Modify");
		createItem = new JMenuItem("Create");
		deleteItem = new JMenuItem("Remove");
		modifyMenu.add(createItem);
		modifyMenu.add(deleteItem);
		
		//Report Menu
		reportMenu = new JMenu("Report");
		searchItem = new JMenuItem("Search Records");
		summaryItem = new JMenuItem("Summary of Records");
		reportMenu.add(searchItem);
		reportMenu.add(summaryItem);
		
		//Close Menu
		closeMenu = new JMenu("Close");
		closeApp = new JMenuItem("Close Application");
		closeMenu.add(closeApp);
		
		
		menuBar.add(modifyMenu);
		menuBar.add(reportMenu);
		menuBar.add(closeMenu);
		
		displayDetails(currentItem);
		
		// Because each pair of corresponding buttons and menu items have the same functionality, instead
		// of repeating the same code in two locations, we can define an ActionListener object that both
		// components will share by having it added as their action listener.
		
		first = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (editItemButton.getText().equals("Save") )
				{
					// Here we make sure that any updated values are saved to the record before
					// we display the next record.
					// This behaviour is performed by next, prev and edit, so we move it into a
					// separate method so as to avoid unnecessary repetition of code.
					saveOpenValues();					
				}
				currentItem = 0;
				displayDetails(currentItem);				
			}
		};
		
		next = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// No next if at end of list
				if (currentItem != (houseList.size() - 1)) 
				{
					if (editItemButton.getText().equals("Save") ) 
					{
						// Here we make sure that any updated values are saved to the record before
						// we display the next record.
						// This behaviour is performed by next, prev and edit, so we move it into a
						// separate method so as to avoid unnecessary repetition of code.
						saveOpenValues();					
					}
					currentItem++;
					displayDetails(currentItem);
					
				}
			}
		};

		prev = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// No previous if at beginning of list
				if (currentItem != 0) 
				{
					if (editItemButton.getText().equals("Save") )
					{
						saveOpenValues();					
					}
					currentItem--;
					displayDetails(currentItem);
				}
			}
		};

		last = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (editItemButton.getText().equals("Save") ) 
				{
					// Here we make sure that any updated values are saved to the record before
					// we display the next record.
					// This behaviour is performed by next, prev and edit, so we move it into a
					// separate method so as to avoid unnecessary repetition of code.
					saveOpenValues();					
				}
				currentItem = houseList.size() - 1;
				displayDetails(currentItem);								
			}
		};
		
		edit = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getActionCommand() == "Edit") 
				{
					// Allow data to be edited
					editItemButton.setText("Save");
					priceTextField.setEditable(true);
				}
				else if (e.getActionCommand() == "Save")
				{
					saveOpenValues();
				}
			}
		};
		
		createItem.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
		{
			if(getPassword() == false)
			{
				
			}
			else
			{
				JOptionPane.showMessageDialog(null , "Correct Password");
				CreateHouseDialog frame = new CreateHouseDialog(houseList);
			}
		}
		}
	);
	
		deleteItem.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
		{
			if(getPassword() == false)
			{
				
			}
			else
			{
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f , "Correct Password");
				int a=JOptionPane.showConfirmDialog(f , "This will delete the record , Are you sure you want to continue");  
				if(a==JOptionPane.YES_OPTION)
				{  
				houseList.remove(currentItem);
				if(currentItem == houseList.size())
				{
				currentItem--;	
				}
				displayDetails(currentItem);
				JOptionPane.showMessageDialog(null , "House Removal Successful");
				}
				else
				if(a==JOptionPane.NO_OPTION)
				{  
					f.setVisible(false);
				}
				else
				if(a==JOptionPane.CANCEL_OPTION);
				{
					f.setVisible(false);
				}
		}
		}
		}
	);
		
		searchItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Integer[] index = new Integer[houseList.size()];
				for (int a = 0; a <houseList.size(); a++)
				{
					index[a] = houseList.get(a).getId();
				}
				JFrame f = new JFrame();
				String search = JOptionPane.showInputDialog(f , "Choose ID" , "House ID", JOptionPane.OK_CANCEL_OPTION , null , index , index[0]).toString();
				 int find = Integer.parseInt(search);
				 currentItem = 0;
				 for(int i = 0 ; i< houseList.size(); i++)
				 {
					 if(find == houseList.get(i).getId())
							 {
						 	displayDetails(currentItem);
							 }
					 currentItem++;
				 }
				 currentItem = 0;
			}
			
		}
		);
		
		summaryItem.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e) 
				{
					HouseSummaryDialog frame = new HouseSummaryDialog(houseList);
				}
		
				});
		
		closeApp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		}
				);
		firstItemButton.addActionListener(first);
		
		nextItemButton.addActionListener(next);
		
		prevItemButton.addActionListener(prev);
		
		lastItemButton.addActionListener(last);
		
		editItemButton.addActionListener(edit);
		
		
	}
	
	public boolean getPassword()
	{
		JPasswordField A = new JPasswordField();
		int b = JOptionPane.showOptionDialog(this, new Object[] {A}, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null , null);
		if (b == JOptionPane.YES_OPTION)
		{
			if(new String(A.getPassword()).equals(password))
			{
				return true;
			}
			else if (!new String(A.getPassword()).equals(password))
			{
				JOptionPane.showMessageDialog(this, "Wrong password");
			}
			return false;
		}
		else
			return false;
	}
	
	

	private void saveOpenValues() 
	{
		// Save data and revert to other state.
		// Update appearance of button.
		editItemButton.setText("Edit");
		// Try to save items to record.
		try 
		{
			double oldPrice = houseList.get(currentItem).getPrice();
			double newPrice = Double.parseDouble(priceTextField.getText());
			double change = newPrice - oldPrice;
			houseList.get(currentItem).setPrice(newPrice);
			houseList.get(currentItem).setChange(change);
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			priceTextField.setText(nf.format(newPrice));
			changeTextField.setText(nf.format(change));
			if (change > 0.0) 
				changeTextField.setForeground(Color.GREEN);
			else if (change < 0.0)
				changeTextField.setForeground(Color.RED);
			else
				changeTextField.setForeground(Color.BLACK);
		}
		catch (NumberFormatException ex) 
		{
			JOptionPane.showMessageDialog(null, "Not a valid value for price");
			// Reset contents of text field.
			priceTextField.setText(houseList.get(currentItem).getPrice() + "");
		}
		// Disable text fields.
		priceTextField.setEditable(false);
		// Display message.
		JOptionPane.showMessageDialog(this, "Record updated");		
	}
	
	public void displayDetails(int currentItem) 
	{
		houseImageLabel.setIcon(new ImageIcon(houseList.get(currentItem).getImageLocation()));
		idTextField.setText(houseList.get(currentItem).getId() + "");
		streetTextField.setText(houseList.get(currentItem).getStreet());
		cityTextField.setText(houseList.get(currentItem).getCity());
		bedroomsTextField.setText(houseList.get(currentItem).getBedrooms() + "");
		bathroomsTextField.setText(houseList.get(currentItem).getBathrooms() + "");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		priceTextField.setText(nf.format(houseList.get(currentItem).getPrice()));
		double change = houseList.get(currentItem).getChange();
		changeTextField.setText(nf.format(change));
		if (change > 0.0) 
			changeTextField.setForeground(Color.GREEN);
		else if (change < 0.0)
			changeTextField.setForeground(Color.RED);
		else
			changeTextField.setForeground(Color.BLACK);
		contactNoTextField.setText(houseList.get(currentItem).getContactNo());
	}

	
	public static void main(String[] args) {
		HouseApplication ha = new HouseApplication();
		ha.pack();
		ha.setSize(400, 550);
		ha.setVisible(true);
	}

}
