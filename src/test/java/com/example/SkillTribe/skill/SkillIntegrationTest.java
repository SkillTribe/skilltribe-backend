package com.example.SkillTribe.skill;

import com.example.SkillTribe.SkillTribeApplication;
import com.example.SkillTribe.dto.request.SkillRequest;
import com.example.SkillTribe.dto.response.SkillResponse;
import com.example.SkillTribe.model.enums.ESkillLevel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SkillTribeApplication.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:integration-test-properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getListOfSkillsTest() throws Exception {
        for(int i = 0; i <5; i++)
            createDefaultSkill();

        String response = mvc.perform(get("/skills"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        List<SkillResponse> skillResponses = getRequiredTypeFromJson(response,  new TypeReference<List<SkillResponse>>() {});
        assertEquals(5, skillResponses.size());
    }

    @Test
    public void getSkillByIdTest() throws Exception {
        SkillResponse skillResponseFromPost = createDefaultSkill();

        String response = mvc.perform(get("/skills/" + skillResponseFromPost.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        SkillResponse skillResponseFromGet = getRequiredTypeFromJson(response,  new TypeReference<SkillResponse>() {});
        assertEquals(skillResponseFromPost.getId(), skillResponseFromGet.getId());
    }

    @Test
    public void getListOfPrerequisiteSkillsByIdTest() throws Exception {
        List<SkillResponse> skillResponses = new ArrayList<>();
        for(int i = 0; i <5; i++)
            skillResponses.add(createDefaultSkill());

        for(int i = 1; i < skillResponses.size(); i++){
            String addUri = "/skills/" + skillResponses.get(0).getId() + "/add-prerequisite-skill/"+ skillResponses.get(i).getId();
            mvc.perform(patch(addUri))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString();
        }
        String response = mvc.perform(get("/skills/prerequisite-skills/" + skillResponses.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        List<SkillResponse> skillResponseFromGet = getRequiredTypeFromJson(response,  new TypeReference<List<SkillResponse>>() {});
        assertEquals(4, skillResponseFromGet.size());
    }

    @Test
    public void createSkillWithoutPrerequisiteSkillsTest() throws Exception {
        SkillRequest skillRequest = getDefaultSkillRequest();

        SkillResponse skillResponse = createDefaultSkill();

        assertNotNull(skillResponse);
        assertNotNull(skillResponse.getId());
        assertEquals(skillRequest.getName(), skillResponse.getName());
        assertEquals(skillRequest.getDescription(), skillResponse.getDescription());
        assertEquals(skillRequest.getSkillLevel(), skillResponse.getSkillLevel());
    }

    @Test
    public void createSkillWithPrerequisiteSkillsTest() throws Exception {
        SkillResponse prerequisiteSkillResponse = createDefaultSkill();

        SkillRequest skillRequest = SkillRequest
                .builder()
                .name("tester skill")
                .description("this test create skill endpoint")
                .skillLevel(ESkillLevel.BEGINNER)
                .prerequisiteSkills(Set.of(prerequisiteSkillResponse.getId()))
                .build();

        String response = mvc.perform(post("/skills")
                        .content(getJson(skillRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        SkillResponse skillResponse = getRequiredTypeFromJson(response, new TypeReference<SkillResponse>() {});

        assertNotNull(skillResponse);
        assertNotNull(skillResponse.getId());
        assertEquals(skillRequest.getName(), skillResponse.getName());
        assertEquals(skillRequest.getDescription(), skillResponse.getDescription());
        assertEquals(skillRequest.getSkillLevel(), skillResponse.getSkillLevel());
        assertTrue(compareRequestAndResponseSet(skillResponse.getPrerequisiteSkills(), skillRequest.getPrerequisiteSkills()));
    }

    @Test
    public void updateSkillTest() throws Exception {
        SkillResponse skillResponse = createDefaultSkill();

        SkillRequest updateSkillRequest = SkillRequest
                .builder()
                .name(skillResponse.getName())
                .description("This test update skill endpoint")
                .skillLevel(skillResponse.getSkillLevel())
                .build();

        String updateResponse = mvc.perform(put("/skills/" + skillResponse.getId())
                        .content(getJson(updateSkillRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        SkillResponse skillUpdateResponse = getRequiredTypeFromJson(updateResponse, new TypeReference<SkillResponse>() {});

        assertNotNull(skillUpdateResponse);
        assertNotNull(skillUpdateResponse.getId());
        assertEquals(updateSkillRequest.getName(), skillUpdateResponse.getName());
        assertEquals(updateSkillRequest.getDescription(), skillUpdateResponse.getDescription());
        assertEquals(updateSkillRequest.getSkillLevel(), skillUpdateResponse.getSkillLevel());
    }

    @Test
    public void deleteSkillsTest() throws Exception {
        SkillResponse skillResponse = createDefaultSkill();

        mvc.perform(delete("/skills/" + skillResponse.getId()))
                .andExpect(status().isNoContent());

        mvc.perform(get("/skills/" + skillResponse.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addAndRemovePreSkillTest() throws Exception {
        SkillResponse prerequisiteSkillResponse = createDefaultSkill();

        SkillRequest skillRequest = SkillRequest
                .builder()
                .name("tester skill with pre")
                .description("this test create skill endpoint")
                .skillLevel(ESkillLevel.BEGINNER)
                .build();

        String responseJson = mvc.perform(post("/skills")
                        .content(getJson(skillRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        SkillResponse skillResponse = getRequiredTypeFromJson(responseJson, new TypeReference<SkillResponse>() {});

        String addUri = "/skills/" + skillResponse.getId() + "/add-prerequisite-skill/"+ prerequisiteSkillResponse.getId();

        String addPreResponseJson = mvc.perform(patch(addUri))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse().getContentAsString();
        SkillResponse addPreResponse = getRequiredTypeFromJson(addPreResponseJson, new TypeReference<SkillResponse>() {});

        assertTrue(containsPreSkill(addPreResponse.getPrerequisiteSkills(), prerequisiteSkillResponse.getId()));

        String removeUri = "/skills/" + skillResponse.getId() + "/remove-prerequisite-skill/"+ prerequisiteSkillResponse.getId();

        String removePreResponseJson = mvc.perform(patch(removeUri))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        SkillResponse removePreResponse = getRequiredTypeFromJson(removePreResponseJson, new TypeReference<SkillResponse>() {});

        assertFalse(containsPreSkill(removePreResponse.getPrerequisiteSkills(), prerequisiteSkillResponse.getId()));
    }

    // TEST UTILS
    private SkillResponse createDefaultSkill() throws Exception {
       String response = mvc.perform(post("/skills")
                        .content(getJson(getDefaultSkillRequest())).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
       return getRequiredTypeFromJson(response, new TypeReference<SkillResponse>() {});
    }
    private SkillRequest getDefaultSkillRequest(){
        return SkillRequest
                .builder()
                .name("tester skill")
                .description("this test create skill endpoint")
                .skillLevel(ESkillLevel.BEGINNER)
                .build();
    }
    private boolean containsPreSkill(Set<SkillResponse> response, Long id){
        return response.stream().anyMatch(preSkill -> preSkill.getId().equals(id));

    }
    private boolean compareRequestAndResponseSet(Set<SkillResponse> response, Set<Long> request){
        if(response.size() != request.size())
            return false;
        Set<Long> responsePrerequishedIds = response.stream().map(SkillResponse::getId).collect(Collectors.toSet());
        for(Long id : responsePrerequishedIds){
            if(!request.contains(id))
                return false;
        }
        return true;
    }
    private String getJson(Object o) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
    private <T> T getRequiredTypeFromJson(String json, TypeReference<T> type) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(json);
        return om.convertValue(jsonNode, type);
    }
}
