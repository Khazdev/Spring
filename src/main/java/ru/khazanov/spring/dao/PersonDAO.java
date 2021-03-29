package ru.khazanov.spring.dao;

import org.springframework.stereotype.Component;
import ru.khazanov.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khazanov
 **/
@Component
public class PersonDAO {
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(1, "first", 22, "1@1.ru"));
        people.add(new Person(2, "second", 23, "2@2.ru"));
        people.add(new Person(3, "third", 24, "3@3.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }


    public void save(Person person) {
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person person = show(id);
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

}
