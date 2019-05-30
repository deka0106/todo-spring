package jp.ac.titech.itsp.todo.controllers.event

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import javassist.NotFoundException
import jp.ac.titech.itsp.todo.models.Event
import jp.ac.titech.itsp.todo.services.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event")
class EventController {

    @Autowired
    lateinit var eventService: EventService

    @PostMapping
    fun register(@RequestBody request: NewEventRequest): ResponseEntity<NewEventResponse> {
        val event = eventService.create(request.title, request.memo, request.deadline)
        return ResponseEntity.ok(NewEventResponse("success", "registered", event.id))
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException::class)
    fun handleError(): ResponseEntity<NewEventResponse> =
        ResponseEntity.badRequest().body(NewEventResponse("failed", "invalid date format"))

    @GetMapping
    fun getAll() = ResponseEntity.ok(eventService.getAll())

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ResponseEntity<Event> {
        return try {
            ResponseEntity.ok(eventService.get(id))
        } catch (e: NotFoundException) {
            ResponseEntity.notFound().build<Event>()
        }
    }

}