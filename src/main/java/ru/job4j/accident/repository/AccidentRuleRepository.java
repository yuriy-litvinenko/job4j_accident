package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public interface AccidentRuleRepository extends CrudRepository<Rule, Integer> {

    @Query("SELECT t FROM Rule t ORDER BY t.id")
    Collection<Rule> getRules();

    @Query("SELECT t FROM Rule t WHERE t.id = ?1")
    Rule getRuleById(int id);
}
