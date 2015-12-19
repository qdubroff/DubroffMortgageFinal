package ch.makery.address.model;

import domain.RateDomainModel;
import org.apache.poi.ss.formula.functions.FinanceLib;

public class Rate extends RateDomainModel {
	
	public double getPayment(double rate, double houseCost, double periods)
	{

		double PV = houseCost;
		double FV=0;
		double numberOfPayments = periods;
		boolean t = false;
		//	Figure out payment based on:
		//	Interest rate
		//	PV
		//	FV (make FV = 0, unless you want a balloon payment
		//	Compounding = True
		//	Number of Payments (passed in)
		
		double monthlyPayment = FinanceLib.pmt(rate/12,numberOfPayments,-PV,FV,t);
		
		return monthlyPayment;
	}
}
