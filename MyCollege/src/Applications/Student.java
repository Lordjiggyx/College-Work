package Applications;


//name of class
public class Student
{
//Variable for student number
private String number;
//Variable for student name
private String name;
//Variable for student year of entry
private int year;
//Variable for the course code
private	String code;


//Empty Constructor
public Student()
{
number = " ";
name = " ";
year = 0;
code = " ";
}
//Constructor with Variables being taken in
public Student (String number , String name , int year , String code)
{
	this.number = number;
	this.name = name;
	
	//Error checking for year
	if (year < 2013 || year > 2017)
	{
		this.year = 0000;
		System.out.println("ERROR Year is not between 2013 and 2017");
	}
		else
	this.year = year;
	
	//Error checking for the course code
	if (!code.startsWith("DT"))
	{
		this.code = "ERROR";
		System.out.println("ERROR Course Code does not start with DT");
	}
		else
	this.code = code;
}


//Getter and Setter methods
public String getNumber() 
{
	return number;
}

public void setNumber(String number) 
{
	this.number = number;
}

public String getName()
{
	return name;
}

public void setName(String name)
{
	this.name = name;
}

public int getYear()
{
	return year;
}

public void setYear(int year)
{
	this.year = year;
}

public String getCode() 
{
	return code;
}

public void setCode(String code) 
{
	this.code = code;

}

//Equals method for checking if incoming student parameter matches the student number or student code
public boolean equals(Object obj)
{
	//Bollean to determine if incoming information matches
	boolean found = false;
	//Casts the Generic object to that of type Student
	Student aStudent = (Student) obj;
	
//Checks to see if the current student number matches the incoming student number
	if (getNumber().equalsIgnoreCase(aStudent.getNumber()))
	{
		found = true;
	}
	
	//Checks to see if the current course code matches the incoming student number
	if(getCode().equals(aStudent.getCode()))
	{
		found = true;
		
	}
	
	return found;
}


//Methods of comparing Students alphabetically
public boolean greater(String number)
{
	
	if(this.number.compareTo(number) >= 0)
	{
		return true;
	}
	else
		return false;
}

public boolean lesser(String number)
{
	
	if(this.number.compareTo(number) <= 0)
	{
		return true;
	}
	else
		return false;
}

//To String method for displaying student information
public String toString()
{
	return "Student Number:" + this.number + "\n" +  
			"Student Name:" + this.name + "\n" +
				"Student Year Of Entry:" + this.year + "\n" +
					"Programme Code:" + this.code + "\n";
	
}
	
	
	
	
	
	
}
