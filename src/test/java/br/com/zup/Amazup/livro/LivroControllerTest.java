package br.com.zup.Amazup.livro;

import br.com.zup.Amazup.autor.Autor;
import br.com.zup.Amazup.componentes.Conversor;
import br.com.zup.Amazup.componentes.UriContrutor;
import br.com.zup.Amazup.enums.Genero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@WebMvcTest({LivroController.class, UriContrutor.class, Conversor.class})
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
        livro.setId(100000);

        autor = new Autor();
        autor.setId(1);
        autor.setNome("Douglas Adams");

        livro.setAutor(autor);
    }

    private ResultActions relizarRequisicao(Object object, int statusEsperado, String httpVerbo) throws Exception {
        String json = objectMapper.writeValueAsString(object);
        URI uri = UriComponentsBuilder.fromPath("/livros").build().toUri();

        return mockMvc.perform(MockMvcRequestBuilders.request(httpVerbo, uri)
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(statusEsperado));
    }

    @Test
    public void testarCadastroDeLivro() throws Exception{
        Mockito.when(livroService.salvarLivro(Mockito.any(Livro.class))).thenReturn(livro);
        String json = objectMapper.writeValueAsString(livro);

        ResultActions resultadoDaRequisicao = relizarRequisicao(livro, 201, "POST");
        resultadoDaRequisicao.andExpect(
                MockMvcResultMatchers.jsonPath("$.vitrine")
                        .value("http://localhost:8080/livros/"+livro.getId())
        );

    }

    @Test
    public void testarRotaDeCadastroDeLivroComValidacaoDeNomeEmBranco() throws Exception{
        Mockito.when(livroService.salvarLivro(Mockito.any(Livro.class))).thenReturn(livro);
        livro.setNome("       ");

        ResultActions resultado = relizarRequisicao(livro, 422,"POST");

        Mockito.verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarRotaDeCadastroDeLivroComValidacaoAutorNullo() throws Exception{
        Mockito.when(livroService.salvarLivro(Mockito.any(Livro.class))).thenReturn(livro);
        livro.setAutor(null);

        ResultActions resultado = relizarRequisicao(livro, 422, "POST");

        Mockito.verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    @Test
    public void testarRotaDeCadastroDeLivroComValidacaoGeneroNullo() throws Exception{
        Mockito.when(livroService.salvarLivro(Mockito.any(Livro.class))).thenReturn(livro);
        livro.setGenero(null);

        ResultActions resultado = relizarRequisicao(livro, 422, "POST");

        Mockito.verify(livroService, Mockito.times(0)).salvarLivro(Mockito.any(Livro.class));

    }

    //Validações: Autor Not Null, Preço Not Null, Nome do Livro Not Null Not blank,
    // Limitar casas decimais no preço, preço não pode menor que 0, genero Not NULL, genero Valido
}
