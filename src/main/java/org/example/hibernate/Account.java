package org.example.hibernate;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Account implements Serializable {
    private String name;
    private String surname;
    private Integer age;

    public Account() {
    }

    public Account(String name, String surname, Integer age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account acc = (Account) o;
        return name.equals(acc.name) && surname.equals(acc.surname) && age.equals(acc.age);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + 31 * surname.hashCode() + age;
    }
}
