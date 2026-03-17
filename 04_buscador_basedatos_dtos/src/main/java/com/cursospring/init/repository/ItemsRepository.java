package com.cursospring.init.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cursospring.init.model.Item;

public interface ItemsRepository extends JpaRepository<Item,Integer>{
	//salvar item: save
	
	Optional<Item> findFistByUrlAndTematica(String url, String tematica);
	boolean existsByUrlAndTematica(String url, String tematica);
	
	List<Item> findByTematica(String tematica);
	
	Optional<Item> findFirstByUrl(String url);
	@Transactional
	@Modifying
	void deleteByUrl(String url);
	//@Query("select dictinct(i.url) from Item i")
	@Query(value="select dictinct(url) from items",nativeQuery = true)
	List<String> findUrls();
}
