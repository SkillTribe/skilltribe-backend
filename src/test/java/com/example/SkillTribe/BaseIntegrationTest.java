package com.example.SkillTribe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class BaseIntegrationTest {
    @Autowired
    protected MockMvc mvc;
    protected String getJson(Object o) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
    protected  <T> T getRequiredTypeFromJson(String json, TypeReference<T> type) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(json);
        return om.convertValue(jsonNode, type);
    }
}
