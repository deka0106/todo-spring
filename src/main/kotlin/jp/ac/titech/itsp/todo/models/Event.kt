package jp.ac.titech.itsp.todo.models

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "event")
data class Event(
    @Column(name = "title", nullable = false)
    val title: String = "",

    @Column(name = "memo", nullable = false)
    val memo: String = "",

    @Column(name = "deadline", nullable = false)
    val deadline: Date = Date(),

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    val id: Long = 0
)
