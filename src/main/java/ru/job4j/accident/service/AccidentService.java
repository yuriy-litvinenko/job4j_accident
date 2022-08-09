package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentRepository accidentRep;
    private final AccidentTypeRepository accidentTypeRep;
    private final AccidentRuleRepository accidentRuleRep;

    public AccidentService(AccidentRepository accidentRep, AccidentTypeRepository accidentTypeRep,
                           AccidentRuleRepository accidentRuleRep) {
        this.accidentRep = accidentRep;
        this.accidentTypeRep = accidentTypeRep;
        this.accidentRuleRep = accidentRuleRep;
    }

    @Transactional
    public void update(Accident accident) {
        accidentRep.save(accident);
    }

    public Collection<Accident> getAccidents() {
        return accidentRep.getAll();
    }

    public Accident getById(int id) {
        return accidentRep.getById(id);
    }

    public Collection<AccidentType> getAccidentsTypes() {
        return accidentTypeRep.getTypes();
    }

    public AccidentType getTypeById(int id) {
        return accidentTypeRep.getTypeById(id);
    }

    public Collection<Rule> getRules() {
        return accidentRuleRep.getRules();
    }

    public Rule getRuleById(int id) {
        return accidentRuleRep.getRuleById(id);
    }
}
