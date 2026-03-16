package com.cursospring.init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursospring.init.model.Item;

public interface ItemsRepository extends JpaRepository<Item,Integer>{
	//salvar item: save
}
