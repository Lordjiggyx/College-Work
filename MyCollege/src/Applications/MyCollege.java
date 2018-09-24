package Applications;
import java.util.Scanner;

import DataStructures.LinearNode;
import DataStructures.LinkedList;

//Name of the the Class
public class MyCollege 
{
	//Global Scanner
	Scanner scan = new Scanner(System.in);
	//Global Linked list which can be accessed by every method in the class
	LinkedList<Student> students;	
	//Global Variable for determining the size of the linked list
	int size;
	//Global variable for the password pin
	String pin;

	//Method that creates the linked list
	public MyCollege()
	{	
		//Declaration of Linked list and setting it to accept objects of type Student
		students = new LinkedList<Student>();

	}

	//Method for creating Pin
	public void createPin()
	{
		//Greeting message
		System.out.println("Greeting user before you can use this program we would like you to input a pin");
		System.out.println("We are doing this to ensure that no unauthorised personnel to delete information stored");
		System.out.println("Please rememeber this pin");

		//Setting pin to user input
		pin = scan.nextLine();

	}




	//Method that adds students to Linked list
	public void addStudent()
	{
		//Variable for Student number
		String number;
		//Variable for Student name
		String name ;
		//Variable for year of entry
		int year;
		//Variable For Programme code
		String code;
		
		//Asks users how many students they wish to input
		System.out.println(" ");
		do
		{
			System.out.println("Greetings what amount of students would you like to manage?(20 max.)");
			size = scan.nextInt();
			scan.nextLine();
		}while(size > 20);
		
		//Students information is input 
		System.out.println(" ");
		{
			for (int i = 0 ; i < size ; i++)
			{
				System.out.println(" ");
				System.out.println("What is the student number");
				number = scan.nextLine();

				System.out.println("What is the student name");
				name = scan.nextLine();

				System.out.println("What year did the student join");
				year = scan.nextInt();
				scan.nextLine();

				System.out.println("What is the code of the programme the student is joining");
				code = scan.nextLine();


				//Student is created
				Student aStudent = new Student (number , name , year , code);
				//Student is then added
				students.add(aStudent);


			}
		}
	}	
	
	
	

	//Displays the students
	public void Display()
	{
		System.out.println(" ");
		System.out.println("Student Details:");
		//Calls the linked list toString
		System.out.println(students.toString());
	}

	//Deletes students from the linked list
	public void delete()

	{
		//Requirement of pin to delete students 
		String pin1;
		System.out.println("To delete Students from a course This progranm requires the pin from An adminstrator");
		System.out.println("Enter pin");
		pin1 = scan.nextLine();
		
		//if pin is correct allows student to be deleted
		if (pin1.equals(pin))
		{
			System.out.println("ACCESS GRANTED");
			//Variable for student number
			String number;
			//Asks for student he user wishes to delete
			System.out.println("What is the student number you wish to delete");
			number = scan.nextLine();
			
			//Creates an empty student with only the student number 
			Student aStudent = new Student (number , "" , 0 , "");
			
			System.out.println("Student will now be removed");
			//Removes student
			students.remove(aStudent);	
		}
		//If incorrect denial message is shown and next methid is called 
		if (!pin1.equals(pin))
		{
			System.out.println("ACCESS DENIED ");
		}

	}

	//Follows the same practice as the delete student method
	public void deleteprogramme()
	{
		String pin1;
		System.out.println("To delete Students from a course This progranm requires the pin from An adminstrator");
		System.out.println("Enter pin");
		pin1 = scan.nextLine();


		if (pin1.equals(pin))
		{
			System.out.println("ACCESS GRANTED");
			//Variable for programme code
			String code;
			System.out.println("What is the programme code you wish to delete");
			code = scan.nextLine();
			//Creates an empty student with only the student code
			Student aStudent = new Student (" " , "" , 0, code);

			System.out.println("Students in this programme will now be removed");
			//Loop to ensure that every student with the same code is deleted
			for (int i = 0; i <size; i++)
			{
				students.remove(aStudent);
			}
		}

		if (!pin1.equals(pin))
		{
			System.out.println("ACCESS DENIED ");
		}


	}



	//Main method calling other methods 
	public static void main(String[] args)
	{

		MyCollege a = new MyCollege() ;
		a.createPin();
		a.Display();
		a.delete();
		a.Display();
		a.deleteprogramme();
		a.Display();



	}


}


