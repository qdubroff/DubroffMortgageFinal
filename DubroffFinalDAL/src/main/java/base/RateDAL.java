package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;



public class RateDAL {
	
	public static ArrayList<RateDomainModel> getRates() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();

		try {
			tx = session.beginTransaction();	
			
			List rate = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = rate.iterator(); iterator.hasNext();) {
				RateDomainModel rat = (RateDomainModel) iterator.next();
				rates.add(rat);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rates;
		
	}
	public static double getRate(int GivenCreditScore) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		double interestRate = 0;
		int MinCreditScore = 0;
		try{
			tx = session.beginTransaction();
			if (GivenCreditScore >= 800){
				MinCreditScore = 800;
			}
			else if (GivenCreditScore >= 750){
				MinCreditScore = 750;
			}
			else if (GivenCreditScore >= 700){
				MinCreditScore = 700;
			}
			else if (GivenCreditScore >= 650){
				MinCreditScore = 650;
			}
			else if(GivenCreditScore >= 600){
				MinCreditScore = 600;
			}

			Query query = session.createQuery("SELECT InterestRate from RateDomainModel WHERE MINCREDITSCORE = " +MinCreditScore);
			List rates = query.list();
			interestRate = (Double)rates.get(0);
			tx.commit();
		
		}
		catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return interestRate;
	}
}