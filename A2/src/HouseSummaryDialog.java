

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class HouseSummaryDialog extends JFrame 
{
	JPanel footers ,houseInfo , average;
	JLabel pname , add1 , add2 , beds , baths ,price,number , id , averages , calc;
	
	
	HouseSummaryDialog(ArrayList<House> houseList)
	{
		
		footers = new JPanel(new GridLayout(1,7));
		houseInfo= new JPanel(new GridLayout(houseList.size(),7));
		average= new JPanel(new GridLayout(1,7));
		
		id = new JLabel("ID");
		add1 = new JLabel("Address Line 1" );
		add2 = new JLabel("Address Line 2");
		beds = new JLabel("Number Of Bedrooms");
		baths = new JLabel("Number Of Bathrooms"); 
		price = new JLabel("Price");
		number = new JLabel("Contact Number");
		averages = new JLabel("Average Price");
		
		footers.add(id);
		footers.add(add1);
		footers.add(add2);
		footers.add(beds);
		footers.add(baths);
		footers.add(price);
		footers.add(number);
		
		for (House house : houseList)
		{
			JLabel ids = new JLabel(Integer.toString(house.getId()));
			houseInfo.add(ids);
			
			JLabel streets = new JLabel(house.getStreet());
			houseInfo.add(streets);
			
			JLabel citys = new JLabel(house.getCity());
			houseInfo.add(citys);
			
			JLabel bedrooms = new JLabel(Integer.toString(house.getBedrooms()));
			houseInfo.add(bedrooms);
			
			JLabel bathrooms = new JLabel(Integer.toString(house.getBathrooms()));
			houseInfo.add(bathrooms);
			
			JLabel prices = new JLabel(Double.toString(house.getPrice()));
			houseInfo.add(prices);
			
			JLabel numbers = new JLabel(house.getContactNo());
			houseInfo.add(numbers);
			
		}
		
		
		
		double calculation = 0 ;
		 for (int i = 0; i <houseList.size(); i++)
		 {
			 calculation = calculation + houseList.get(i).getPrice();
		 }
		 calculation = calculation/houseList.size();
		 calc = new JLabel(Double.toString(calculation));
		 JLabel a = new JLabel("");
		 JLabel b = new JLabel("");
		 JLabel c = new JLabel("");
		 JLabel d = new JLabel("");
		 JLabel e = new JLabel("");
		 JLabel f = new JLabel("");
		 average.add(averages);
		 average.add(a);
		 average.add(b);
		 average.add(c);
		 average.add(d);
		 average.add(calc);
		 average.add(e);
		
		add(footers , BorderLayout.NORTH);
		add(houseInfo , BorderLayout.CENTER);
		add(average , BorderLayout.SOUTH);
		setSize(800 , 400);
		setVisible(true);
	}

}


