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

    @Column(name = "city_of_living")
    private String cityOfLiving;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Person() {
    }

    public Person(Account account, String cityOfLiving) {
        this.account = account;
        this.cityOfLiving = cityOfLiving;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCityOfLiving() {
        return cityOfLiving;
    }

    public void setCityOfLiving(String cityOfLiving) {
        this.cityOfLiving = cityOfLiving;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
