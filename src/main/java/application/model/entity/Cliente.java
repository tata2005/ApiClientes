package application.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    // Define o campo 'id' como chave primária da tabela.
    // A anotação @GeneratedValue indica que o valor será gerado automaticamente pelo banco.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Campo 'nome' obrigatório, com no máximo 150 caracteres.
    // @NotEmpty garante que o nome não seja nulo nem vazio.
    // A mensagem de erro será buscada no arquivo messages.properties.
    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    // Campo 'cpf' obrigatório, com no máximo 11 caracteres.
    // @NotNull garante que o valor não seja nulo.
    // @CPF valida se o valor é um CPF válido.
    // As mensagens de erro também são personalizadas via arquivo de mensagens.
    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    // Campo que armazena a data de cadastro do cliente.
    // updatable = false impede que esse campo seja alterado após o cadastro.
    // @JsonFormat define o formato da data ao serializar para JSON.
    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    // Método executado automaticamente antes de salvar o cliente no banco.
    // Define a data de cadastro como a data atual.
    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }
}

