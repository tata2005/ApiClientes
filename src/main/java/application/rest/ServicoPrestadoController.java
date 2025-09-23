package application.rest;

import application.model.entity.ServicoPrestado;
import application.rest.dto.ServicoPrestadoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicos-prestados")
public class ServicoPrestadoController {

    @PostMapping
    public ServicoPrestado salvar (@RequestBody ServicoPrestadoDTO dto) {

    }
}
