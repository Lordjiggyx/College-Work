/*                                                            */
/*   Program to demonstrate the implmentation of a            */
/*   linked list of numbers.								  */
/*                                                            */
/**************************************************************/
#define _CRT_SECURE_NO_WARNINGS
#define bool int
#define false 0
#define true (!false)

//Libraries
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Preprocessor Variable
#define SIZE 1
//Stucture template for data part of a LinearNode
struct car
{
	char Reg[20];
	char Year[20];
	char County[20];
	char RegNo[20];
	char Model[20];
	char Make[20];
	char Colour[20];
	char reserved[20];
	int owners;
	bool  booked;
	double deposit;
};

//Stucture template for one node
struct LinearNode
{
	struct car *element;
	struct LinearNode *next;
};


// Function prototypes
void addCar();  //adding Cars to end of the list
void sellCar(); // delete a specific node
void saveToFile();
void reserve();
void viewAllCars();
void SpecificCar();
void menu();
void end();
bool isEmpty();




// Global Variables
struct LinearNode *front = NULL;
struct LinearNode *last = NULL;
int counter = 0;
FILE *fp;
/**************MAIN FUNCTION*******************/
int main(void)
{

	fp = fopen("car.dat", "rb");
	struct LinearNode *aNode;
	struct car *aCar;
	if (fp == NULL)
	{
		printf("No Cars In File Please Go To Menu Option 1 And Add Cars\n");
		menu();

	}
	else
	{
		printf("Retriving cars from file...\n");

		while (fread(&aCar, sizeof(struct car), 1, fp));
		{
			aCar = (struct car *)malloc(sizeof(struct car));
			aNode = (struct LinearNode *)malloc(sizeof(struct LinearNode));

			aNode->element = aCar;
			aNode->next = NULL;

			if (isEmpty())
			{
				front = aNode;
				last = aNode;
			}
			else
			{
				last->next = aNode;
				last = aNode;
			}
		}
		menu();

	}


	getchar();
	getchar();
}

/////////////////Add Cars////////////////////////
void addCar()
{
	char Year1[20];
	char County2[20];
	struct LinearNode *aNode;
	struct car *aCar;
	if (counter >= 5)
	{
		printf("The ShowRoom Is Full No More Cars Can Be Added\n");
	}
	else
		// add SIZE nodes to the list
		for (int i = 0; i<SIZE; i++)
		{
			//////////STEP ONE:CREATE SPACE FOR DATA I.E THE BOX THAT CONTAINS INFORMATION/////////////
			//Create space for data part of node
			aCar = (struct car *)malloc(sizeof(struct car));
			{
				//Input value into the data part
				printf("Enter The Year of Manufacteuring:\n");
				scanf("%s", Year1);
				printf("Enter County of Ownership:\n");
				scanf("%s", County2);

				do
				{
					printf("Enter The Registration Numbers for the Car (MAX 4 NUMBERS): \n");
					scanf("%s", aCar->RegNo);
				} while (strlen(aCar->RegNo) > 4);

				aCar->Reg[0] = Year1[2];
				aCar->Reg[1] = Year1[3];
				aCar->Reg[2] = County2[0];
				aCar->Reg[3] = aCar->RegNo[0];
				aCar->Reg[4] = aCar->RegNo[1];
				aCar->Reg[5] = aCar->RegNo[2];
				aCar->Reg[6] = aCar->RegNo[3];
				aCar->Reg[7] = '\0';

				printf("Enter The Car Model: \n");
				scanf("%s", &aCar->Model);

				printf("Enter The car Make: \n");
				scanf("%s", &aCar->Make);

				printf("Enter The Car Colour: \n");
				scanf("%s", &aCar->Colour);


				printf("Enter the number of  previous owners:\n");
				scanf("%d", &aCar->owners);



				strcpy(aCar->reserved, "Unreserved");
				aCar->deposit = 0;
				aCar->booked = false;
				// create space for new node that will contain data
				aNode = (struct LinearNode *)malloc(sizeof(struct LinearNode));
				if (aCar->owners > 3)
				{
					printf("This Car Cannot Be Added As It Has Too Many Previous Owners\n");
					aCar = NULL;
					aNode = NULL;
				}
				//////////////STEP TWO: CREATE SPACE FOR THE NODE I.E THE POINTER OF THE INFORMATION///////////	
				if (aNode == NULL && aCar == NULL)
				{
					printf("You Will Be Returned To The Menu\n");
				}
				else
					///////STEP 3 : ADD DATA TO THE NODE WHICH CONNECTS THE BOXES//////////////			
				{ // add data part to the node
					aNode->element = aCar;
					aNode->next = NULL;

					//////STEP 4 : ADD THE NODE TO THE LIST//////////////
					//add node to end of the list
					if (isEmpty())
					{
						front = aNode;
						last = aNode;
					}
					else
					{
						last->next = aNode;
						last = aNode;
					} //end else
				}//end else
			}//end else 
		}//end for
	counter = counter++;
} //end addNodes
  ////////////////View Cars/////////////////////
void viewAllCars()
{
	int choice;
	char color[20];
	bool found = false;
	struct LinearNode *current = front;

	printf("\n");
	if (isEmpty())
		printf("No Cars Present\n");
	else
	{

		printf("Would you Like To See All Cars or Cars By Colour\n");
		printf("Enter 1 To see All Cars\n");
		printf("Enter 2 to see Cars By Colour\n");
		scanf("%d", &choice);
		if (choice == 1)
		{

			while (current != NULL)
			{

				printf("Car Information:\n");
				printf("==================\n");
				printf("Car Registartion Number:%s\n", current->element->Reg);
				printf("Car Make:%s\n", current->element->Make);
				printf("Car Model:%s\n", current->element->Model);
				printf("Car Colour:%s\n", current->element->Colour);
				printf("Amount Of Previous Owners:%d\n", current->element->owners);
				printf("Reserved:%s\n", current->element->reserved);
				printf("Deposit:%f\n", current->element->deposit);
				current = current->next;
			}
			//end while
		} //end if
		if (choice == 2)
		{

			printf("What Coulor Car would you like to see\n");
			scanf("%s", color);

			while (current != NULL)
			{

				if (strcmp(color, current->element->Colour) == 0)
				{
					found = true;
					printf("Car Information\n");
					printf("==================\n");
					printf("Car Registartion Number:%s\n", current->element->Reg);
					printf("Car Make:%s\n", current->element->Make);
					printf("Car Model:%s\n", current->element->Model);
					printf("Car Colour:%s\n", current->element->Colour);
					printf("Amount Of Previous Owners:%d\n", current->element->owners);
					printf("Reserved:%s\n", current->element->reserved);
					printf("Deposit:%f\n", current->element->deposit);
				}
				current = current->next;
			}
			if (!found)
			{
				printf("No car Of This Colour Can Be Found\n");
			}






		}


	}//end else
} //end viewAllNodes
  ///////////////Sell Cars/////////////////////
void sellCar()
{
	struct LinearNode *current, *previous = front;
	bool notFound = true;
	char registration[20];

	printf("\n");
	if (isEmpty())
		printf("Error - there are no nodes in the list\n");
	else
	{
		current = previous = front;

		printf("Enter Registration Number of car you wish to sell\n");
		scanf("%s", &registration);
		if (strcmp(registration, current->element->Reg) == 0 && current->element->booked == false)
		{
			printf("Car has been found however it is not reserved thus cannot be sold\n");
		}
		else
			while (notFound && current != NULL)
			{
				if (strcmp(registration, current->element->Reg) == 0 && current->element->booked == true)
					notFound = false;
				else
				{
					previous = current;
					current = current->next;
				}//end else
			} //end while

		if (notFound)
			printf("This Car Cannot Be Found %s\n", registration);
		else
		{
			if (current == front)
			{ //delete front node
				front = front->next;
				free(current);
			} //end else
			else if (current == last)
			{//delete last node
				free(current);
				previous->next = NULL;
				last = previous;
			}
			else
			{//delete node in middle of list
				previous->next = current->next;
				free(current);
			} //end else
			printf("This Car has been Sold\n");
		}//end else
	}//end else
}// end deleteNode
 //////////////Saving to File////////////////
void saveToFile() {
	fp = fopen("car.dat", "wb");
	struct LinearNode *current = front;
	while (current != NULL) {
		fwrite(&current->element, sizeof(struct car), 1, fp);
		current = current->next;
	} //end while
	fclose(fp);
}//Save to file
 /////////////Reserve/Unreserve Car///////////
void reserve()
{
	struct LinearNode *current = front;
	int choice;
	int amount;
	bool found = false;
	char reg[20];

	printf("Would you Like To Reserve/Unreserve a Car\n");
	printf("Enter 1 To Reserve\n");
	printf("Enter 2 To Unreserve\n");
	scanf("%d", &choice);


	if (choice == 1)
	{
		printf("Enter Car Registration Number\n");
		scanf("%s", reg);
		while (current != NULL)
		{
			if (strcmp(reg, current->element->Reg) == 0 && current->element->booked == true)
			{
				printf("Car has been Found but has already been booked you cannot book this vehicle\n");
			}
			if (strcmp(reg, current->element->Reg) == 1)
			{
				printf("Car has not been Found\n");
			}
			else
				if (strcmp(reg, current->element->Reg) == 0)
				{
					found = true;
					printf("Car has been Found\n");
					current->element->booked = true;
					if (current->element->booked = true)
					{
						strcpy(current->element->reserved, "Reserved");
						do
						{
							printf("Enter Deposit Amount ( Must Be More Than 500 And Less Than 1500 \n");
							scanf("%d", &amount);
						} while (amount < 500 || amount > 1500);
						current->element->deposit = amount;
					}


					printf("Car has been reserved\n");

				}
			current = current->next;
		}
	}
	if (choice == 2)
	{
		printf("Enter Car Registration Number\n");
		scanf("%s", reg);
		while (current != NULL)
		{
			if (strcmp(reg, current->element->Reg) == 0)
			{
				printf("Car has been Found\n");
				current->element->booked = false;
				strcpy(current->element->reserved, "Unreserved");
				current->element->deposit = 0;
			}
			if (strcmp(reg, current->element->Reg) == 1)
			{
				printf("Car has not been Found\n");
			}
			current = current->next;
		}
		printf("Car has been Unreserved\n");
	}

}
/////////////View a a specific Car/////////////
void SpecificCar()
{
	struct LinearNode *current = front;
	char reg[20];
	bool found = false;

	printf("Enter Car Registration Number\n");
	scanf("%s", &reg);

	while (current != NULL)
	{
		if (strcmp(reg, current->element->Reg) == 0)
		{
			found = true;
			printf("Car Information\n");
			printf("==================\n");
			printf("Car Registartion Number:%s\n", current->element->Reg);
			printf("Car Make:%s\n", current->element->Make);
			printf("Car Model:%s\n", current->element->Model);
			printf("Car Colour:%s\n", current->element->Colour);
			printf("Amount Of Previous Owners:%d\n", current->element->owners);
			printf("Reserved:%s\n", current->element->reserved);
			printf("Deposit:%f\n", current->element->deposit);
		}
		current = current->next;
	}

	if (!found)
	{
		printf("Car Can't Be Found\n");
	}


}
////////////Check if List is empty/////////////// 
bool isEmpty()
{
	if (front == NULL)
		return true;
	else
		return false;
}
/////////////Menu///////////////////////////////
void menu()
{
	int option;
	do
	{
		printf("\n");
		printf("==================\n");
		printf("1. Add A New Car To The ShowRoom\n");
		printf("2. Sell A Car From The ShowRoom\n");
		printf("3. Reserve/Unreserve A Car From The ShowRoom\n");
		printf("4. View All The Cars From ShowRoom\n");
		printf("5. View A Specific Car From The ShowRoom\n");
		printf("6. Extra\n");
		printf("7.Exit System\n");

		do
		{
			printf("Please Select Menu Option\n");
			scanf("%d", &option);
		} while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7);

		if (option == 1)
		{
			addCar();
		}
		if (option == 2)
		{
			sellCar();
		}
		if (option == 3)
		{
			reserve();
		}
		if (option == 4)
		{
			viewAllCars();
		}
		if (option == 5)
		{
			SpecificCar();
		}

		/*if (option == 6)
		{
		extra();
		}*/
	} while (option != 7);
	if (option == 7)
	{
		saveToFile(front);
		end();
	}

}
///////////Farewell Messaage///////////////////
void end()
{
	//farewell message
	printf("Thank you for working with us\n");
}