package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService service;
    private final AccidentMem accidents;

    public AccidentControl(AccidentService service, AccidentMem accidents) {
        this.service = service;
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        Collection<Accident> accidents = service.getAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Collection<AccidentType> types = service.getAccidentsTypes();
        model.addAttribute("types", types);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.getById(id);
        Collection<AccidentType> types = service.getAccidentsTypes();

        model.addAttribute("accident", accident);
        model.addAttribute("types", types);
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId) {
        accident.setType(accidents.getTypeById(typeId));
        accidents.add(accident);
        return "redirect:/";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId) {
        accident.setType(accidents.getTypeById(typeId));
        accidents.edit(accident.getId(), accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidents.getById(id));
        return "accident/update";
    }
}
