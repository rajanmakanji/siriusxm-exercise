package com.siriusxm.datatransform.siriusxmexercise.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

public class TrafficIncident {

    private String _id;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Geo geo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String roadName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Tmc tmc;
    private int eventCode;
    private int severity;
    private String validStart;
    private String validEnd;
    private String type;
    private String lastUpdated;

    public TrafficIncident() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Tmc getTmc() {
        return tmc;
    }

    public void setTmc(Tmc tmc) {
        this.tmc = tmc;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getValidStart() {
        return validStart;
    }

    public void setValidStart(String validStart) {
        this.validStart = validStart;
    }

    public String getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(String validEnd) {
        this.validEnd = validEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficIncident that = (TrafficIncident) o;
        return eventCode == that.eventCode &&
                severity == that.severity &&
                Objects.equals(_id, that._id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(geo, that.geo) &&
                Objects.equals(roadName, that.roadName) &&
                Objects.equals(tmc, that.tmc) &&
                Objects.equals(validStart, that.validStart) &&
                Objects.equals(validEnd, that.validEnd) &&
                Objects.equals(type, that.type) &&
                Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, description, geo, roadName, tmc, eventCode, severity, validStart, validEnd, type, lastUpdated);
    }

    @Override
    public String toString() {
        return "TrafficIncident{" +
                "_id='" + _id + '\'' +
                ", description='" + description + '\'' +
                ", geo=" + geo +
                ", roadName='" + roadName + '\'' +
                ", tmc=" + tmc +
                ", eventCode=" + eventCode +
                ", severity=" + severity +
                ", validStart='" + validStart + '\'' +
                ", validEnd='" + validEnd + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
