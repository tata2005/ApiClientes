package application.model.repository;

import application.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que representa o repositório da entidade Servico.
// Ela herda os métodos prontos da JpaRepository para facilitar o acesso ao banco de dados.
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    // Ao estender JpaRepository, essa interface já possui métodos como:
    // - save() → para salvar ou atualizar um serviço
    // - findById() → para buscar um serviço pelo ID
    // - findAll() → para listar todos os serviços
    // - delete() → para remover um serviço
    //
    // O tipo <Servico, Integer> indica:
    // - Servico é a entidade que será manipulada
    // - Integer é o tipo da chave primária (id)
}

