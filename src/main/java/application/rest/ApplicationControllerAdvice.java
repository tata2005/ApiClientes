package application.rest;

import application.rest.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    // Este método trata erros de validação de dados enviados pelo cliente.
    // Ele será chamado automaticamente quando um campo inválido for detectado em uma requisição.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Retorna o status 400 (requisição malformada).
    public ApiErrors hendleValidationErros(MethodArgumentNotValidException ex) {
        // Captura os resultados da validação que falhou.
        BindingResult bindingResult = ex.getBindingResult();

        // Extrai todas as mensagens de erro da validação.
        List<String> messagens = bindingResult.getAllErrors()
                .stream() // Transforma a lista de erros em um fluxo de dados.
                .map(objectError -> objectError.getDefaultMessage()) // Pega a mensagem padrão de cada erro.
                .collect(Collectors.toList()); // Junta tudo em uma lista de Strings.

        // Cria e retorna um objeto ApiErrors com todas as mensagens de erro.
        return new ApiErrors(messagens);
    }

    // Este método trata exceções do tipo ResponseStatusException.
    // Essas exceções são lançadas manualmente quando queremos retornar um erro com um código específico.
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
        // Pega a mensagem de erro da exceção.
        String mensagemError = ex.getReason();

        // Pega o código de status HTTP associado à exceção.
        HttpStatus codigoStatus = ex.getStatus();

        // Cria um objeto ApiErrors com a mensagem de erro.
        ApiErrors apiErrors = new ApiErrors(mensagemError);

        // Retorna uma resposta HTTP contendo o erro e o código de status.
        return new ResponseEntity(apiErrors, codigoStatus);
    }
}

