package br.com.zup.handora.cadastrobasico5.exceptions;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErroPadronizado handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        Integer codigoHttp = badRequestStatus.value();
        String mensagemHttp = badRequestStatus.getReasonPhrase();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Integer totalErros = fieldErrors.size();
        String palavraErro = totalErros == 1 ? "erro" : "erros";
        String mensagemGeral = "Validação falhou com " + totalErros + " " + palavraErro + ".";

        ErroPadronizado erroPadronizado = new ErroPadronizado(
            codigoHttp, mensagemHttp, mensagemGeral
        );
        fieldErrors.forEach(erroPadronizado::adicionarErro);

        return erroPadronizado;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    ErroPadronizado handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                 HandlerMethod handlerMethod) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        Integer codigoHttp = badRequestStatus.value();
        String mensagemHttp = badRequestStatus.getReasonPhrase();

        String mensagemGeral = "Erro de formatação JSON";

        ErroPadronizado erroPadronizado = new ErroPadronizado(
            codigoHttp, mensagemHttp, mensagemGeral
        );

        Throwable causaMaisEspecifica = ex.getMostSpecificCause();

        if (causaMaisEspecifica instanceof InvalidFormatException) {
            if (causaMaisEspecifica.getMessage()
                                   .startsWith(
                                       "Cannot deserialize value of type "
                                               + "`br.com.zup.handora.cadastrobasico5.models.TipoPet`"
                                               + " from String"
                                   )) {
                erroPadronizado.adicionarErro(
                    "O tipo fornecido possui um valor que não é aceito pela API. Valores aceitos: CAO, GATO."
                );
            }
        } else if (causaMaisEspecifica instanceof DateTimeParseException
                || causaMaisEspecifica instanceof DateTimeException) {
            erroPadronizado.adicionarErro(
                "A data fornecida está em um formato incorreto. O formato correto é: dd/MM/yyyy."
            );
        }

        return erroPadronizado;
    }

}
