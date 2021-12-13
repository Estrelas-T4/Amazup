package br.com.zup.Amazup.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro salvarLivro(Livro livro){
       Livro livroObjeto = livroRepository.save(livro);
       return livroObjeto;
    }
}
