package jp.ac.titech.itsp.todo.controllers.event

import jp.ac.titech.itsp.todo.controllers.Response

data class NewEventResponse(
    override val status: String = "",
    override val message: String = "",
    val id: Long? = null
) : Response
