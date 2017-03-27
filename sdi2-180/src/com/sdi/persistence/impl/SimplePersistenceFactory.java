package com.sdi.persistence.impl;

import com.sdi.persistence.PersistenceFactory;

import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.RatingDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.UserDao;

public class SimplePersistenceFactory implements PersistenceFactory{

	@Override
	public Transaction createTransaction() {
		return new TransactionJdbcImpl();
	}
	
	@Override
	public RatingDao createRatingDao() {
		return new RatingDaoJdbcImpl();
	}

	@Override
	public UserDao createUserDao() {
		return new UserDaoJdbcImpl();
	}

	@Override
	public TripDao createTripDao() {
		return new TripDaoJdbcImpl();
	}

	@Override
	public SeatDao createSeatDao() {
		return new SeatDaoJdbcImpl();
	}

	@Override
	public ApplicationDao createApplicationDao() {
		return new ApplicationDaoJdbcImpl();
	}

}
