package com.nmp90.beagleworld.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.io.IOException
import javax.servlet.http.HttpServletResponse



@RestControllerAdvice
class GlobalExceptionHandlerController {
    @ExceptionHandler(CustomException::class)
    @Throws(IOException::class)
    fun handleCustomException(res: HttpServletResponse, ex: CustomException) {
        res.sendError(ex.httpStatus.value(), ex.message)
    }

}