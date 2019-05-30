package jp.ac.titech.itsp.todo.services

import javassist.NotFoundException
import jp.ac.titech.itsp.todo.models.Event
import jp.ac.titech.itsp.todo.repositories.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventService {

    @Autowired
    lateinit var eventRepository: EventRepository

    fun create(title: String, memo: String, deadline: Date) = eventRepository.save(Event(title, memo, deadline))

    fun get(id: Long): Event {
        val event = eventRepository.findById(id)
        if (event.isPresent) return event.get()
        throw NotFoundException("Event(id=$id) is not found")
    }

    fun getAll(): List<Event> = eventRepository.findAll()

}
