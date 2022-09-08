package br.com.cleanarchitecture.infrastructure.controller.handler

import br.com.cleanarchitecture.domain.pattern.dto.exception.ExceptionHandlerDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
internal class ControllerHandler {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handler(req: HttpServletRequest, e: Exception): ResponseEntity<ExceptionHandlerDTO> {
        logger.error("E=Houve um erro ao tentar processar a requisicao, M=${e.message}", e)
        val exceptionHandlerDTO = ExceptionHandlerDTO(
            message = e.message ?: "Erro interno"
        )

        return ResponseEntity.internalServerError().body(exceptionHandlerDTO)
    }
}
