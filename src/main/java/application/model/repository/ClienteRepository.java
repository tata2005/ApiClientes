package application.model.repository;

import application.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que representa o repositório da entidade Cliente.
// Ela permite realizar operações no banco de dados sem precisar escrever SQL manualmente.
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Ao estender JpaRepository, essa interface herda vários métodos prontos:
    // - save() para salvar ou atualizar um cliente
    // - findById() para buscar por ID
    // - findAll() para listar todos os clientes
    // - delete() para remover um cliente
    // O tipo <Cliente, Integer> indica que:
    // - Cliente é a entidade que será manipulada
    // - Integer é o tipo da chave primária (id)
}

