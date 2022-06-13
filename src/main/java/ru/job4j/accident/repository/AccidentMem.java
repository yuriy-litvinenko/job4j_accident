package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final ConcurrentHashMap<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(4);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Acc 1", "Text 1", "Address 1"));
        accidents.put(2, new Accident(2, "Acc 2", "Text 2", "Address 2"));
        accidents.put(3, new Accident(3, "Acc 3", "Text 3", "Address 3"));
        accidents.put(4, new Accident(4, "Acc 4", "Text 4", "Address 4"));
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
}
