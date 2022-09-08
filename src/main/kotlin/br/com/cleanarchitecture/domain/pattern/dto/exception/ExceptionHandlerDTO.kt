package br.com.cleanarchitecture.domain.pattern.dto.exception

data class ExceptionHandlerDTO(
    val message: String,
) {
    override fun toString(): String {
        return "ExceptionHandlerDTO(message='$message')"
    }
}
