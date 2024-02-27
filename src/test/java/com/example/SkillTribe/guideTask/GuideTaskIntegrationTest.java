package com.example.SkillTribe.guideTask;

import com.example.SkillTribe.BaseIntegrationTest;
import com.example.SkillTribe.SkillTribeApplication;
import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.response.GuideResponse;
import com.example.SkillTribe.dto.response.GuideTaskResponse;
import com.example.SkillTribe.model.enums.ETaskGoal;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SkillTribeApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = {"/test-guide-task.sql"}, config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class GuideTaskIntegrationTest extends BaseIntegrationTest {

    @Test
    public void getAllGuideTaskTest() throws Exception {
        String response = mvc
                .perform(get("/guide-tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        List<GuideTaskResponse> guideTasks = getRequiredTypeFromJson(response, new TypeReference<List<GuideTaskResponse>>() {});
        assertEquals(4, guideTasks.size());
    }

    @Test
    public void getAllGuideTaskByGuideIdTest() throws Exception {
        long guideId = 1;
        String response = mvc
                .perform(get("/guide-tasks/get-by-guide/" + guideId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        List<GuideTaskResponse> guideTasks = getRequiredTypeFromJson(response, new TypeReference<List<GuideTaskResponse>>() {});

        String responseGuides = mvc
                .perform(get("/guides/" + guideId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        GuideResponse guideResponse = getRequiredTypeFromJson(responseGuides, new TypeReference<GuideResponse>() {});


        assertEquals(3, guideTasks.size());
        assertEquals(guideTasks.size(), guideResponse.getGuidePlan().getGuideTasks().size());
        List<Long> guideTaskIds = guideTasks.stream().map(GuideTaskResponse::getId).toList();
        List<Long> guideTaskIdsFromGuide = guideResponse.getGuidePlan().getGuideTasks().stream().map(GuideTaskResponse::getId).toList();
        guideTaskIdsFromGuide.forEach(id -> assertTrue(guideTaskIds.contains(id)));
    }

    @Test
    public void getGuideTaskByIdTest() throws Exception {
        String response = mvc
                .perform(get("/guide-tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        GuideTaskResponse guideTask = getRequiredTypeFromJson(response, new TypeReference<GuideTaskResponse>() {});
        assertEquals(1, guideTask.getId());
    }

    @Test
    public void addGuideTaskTest() throws Exception {
        GuideTaskRequest guideTaskRequest = new GuideTaskRequest(3);
        guideTaskRequest.setName("Task by test");
        guideTaskRequest.setDescription("Description by test");
        guideTaskRequest.setTaskGoal(ETaskGoal.LEVEL_UP);

        String responseJson = mvc
                .perform(post("/guide-tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(guideTaskRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        GuideTaskResponse response = getRequiredTypeFromJson(responseJson, new TypeReference<GuideTaskResponse>() {});

        assertNotNull(response.getId());
        assertEquals(guideTaskRequest.getName(), response.getName());
        assertEquals(guideTaskRequest.getDescription(), response.getDescription());
        assertEquals(guideTaskRequest.getTaskGoal(), response.getTaskGoal());
        assertEquals(guideTaskRequest.getRepetition(), response.getRepetition());
    }

    @Test
    public void modifyGuideTaskTest() throws Exception {
        GuideTaskRequest guideTaskRequest = new GuideTaskRequest(4);
        guideTaskRequest.setName("Task by test");
        guideTaskRequest.setDescription("Description by test");
        guideTaskRequest.setTaskGoal(ETaskGoal.LEVEL_UP);

        long id = 1;

        String responseJson = mvc
                .perform(put("/guide-tasks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJson(guideTaskRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        GuideTaskResponse response = getRequiredTypeFromJson(responseJson, new TypeReference<GuideTaskResponse>() {});

        assertEquals(id, response.getId());
        assertEquals(guideTaskRequest.getName(), response.getName());
        assertEquals(guideTaskRequest.getDescription(), response.getDescription());
        assertEquals(guideTaskRequest.getTaskGoal(), response.getTaskGoal());
        assertEquals(guideTaskRequest.getRepetition(), response.getRepetition());
    }

    @Test
    public void deleteGuideTaskTest() throws Exception {
        long id = 1;
        mvc.perform(delete("/guide-tasks/" + id))
                .andExpect(status().isNoContent());

        mvc.perform(get("/guide-tasks/" + id))
                .andExpect(status().isNotFound());
    }
}
