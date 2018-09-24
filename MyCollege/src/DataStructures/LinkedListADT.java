package DataStructures;

public interface LinkedListADT <T>
{
	//Return First item
	public void first(T element);
	
	//Return Last Item
	public void last (T element);
	
	//Return the next generic element
	public T next(T element);
	
	// Adds one element to the end of this list
	public void add (T element);
	
	//Delete any object from the list where the object is passed in as a parameter
	public void remove(T element);
	
	//Returns the first node 
	public LinearNode<T> getFirst();
	
	//Adds a generic object in the correct sorted position in the list
	public void add(T element, int i);
	
	//Gets the next node
	public LinearNode<T> getNext(LinearNode<T> node);

	//  Removes and returns the first element from this list
	public T remove();

	//  Returns true if this list contains no elements
	public boolean isEmpty();

	//  Returns the number of elements in this list
	public int size();

	//  Returns a string representation of this list
	public String toString();

	//Method returns true if it discovers a duplicate element in the list
	public boolean contains(T element);

}
