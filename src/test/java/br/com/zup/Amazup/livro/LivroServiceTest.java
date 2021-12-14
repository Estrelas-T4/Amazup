package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.enums.Genero;
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

        Livro livroObjeto = livroService.salvarLivro(livro);

        Mockito.verify(livroRepository, Mockito.times(1)).save(Mockito.any(Livro.class));
    }

    //Validações: Não Cadastrar o mesmo Livro para o mesmo autor 2 vezes
}
