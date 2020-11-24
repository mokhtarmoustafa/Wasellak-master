package com.unicom.wasalakclientproduct.model;

import java.util.Objects;

public class CountryClass implements Comparable<CountryClass> {
    private int id;
    private String name;

    public CountryClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public int compareTo(CountryClass o) {
        if(o==null)
            return -1;
        else
        return this.name.compareTo(o.name);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CountryClass that = (CountryClass) o;
//        return id == that.id &&
//                Objects.equals(name, that.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
}
