package com.ftn.model;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zlatan on 11/25/17.
 */
@Data
@NoArgsConstructor
public class User {

    private String name;

    private int number;


    public User(String name, int number){
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "User: { name=\"" + name + "\"" + ", number=" + number + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        User that = (User) o;
        return this.number == that.number && this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + number;
        return result;
    }
}
