package com.ams.billingandpayment.ports.adapter.console;

import java.util.Scanner;

/**
 * 
 * @author Raghavendra Badiger
 */

public class BillRunJobScheduler
{
	public static void main(String[] args)
	{

		Scanner sc = new Scanner(System.in);
		StringBuilder exprsn = new StringBuilder();
		String c;
		for (int i = 0; i < 5; i++)
		{
			c = sc.next();
			if ((i == 0) && c.matches(""))
			{
				exprsn.append(" ").append(c);
			}

		}

		System.out.println(exprsn);
	}
}
