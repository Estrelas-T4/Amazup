package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.dtos.AutorUriDTO;
import br.com.zup.Amazup.componentes.UriContrutor;
import br.com.zup.Amazup.livro.dtos.LivroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private UriContrutor contrutor;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, URI> cadastrarLivro(@RequestBody Livro livro){
        Livro livroObjeto = livroService.salvarLivro(livro);

        URI enderecoVitrini = contrutor.criarUri("/livros", livro.getId());

        HashMap<String, URI> resposta = new HashMap<>();
        resposta.put("vitrine", enderecoVitrini);
        return resposta;
    }

    @GetMapping("/{id}")
    public LivroDTO exibirLivro(@PathVariable int id){
        Livro livro = livroService.buscarLivroPorId(id);
        ModelMapper modelMapper = new ModelMapper();

        LivroDTO livroDTO = modelMapper.map(livro, LivroDTO.class);
        livroDTO.getAutor().setUri(contrutor.criarUri("/livros",""+livro.getAutor().getId()));
        return livroDTO;
    }
}
