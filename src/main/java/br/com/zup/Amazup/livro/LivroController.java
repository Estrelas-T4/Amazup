package br.com.zup.Amazup.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> cadastrarLivro(@RequestBody Livro livro){
        Livro livroObjeto = livroService.salvarLivro(livro);
        HashMap<String, String> resposta = new HashMap<>();
        resposta.put("vitrine", "http://localhost:8080/livros/"+livroObjeto.getId());
        return resposta;
    }
}
