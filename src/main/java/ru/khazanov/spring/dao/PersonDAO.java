package ru.khazanov.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.khazanov.spring.models.Person;

import java.util.List;



/**
 * @author Khazanov
 **/
@Repository
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person ORDER BY id", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public List<Person> findByName(String name) {

        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new BeanPropertyRowMapper<>(Person.class), name);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name,age,email,phone) VALUES(? , ? ,?, ?)",
                person.getName(), person.getAge(), person.getEmail(), person.getPhone());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(
                "UPDATE Person SET name=?, age=?, email=?, phone=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(),
                updatedPerson.getEmail(), updatedPerson.getPhone(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

    }
}
