package jp.ac.titech.itsp.todo.controllers

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
interface Response {
    val status: String
    val message: String
}
