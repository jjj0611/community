package community.content.myanglog.api;

import static community.content.myanglog.api.dto.PostRequestDtoTest.getPostRequestDtoFixture;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import community.content.ContentApiApplication;
import community.content.myanglog.api.dto.PostRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest(classes = ContentApiApplication.class, webEnvironment = RANDOM_PORT)
@Transactional
@Sql("/data/posts.sql")
public class PostControllerIntTest {
  private @Autowired MockMvc mvc;
  private @Autowired ObjectMapper objectMapper;

  @Test
  void create_ValidInput_ValidOutput() throws Exception {
    PostRequestDto postRequestDto = getPostRequestDtoFixture();

    this.mvc.perform(post("/myanglog/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(postRequestDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNumber());
  }
}
