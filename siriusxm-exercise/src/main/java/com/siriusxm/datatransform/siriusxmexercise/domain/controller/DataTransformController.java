package com.siriusxm.datatransform.siriusxmexercise.domain.controller;

import com.siriusxm.datatransform.siriusxmexercise.domain.model.TrafficIncidentList;
import com.siriusxm.datatransform.siriusxmexercise.domain.service.TrafficIncidentUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataTransformController {

    @Value("${siriusxm.s3.bucket}")
    private String s3Bucket;
    @Value("${siriusxm.s3.key}")
    private String xmlFileName;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/incidents",
            produces = "application/json")
    public TrafficIncidentList getIncidentsJson(){
        return TrafficIncidentUtil.getTrafficIncidents(s3Bucket, xmlFileName);
    }
}
