package com.siriusxm.datatransform.siriusxmexercise.domain.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class TrafficIncidentXmlClient {

    /**
     * Retrieve data from S3, then convert xml data to JSONObject node
     * @return
     * @throws Exception
     */
    public static JSONObject getIncidentData(String s3Bucket, String xmlFileName) throws Exception {
        final AmazonS3 s3 = AmazonS3ClientBuilder
                                .standard()
                                .withRegion(Regions.US_EAST_1)
                                .build();

        S3Object s3Object = s3.getObject(new GetObjectRequest(s3Bucket, xmlFileName));
        InputStream xmlFileStream = new GZIPInputStream(s3Object.getObjectContent());
        return getJsonObjectFromXml(xmlFileStream);
    }

    /**
     * Convert XML InputStream to String, then convert the String to a JSONObject node
     * @param input
     * @return xml data as JSONObject
     * @throws IOException
     */
    private static JSONObject getJsonObjectFromXml(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        String xmlString = "";
        while ((line = reader.readLine()) != null) {
            xmlString += line;
        }
        return XML.toJSONObject(xmlString);
    }
}
