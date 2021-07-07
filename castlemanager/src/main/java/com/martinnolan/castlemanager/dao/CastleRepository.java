package com.martinnolan.castlemanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinnolan.castlemanager.dto.Castle;

public interface CastleRepository extends JpaRepository<Castle, Integer> {

}
