package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.enums.Genero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {
    @MockBean
    private LivroService livroService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private Livro livro;
    private Autor autor;

    @BeforeEach
    private void setup(){
        objectMapper = new ObjectMapper();

        livro = new Livro();
        livro.setGenero(Genero.FICCAO_CIENTIFICA);
        livro.setNome("Adeus e Obrigado Pelos Peixes");
        livro.setId(1);

        autor = new Autor();
        autor.setId(1);
        autor.setNome("Douglas Adams");

        livro.setAutor(autor);
    }

}
