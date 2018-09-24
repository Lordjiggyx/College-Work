package DataStructures;


import java.util.Iterator;
//This class implements the ADT definition of a linked list using the same signatures. Note that we can add extra methods
//here once all of the methods listed in the ADT interface are included.

import Applications.Student;

public class LinkedList<T> implements LinkedListADT<T> {
	
	 private int count;  // the current number of elements in the list
	 private LinearNode<T> list; //pointer to the first element 
	 private LinearNode<T> last; //pointer to the last element 
	 
	 //-----------------------------------------------------------------
	 //  Creates an empty list.
	 //-----------------------------------------------------------------
	    public LinkedList()
	    {
	       this.count = 0;
	       this.last = null;
	       this.list = null;
	    }
	    //Return First item
	    public void first (T element)
	    {
	    	LinearNode<T> T = this.list;
	    	return;
	    }
	    //Returns the first node 
	    public LinearNode<T> getFirst()
	    {
	    	return this.list;
	    	
	    }
	    
	    //Gets the next node
	    public LinearNode<T> getNext(LinearNode<T> node)
	    {
	    	node = this.list.getNext();
	    	return node;
	    }
	    //Return Last Item
	    public void last (T element)
	    {
	    	LinearNode<T> T = this.last;
	    	return;
	    }
	    
	    //Return the next generic element
	    public T next(T element)
	    {	
	    	//Node is cast to an element variable
	    	LinearNode<T> node = new LinearNode<T> (element); 
	    	//Currengt is set to the first node
	    	LinearNode<T> current = this.list;
	    	//Bollean variable to determine if an element is found
	    	boolean found = false;
	    	//Dsiplays message if the linked list is empty
	    	if (isEmpty()) 
	    	{
				System.out.println("There are no nodes in the list");
	    	}
				else
				{
					//What is happening is it goes through the list 
					//while current is null and found isn't true
					//it goes through the list and checks if the current element equals the parameter coming in
					//If it does found is true
					while (current != null && !found)
					{
						if (current.getElement().equals(element))
						{
							found = true;
						}
					}				
					//If found is not true
					//and if the current one equals the last one
					//It will return null as we want the next one not the last or current one
					if (!found)
						return null;
					else if (current == last)
						return null;
					//This is that part here as it returns the next element
					else
						return current.getNext().getElement();
						
				}
	    	return null;
	    	
	    }
	    
	    
	 //  Adds one element to the end of this list
	   public void add (T element)
	   {      
		   LinearNode<T> node = new LinearNode<T> (element); 
     
		   if (size() == 0) {  
			   	this.last = node; // This is the last and the 
			 	this.list = node; // first node
			 	this.count++;
		   }//end if
		   else
			  { 
				  last.setNext(node); // add node to the end of the list
				  last = node; // now make this the new last node.
				  count++;   
		      } //end if
	   }
	   
	   //Adds a generic object in the correct sorted position in the list
public void add(T element, int i) 
{
		   
		   LinearNode<T> node = new LinearNode<T> (element);
		   LinearNode<T> current = this.list;
		   LinearNode<T> previous = null;
		   
		   if (size() == 0) {  
			   	this.last = node; // This is the last and the 
			 	this.list = node; // first node
			 	this.count++;
		   }//end if
		   
		   else {
			   for (int j = 0; j < i; j++) {
				   previous = current;
				   current = current.getNext();
			   }
			   if (previous == null) {
				   list = node;
				   node.setNext(current);
			   }
			   else {
				   previous.setNext(node);//previous node's next is set to new Node.
				   node.setNext(current);//New Node's next set to Current.
			   }
		  }//end else
	   }//end add method
	
	   
	   //  Removes and returns the first element from this list
	
	public T remove()
	   { 
		   T result = null;
			if (isEmpty()) {
				System.out.println("There are no nodes in the list");
			}//end if
			else {
				result = this.list.getElement();
				this.list = this.list.getNext();
				count--;
			}//end else
			return result;

	   }
	//Delete any object from the list where the object is passed in as a parameter
	public void remove(T element)
	{
		boolean found = false;

		LinearNode<T> current = this.list;
		LinearNode<T> previous = this.list; // points to previous position of
											// current
		String fullList = "";

		T result = null;

		while (!found && current != null)
		{
			if (!current.getElement().equals(element)) 
			{
				previous = current;
				current = current.getNext();
			} else
				found = true;
		}

		if (!found) 
		{
			System.out.println("");
		} 
		else 
		{
			if (current == (list)) // delete first element
			{
				result = this.list.getElement();
				this.list = this.list.getNext();

			} 
			else if (current == (last)) // delete last
			{
				result = this.last.getElement();
				last = previous;
				previous.setNext(null);

			} 
			else // delete other
			{
				//result = current.getElement();
				previous.setNext(current.getNext());
			}
		}
	}
	
	

	   //  searches for element in the list and returns True or False
	public boolean contains(T element) {
		boolean found = false;

		LinearNode<T> current = this.list;

		String fullList = "";

		// looking to see if the element we want to delete is the first element
		for (current = this.list; current != null; current = current.getNext()) {
			if (current.getElement().equals(element))
				fullList = fullList + "\n" + current.getElement().toString();
		}

		return found;
	}
	   
	   //  Returns true if this list contains no elements
	   public boolean isEmpty()
	   {
		   if (this.list == null)
			   return true;
		   else
			   return false;
	   }

	   //  Returns the number of elements in this list
	   public int size()
	   {
		   return this.count;
	   }
	

	   //  Returns a string representation of this list
	  
	public String toString()
	   {
		   LinearNode<T> current = this.list;
		   String fullList = "";
		   
		   for (current = this.list; current != null; current = current.getNext())
		   {
			   fullList = fullList + "\n" + current.getElement().toString();
		   }//end for
		   
		   return fullList + "\n";
	   }
	

	
	


	
}
