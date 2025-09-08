// Define o pacote onde essa classe está localizada.
// Isso ajuda a organizar o projeto em módulos ou funcionalidades.
package application.rest.exception;

// Importa a anotação @Getter da biblioteca Lombok.
// Essa anotação gera automaticamente o método getErrors(), que permite acessar o campo 'errors'.
import lombok.Getter;

// Importa classes utilitárias da biblioteca padrão do Java.
import java.util.Arrays;  // Usada para transformar um array em uma lista.
import java.util.List;    // Interface que representa uma lista de elementos.

// Classe que representa os erros que serão retornados pela API.
// Ela encapsula uma lista de mensagens de erro para facilitar o envio de respostas padronizadas.
public class ApiErrors {

    // Lombok vai gerar automaticamente o método getErrors().
    // Isso permite que outras partes do código acessem a lista de erros sem precisar escrever o getter manualmente.
    @Getter
    private List<String> errors; // Lista que armazena as mensagens de erro.

    // Construtor que recebe uma lista de mensagens de erro.
    // Útil quando há múltiplos erros, como em validações de formulário.
    public ApiErrors(List<String> errors) {
        this.errors = errors; // Atribui a lista recebida ao campo da classe.
    }

    // Construtor alternativo que recebe apenas uma mensagem de erro.
    // Transforma essa única mensagem em uma lista com um único elemento.
    public ApiErrors(String message) {
        this.errors = Arrays.asList(message); // Cria uma lista contendo apenas a mensagem recebida.
    }
}
