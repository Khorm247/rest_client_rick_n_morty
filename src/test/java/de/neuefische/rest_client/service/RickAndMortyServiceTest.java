package de.neuefische.rest_client.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RickAndMortyServiceTest {

    @Autowired
    private MockMvc mockMvc;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void startMockwebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void stopMockWebServer() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void setMockWebServerUrl(DynamicPropertyRegistry registry) {
        registry.add("basic.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getAllRickAndMortyCharacters() {
    }

    @Test
    void getCharacterById_whenCalledWithId1_thenReturnRick() throws Exception {
        // GIVEN
        mockWebServer.enqueue(new MockResponse()
                .setBody("""
                        {
                            "id": 1,
                            "name": "Rick Sanchez",
                            "status": "Alive",
                            "species": "Human",
                            "type": "",
                            "gender": "Male"
                        }

                        """)
                .addHeader("Content-Type", "application/json"));

        // WHEN + THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "name": "Rick Sanchez",
                            "status": "Alive",
                            "species": "Human"
                        }
                        """));
    }

    @Test
    void getCharacterById_whenCalledWithInvalidId_thenThrowInvalidIdException() throws Exception {
        // GIVEN
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody("Invalid ID")
                .addHeader("Content-Type", "application/json"));

        // WHEN + THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters/1000"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Invalid ID"));
    }

    @Test
    void getCharacterByStatus() {
    }

    @Test
    void getSpeciesStats() {
    }
}