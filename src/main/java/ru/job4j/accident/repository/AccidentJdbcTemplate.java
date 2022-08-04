package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void add(Accident accident) {
        String insertSql = "insert into accident (name, text, address, accident_type) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        int accidentId = keyHolder.getKey().intValue();
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)", accidentId, rule.getId());
        }
    }

    public void delete(int id) {
        jdbc.update("delete from accident where id = ?", id);
        jdbc.update("delete from accident_rule where accident_id = ?", id);
    }

    public void edit(int id, Accident accident) {
        jdbc.update("update accident set (name, text, address, accident_type) = (?, ?, ?, ?) where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(), id);
        jdbc.update("delete from accident_rule where accident_id = ?", id);
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)", id, rule.getId());
        }
    }

    public Collection<Accident> getAll() {
        return jdbc.query("select id, name, text, address, accident_type from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getTypeById(rs.getInt("accident_type")));
                    return accident;
                });
    }

    public Accident getById(int id) {
        return jdbc.queryForObject(
                "select id, name, text, address, accident_type from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getTypeById(rs.getInt("accident_type")));
                    accident.setRules(this.getRulesById(rs.getInt("id")));
                    return accident;
                }, id
        );
    }

    public List<AccidentType> getTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public AccidentType getTypeById(int id) {
        return jdbc.queryForObject(
                "select id, name from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id
        );
    }

    public List<Rule> getRules() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public List<Rule> getRulesById(int id) {
        return jdbc.query(
                "select accident_id, rule_id from accident_rule where accident_id = ?",
                (rs, row) -> getRuleById(rs.getInt("rule_id")), id
        );
    }

    public Rule getRuleById(int id) {
        return jdbc.queryForObject(
                "select id, name from rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id
        );
    }
}
