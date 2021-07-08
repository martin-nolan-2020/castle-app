package com.martinnolan.castlemanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinnolan.castlemanager.dto.Castle;

public interface CastleRepository extends JpaRepository<Castle, Integer> {

	List<Castle> findByLengthLessThan(Double length);

}
