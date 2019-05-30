package jp.ac.titech.itsp.todo.controllers.event

import java.util.*

data class NewEventRequest(
    val title: String = "",
    val memo: String = "",
    val deadline: Date = Date()
)
