package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("SELECT t FROM Accident t")
    Collection<Accident> getAll();

    @Query("SELECT t FROM Accident t JOIN FETCH t.rules WHERE t.id = ?1")
    Accident getById(int id);
}
