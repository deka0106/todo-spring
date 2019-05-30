package jp.ac.titech.itsp.todo.controllers.event

import jp.ac.titech.itsp.todo.models.Event
import jp.ac.titech.itsp.todo.repositories.EventRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.*
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerTests {

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @BeforeEach
    fun before() {
        eventRepository.saveAll((1..5L).map { Event("title $it", "memo $it", Date(), it) })
    }

    @Test
    fun register() {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val actual = testRestTemplate.postForEntity<NewEventResponse>(
            "/api/v1/event",
            HttpEntity("""{"deadline": "2019-06-11T14:00:00+09:00", "title": "レポート提出", "memo": ""}""", headers)
        )
        assertEquals("success", actual.body?.status)
        assertNotNull(actual.body?.id)
    }

    @Test
    fun registerFail() {
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        val actual = testRestTemplate.postForEntity<NewEventResponse>(
            "/api/v1/event",
            HttpEntity("""{"deadline": "2019-06-11", "title": "レポート提出", "memo": ""}""", headers)
        )
        assertEquals("failed", actual.body?.status)
        assertNull(actual.body?.id)
    }

    @Test
    fun get() {
        val actual: ResponseEntity<Event> = testRestTemplate.getForEntity("/api/v1/event/1")
        assertEquals(HttpStatus.OK, actual.statusCode)
    }

    @Test
    fun getNotFound() {
        val actual: ResponseEntity<Event> = testRestTemplate.getForEntity("/api/v1/event/114514")
        assertEquals(HttpStatus.NOT_FOUND, actual.statusCode)
    }

    @Test
    fun getAll() {
        val actual: ResponseEntity<List<Event>> = testRestTemplate.getForEntity("/api/v1/event")
        assertEquals(HttpStatus.OK, actual.statusCode)
    }

}
