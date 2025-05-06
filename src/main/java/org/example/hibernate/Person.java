package org.example.hibernate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@Entity
public class Person {
    @EmbeddedId
    private Account account;

    @Column
    private String city_of_living;

    public Person() {
    }

    public Person(Account account, String city_of_living) {
        this.account = account;
        this.city_of_living = city_of_living;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCity_of_living() {
        return city_of_living;
    }

    public void setCity_of_living(String city_of_living) {
        this.city_of_living = city_of_living;
    }
}
