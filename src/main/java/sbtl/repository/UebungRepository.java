package sbtl.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sbtl.model.Tag;
import sbtl.model.Uebung;

@Repository
public interface UebungRepository extends CrudRepository <Uebung, Long> {
	
}
