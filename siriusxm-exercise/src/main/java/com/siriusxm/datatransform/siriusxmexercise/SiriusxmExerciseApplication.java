package com.siriusxm.datatransform.siriusxmexercise;

import com.siriusxm.datatransform.siriusxmexercise.domain.service.TrafficIncidentUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiriusxmExerciseApplication {

    public static void main(String[] args) {
        //uncomment line below to start the springboot application server
        SpringApplication.run(SiriusxmExerciseApplication.class, args);


        /**
         * TrafficIncidentUtil class has two methods. Both require the s3bucket and filename to be passed as arguments.
         * 1) Retrieve the data in the form of the TrafficIncidentList Java Object
         *
         * 2) Retrieve the data as a json string
         *
         * Below is an example of calling the util class using the second approach.
         */
        String incidentListJsonString =
                TrafficIncidentUtil.getTrafficIncidentsAsJsonString(
                        "sxm-dataservices-samples",
                        "incidents.xml.gz");
        System.out.println(incidentListJsonString);
    }

}
