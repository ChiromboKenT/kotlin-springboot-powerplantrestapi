package assessments.com.powerplantrestapi.controller

import assessments.com.powerplantrestapi.error.NotFoundException
import assessments.com.powerplantrestapi.error.UnauthorizedException
import assessments.com.powerplantrestapi.model.WebResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): ResponseEntity<WebResponse<String>> {

        return ResponseEntity(WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        ),HttpStatus.BAD_REQUEST)

    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException):  ResponseEntity<WebResponse<String>>  =
        ResponseEntity( WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = "Not found"
        ),HttpStatus.NOT_FOUND)


    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorized(unauthorizedException: UnauthorizedException): ResponseEntity<WebResponse<String>> =
        ResponseEntity(WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = "Please put your X-Api-Key"
        ),HttpStatus.UNAUTHORIZED)

}