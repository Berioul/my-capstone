package de.sahbani.backend.Security;

import de.sahbani.backend.ItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void integrationTest() {
        ResponseEntity<String> createUserResponse = restTemplate.postForEntity("/api/users", new UserCreationData("oualid","blabla","blabla"),String.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<Token> loginResponse =
                restTemplate.postForEntity("/api/users/login", new LoginData("oualid","blabla"),Token.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotNull();

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCategory("bla");
        String token = loginResponse.getBody().getToken();
        ResponseEntity<ItemDTO> CreatItemResponse = restTemplate.exchange("/api/todos", HttpMethod.POST,new HttpEntity<>(itemDTO,createHeaders(token)),ItemDTO.class);

        assertThat(CreatItemResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }
    private HttpHeaders createHeaders(String token) {
        String authHeader = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }
}