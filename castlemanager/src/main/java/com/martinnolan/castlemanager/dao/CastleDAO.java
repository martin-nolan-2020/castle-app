package com.martinnolan.castlemanager.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.martinnolan.castlemanager.dto.Castle;

@Repository
@Transactional
public class CastleDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	private List<Castle> castleList = new ArrayList<>(Arrays.asList(
            new Castle("AC-4", "40ft Assault Course", "Red assault course", 45.0, 15.0, 180.0),
            new Castle("AC-7", "35ft pink unicorn Course", "pink unicorn assault course", 35.0, 14.0, 150.0),
            new Castle("BS-2", "Big slide", "Blue slide", 30.0, 25.0, 160.0)
            
    ));
	
	
	public int insert(Castle castle){
		entityManager.persist(castle);
		//entityManager.persist(castleList.get(0));
		//entityManager.persist(castleList.get(1));
		return castle.getId();
	}
	
}
