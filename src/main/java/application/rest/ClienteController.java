package application.rest;

import application.model.entity.Cliente;
import application.model.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    // Declara o repositório que será usado para acessar os dados dos clientes no banco.
    private final ClienteRepository repository;

    // Construtor com injeção de dependência (@Autowired).
    // O Spring injeta automaticamente uma instância de ClienteRepository aqui.
    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    // Endpoint para salvar um novo cliente.
    // @PostMapping indica que esse método responde a requisições HTTP POST.
    // @ResponseStatus define que, ao salvar com sucesso, será retornado o status 201 (Created).
    // @RequestBody indica que os dados do cliente virão no corpo da requisição.
    // @Valid ativa a validação automática dos campos do objeto Cliente.
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return repository.save(cliente); // Salva o cliente no banco e retorna o objeto salvo.
    }

    // Endpoint para buscar um cliente pelo ID.
    // @GetMapping com "{id}" indica que o ID será passado na URL.
    // @PathVariable extrai esse ID da URL.
    public Cliente acharporId(@PathVariable Integer id){
        return repository
                .findById(id) // Tenta encontrar o cliente pelo ID.
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        // Se não encontrar, lança uma exceção com status 404 e mensagem personalizada.
    }

    // Endpoint para deletar um cliente pelo ID.
    // @DeleteMapping indica que esse método responde a requisições HTTP DELETE.
    // @ResponseStatus define que, ao deletar com sucesso, será retornado o status 204 (No Content).
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id) // Tenta encontrar o cliente.
                .map(cliente -> {
                    repository.delete(cliente); // Se encontrar, deleta o cliente.
                    return Void.TYPE; // Retorna um tipo vazio apenas para finalizar o bloco.
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        // Se não encontrar, lança exceção 404.
    }

    // Endpoint para atualizar os dados de um cliente existente.
    // @PutMapping indica que esse método responde a requisições HTTP PUT.
    // @ResponseStatus define que, ao atualizar com sucesso, será retornado o status 204 (No Content).
    // @RequestBody recebe os dados atualizados do cliente.
    // @Valid ativa a validação dos campos.
    public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
        repository
                .findById(id) // Tenta encontrar o cliente original.
                .map(cliente -> {
                    // Atualiza os campos do cliente original com os dados recebidos.
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setCpf(clienteAtualizado.getCpf());
                    return repository.save(clienteAtualizado); // Salva as alterações no banco.
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        // Se não encontrar, lança exceção 404.
    }
}

