package com.siriusxm.datatransform.siriusxmexercise.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.siriusxm.datatransform.siriusxmexercise.domain.model.Geo;
import com.siriusxm.datatransform.siriusxmexercise.domain.model.Tmc;
import com.siriusxm.datatransform.siriusxmexercise.domain.model.TrafficIncident;
import com.siriusxm.datatransform.siriusxmexercise.domain.model.TrafficIncidentList;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.siriusxm.datatransform.siriusxmexercise.common.Constants.*;

public class TrafficIncidentMapper {


    /**
     * Parse JSONObject node and map to TrafficIncidentList
     * @param jsonObject
     * @return TrafficIncidentList
     */
    public static TrafficIncidentList mapToTrafficIncidentList(JSONObject jsonObject){
        ObjectMapper mapper = new ObjectMapper();
        TrafficIncidentList trafficIncidentList = new TrafficIncidentList();
        JsonNode root;
        try {
            root = mapper.readTree(jsonObject.toString());
        } catch (JsonProcessingException e) {
            return trafficIncidentList;
        }
        // if the xml incident node && incident.tv node are not null then continue parsing
        if(root.get(INCIDENT) != null && root.get(INCIDENT).get(TI) != null){
            JsonNode ti = root.get(INCIDENT).get(TI);
            String at = ti.get(AT).asText();
            int table = ti.get(TABLE).asInt();

            //if ev nodes are not null and not empty, then map each evNode to TrafficIncident
            //map each one in a multithreaded fashion using completablefutures
            ArrayNode evNodes = (ArrayNode) ti.get(EV);
            if(evNodes != null && evNodes.size() > 0){
                List<CompletableFuture<TrafficIncident>>  futureIncidentsList = new ArrayList<>();
                for(int i=0; i<evNodes.size(); i++){
                    JsonNode currentEvNode = evNodes.get(i);
                    futureIncidentsList.add(CompletableFuture.supplyAsync(() -> mapToTrafficIncident(currentEvNode, table, at)));
                }
                //wait for futures to complete and then map them to a list of traffic incident objects
                //set locations attribute with the list obtained from the futures.
                List<TrafficIncident> trafficIncidents = futureIncidentsList.stream().map(CompletableFuture::join).collect(Collectors.toList());
                trafficIncidentList.setLocations(trafficIncidents);
            }
        }
        return trafficIncidentList;
    }


    /**
     * Map individual event node to TrafficIncident object
     * @param evNode
     * @param table
     * @param lastUpdated
     * @return TrafficIncident
     */
    private static TrafficIncident mapToTrafficIncident(JsonNode evNode, int table, String lastUpdated){
        TrafficIncident trafficIncident = new TrafficIncident();
        trafficIncident.set_id(evNode.get(ID) != null ? evNode.get(ID).asText() : null);
        trafficIncident.setEventCode(evNode.get(EC) != null ? evNode.get(EC).asInt() : null);
        trafficIncident.setSeverity(evNode.get(SE) != null ? evNode.get(SE).asInt() : null);
        trafficIncident.setType(TRAFFIC_INCIDENT);
        trafficIncident.setLastUpdated(lastUpdated);
        trafficIncident.setDescription(evNode.get(TEXT) != null && evNode.get(TEXT).get(CONTENT) != null ?
                evNode.get(TEXT).get(CONTENT).asText() : null);

        trafficIncident.setValidStart(evNode.get(VALID) != null && evNode.get(VALID).get(START) != null ?
                evNode.get(VALID).get(START).asText() : null);

        trafficIncident.setValidEnd(evNode.get(VALID) != null && evNode.get(VALID).get(END) != null ?
                evNode.get(VALID).get(END).asText() : null);

        //set loc based on loc.type
        JsonNode locNode = evNode.get(LOC);
        switch (locNode.get(TYPE).asText()){
            case GEO:
                trafficIncident.setGeo(mapToGeo(locNode));
                trafficIncident.setRoadName(locNode.get(ADDR) != null ? locNode.get(ADDR).asText() : null);
                break;
            case TMC:
                trafficIncident.setTmc(mapToTmc(locNode, table));
                break;
            default:
                break;
        }
        return trafficIncident;
    }

    private static Geo mapToGeo(JsonNode locNode){
        double[] coordinates = {locNode.get(GEO).get(LON).asDouble(), locNode.get(GEO).get(LAT).asDouble()};
        return new Geo(POINT, coordinates);
    }

    private static Tmc mapToTmc(JsonNode locNode, int table){
        return new Tmc(table, locNode.get(START).get(ID).asInt(), locNode.get(START).get(DIR).asText());
    }
}
