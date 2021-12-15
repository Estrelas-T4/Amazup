package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.livro.exceptions.LivroJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro salvarLivro(Livro livro) {
        if (livroRepository.existsByNomeAndAutorId(livro.getNome(), livro.getAutor().getId())) {
            throw new LivroJaCadastradoException();
        }
        Livro livroObjeto = livroRepository.save(livro);

        return livroObjeto;
    }

    public Livro buscarLivroPorId(int id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        if (livroOptional.isEmpty()) {
            throw new RuntimeException();
        }

        return livroOptional.get();
    }
}
