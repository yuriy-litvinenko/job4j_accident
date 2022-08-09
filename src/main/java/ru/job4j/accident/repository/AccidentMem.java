package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccidentMem {
    private final AtomicInteger count = new AtomicInteger(4);
    private final ConcurrentHashMap<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
        Accident accident = new Accident(1, "Acc 1", "Text 1", "Address 1");
        accident.setType(getTypeById(2));
        accident.addRule(getRuleById(1));
        accident.addRule(getRuleById(2));
        accidents.put(1, accident);
        Accident accident2 = new Accident(2, "Acc 2", "Text 2", "Address 2");
        accident2.setType(getTypeById(3));
        accident2.addRule(getRuleById(2));
        accident2.addRule(getRuleById(3));
        accidents.put(2, accident2);
        Accident accident3 = new Accident(3, "Acc 3", "Text 3", "Address 3");
        accident3.setType(getTypeById(1));
        accident3.addRule(getRuleById(1));
        accident3.addRule(getRuleById(3));
        accidents.put(3, accident3);
        Accident accident4 = new Accident(4, "Acc 4", "Text 4", "Address 4");
        accident4.setType(getTypeById(2));
        accident4.addRule(getRuleById(1));
        accident4.addRule(getRuleById(2));
        accident4.addRule(getRuleById(3));
        accidents.put(4, accident4);
    }

    public void add(Accident accident) {
        int id = count.incrementAndGet();
        accident.setId(id);
        accidents.put(id, accident);
    }

    public void delete(int id) {
        accidents.remove(id);
    }

    public void edit(int id, Accident accident) {
        accidents.replace(id, accident);
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> getTypes() {
        return accidentTypes.values();
    }

    public AccidentType getTypeById(int id) {
        return accidentTypes.get(id);
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }
}
