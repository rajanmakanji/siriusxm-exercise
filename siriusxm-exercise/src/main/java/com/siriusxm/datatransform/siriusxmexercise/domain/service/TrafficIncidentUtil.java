package com.siriusxm.datatransform.siriusxmexercise.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siriusxm.datatransform.siriusxmexercise.domain.model.TrafficIncidentList;
import org.json.JSONObject;

public class TrafficIncidentUtil {

    /**
     * Get TrafficIncidentList and convert to json string
     * @param s3Bucket
     * @param xmlFilename
     * @return
     */
    public static String getTrafficIncidentsAsJsonString(String s3Bucket, String xmlFilename){
        TrafficIncidentList incidentList = getTrafficIncidents(s3Bucket, xmlFilename);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(incidentList);
        } catch (JsonProcessingException e) {
            jsonString = "Exception while converting data to json string";
        }
        return jsonString;
    }

    /**
     * Get TrafficIncidentList Object
     * @param s3Bucket
     * @param xmlFilename
     * @return
     */
    public static TrafficIncidentList getTrafficIncidents(String s3Bucket, String xmlFilename){
        TrafficIncidentList incidentList;
        try {
            JSONObject jsonObject = TrafficIncidentXmlClient.getIncidentData(s3Bucket, xmlFilename);
            incidentList = TrafficIncidentMapper.mapToTrafficIncidentList(jsonObject);
        } catch (Exception e) {
            incidentList = new TrafficIncidentList();
        }
        return incidentList;
    }


}
