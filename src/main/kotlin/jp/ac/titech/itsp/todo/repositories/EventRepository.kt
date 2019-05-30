package jp.ac.titech.itsp.todo.repositories

import jp.ac.titech.itsp.todo.models.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : JpaRepository<Event, Long>
