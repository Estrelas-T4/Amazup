package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.enums.Genero;
import br.com.zup.Amazup.livro.exceptions.LivroJaCadastradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class LivroServiceTest {
    @MockBean
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    private Livro livro;
    private Autor autor;

    @BeforeEach
    private void setup(){
        livro = new Livro();
        livro.setGenero(Genero.FICCAO_CIENTIFICA);
        livro.setNome("Adeus e Obrigado Pelos Peixes");
        livro.setId(1);

        autor = new Autor();
        autor.setId(1);
        autor.setNome("Douglas Adams");

        livro.setAutor(autor);
    }

    @Test
    public void testarCadastrodeLivro(){
        Mockito.when(livroRepository.save(Mockito.any(Livro.class))).thenReturn(livro);
        Mockito.when(livroRepository.existsByNomeAndAutorId(Mockito.anyString(), Mockito.anyInt())).thenReturn(false);

        Livro livroObjeto = livroService.salvarLivro(livro);

        Mockito.verify(livroRepository, Mockito.times(1)).save(Mockito.any(Livro.class));
    }

    @Test
    public void testarCadastrarLivroRepetido(){
        Mockito.when(livroRepository.save(Mockito.any(Livro.class))).thenReturn(livro);
        Mockito.when(livroRepository.existsByNomeAndAutorId(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);

        RuntimeException exception = Assertions.assertThrows(LivroJaCadastradoException.class, ()-> {
            livroService.salvarLivro(livro);
        });

        Mockito.verify(livroRepository, Mockito.times(0)).save(Mockito.any(Livro.class));
    }

}
