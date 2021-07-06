package com.martinnolan.castlemanager.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.martinnolan.castlemanager.dto.Castle;

public class CastleDAO {

	private List<Castle> castleList = new ArrayList<>(Arrays.asList(
            new Castle(0, "AC-4", "40ft Assault Course", "Red assault course", 45.0, 15.0, 180.0),
            new Castle(1, "AC-7", "35ft pink unicorn Course", "Red assault course", 35.0, 14.0, 150.0),
            new Castle(2, "BS-2", "Big slide", "Blue slide", 30.0, 25.0, 160.0)
            
    ));
	
	
}
