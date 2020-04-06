package com.siriusxm.datatransform.siriusxmexercise.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrafficIncidentList {

    private List<TrafficIncident> locations;

    public TrafficIncidentList() {
        this.setLocations(new ArrayList<>());
    }

    public TrafficIncidentList(List<TrafficIncident> locations) {
        this.locations = locations;
    }

    public List<TrafficIncident> getLocations() {
        return locations;
    }

    public void setLocations(List<TrafficIncident> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficIncidentList that = (TrafficIncidentList) o;
        return Objects.equals(locations, that.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locations);
    }

    @Override
    public String toString() {
        return "TrafficIncidentList{" +
                "locations=" + locations +
                '}';
    }
}
