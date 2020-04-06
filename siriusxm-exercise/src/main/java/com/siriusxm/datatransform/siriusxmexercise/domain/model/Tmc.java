package com.siriusxm.datatransform.siriusxmexercise.domain.model;

import java.util.Objects;

public class Tmc {
    private int table;
    private int id;
    private String direction;

    public Tmc() {
    }

    public Tmc(int table, int id, String direction) {
        this.table = table;
        this.id = id;
        this.direction = direction;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tmc tmc = (Tmc) o;
        return table == tmc.table &&
                id == tmc.id &&
                Objects.equals(direction, tmc.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, id, direction);
    }

    @Override
    public String toString() {
        return "Tmc{" +
                "table=" + table +
                ", id=" + id +
                ", direction='" + direction + '\'' +
                '}';
    }
}
