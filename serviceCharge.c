#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>

void main()
{
	float accountbalance, servicecharge, total;
	servicecharge = 0;
	accountbalance = 0;

	for (int i = 0; i < 5; i++)
	{
		servicecharge = 0;
		printf("Enter an account balance:");
		scanf("%f", &accountbalance);

		if (accountbalance == -100)
		{
			servicecharge = 10.00;
			total = accountbalance - servicecharge;
		}
		else if(accountbalance < -100 && accountbalance > -500)
		{
			servicecharge = 100.00;
			total = accountbalance - servicecharge;
		}
		else if(accountbalance <= -500)
		{
			servicecharge = (accountbalance * 2)*-1;
			total = accountbalance - servicecharge;
		}
		else if (accountbalance >0)
		{
			servicecharge = 0;
			total = accountbalance - servicecharge;
		}
		printf("Account balance = %5.2f, Service Charge = %5.2f, total = %5.2f\n",
			accountbalance, servicecharge, total);
	
	}

	getchar();
	getchar();

}