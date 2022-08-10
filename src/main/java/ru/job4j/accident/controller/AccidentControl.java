package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
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
        Collection<Rule> rules = service.getRules();
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Accident accident = service.getById(id);
        model.addAttribute("accident", accident);
        Collection<Rule> accRules = accident.getRules();
        model.addAttribute("accRules", accRules);
        Collection<AccidentType> types = service.getAccidentsTypes();
        model.addAttribute("types", types);
        Collection<Rule> rules = service.getRules();
        model.addAttribute("rules", rules);
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        for (String rId : rIds) {
            accident.addRule(service.getRuleById(Integer.parseInt(rId)));
        }
        accident.setType(service.getTypeById(typeId));
        service.update(accident);
        return "redirect:/";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId,
                         HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        for (String rId : rIds) {
            accident.addRule(service.getRuleById(Integer.parseInt(rId)));
        }
        accident.setType(service.getTypeById(typeId));
        service.update(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.getById(id));
        return "accident/update";
    }
}
