package com.siriusxm.datatransform.siriusxmexercise.domain.model;

import java.util.Arrays;
import java.util.Objects;

public class Geo {

    private String type;
    private double[] coordinates;

    public Geo() {
    }

    public Geo(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Objects.equals(type, geo.type) &&
                Arrays.equals(coordinates, geo.coordinates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(coordinates);
        return result;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
