package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

@Repository
public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {

    @Query("SELECT t FROM AccidentType t")
    Collection<AccidentType> getTypes();

    @Query("SELECT t FROM AccidentType t WHERE t.id = ?1")
    AccidentType getTypeById(int id);
}
