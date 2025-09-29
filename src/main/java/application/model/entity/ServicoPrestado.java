package application.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoPrestado {

    // Define o campo 'id' como chave primária da tabela.
    // O valor será gerado automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Campo obrigatório que descreve o serviço.
    // O texto pode ter até 150 caracteres.
    @Column(nullable = false, length = 150)
    private String descricao;

    // Relacionamento muitos-para-um com a entidade Cliente.
    // Cada serviço está associado a um único cliente.
    // A coluna 'id_cliente' será usada como chave estrangeira no banco.
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    // Campo que representa o valor do serviço.
    // BigDecimal é usado para representar valores monetários com precisão.
    @Column
    private BigDecimal valor;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
}

