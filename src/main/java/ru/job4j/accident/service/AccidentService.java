package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentHibernate accidentRep;

    public AccidentService(AccidentHibernate accidentRep) {
        this.accidentRep = accidentRep;
    }

    public Collection<Accident> getAccidents() {
        return accidentRep.getAll();
    }

    public Collection<AccidentType> getAccidentsTypes() {
        return accidentRep.getTypes();
    }

    public Collection<Rule> getRules() {
        return accidentRep.getRules();
    }

    public Accident getById(int id) {
        return accidentRep.getById(id);
    }

    public Rule getRuleById(int id) {
        return accidentRep.getRuleById(id);
    }

    public AccidentType getTypeById(int id) {
        return accidentRep.getTypeById(id);
    }

    public void add(Accident accident) {
        accidentRep.add(accident);
    }

    public void edit(int id, Accident accident) {
        accidentRep.edit(id, accident);
    }
}
