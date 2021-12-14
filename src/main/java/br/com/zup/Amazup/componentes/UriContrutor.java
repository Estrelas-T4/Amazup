package br.com.zup.Amazup.componentes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class UriContrutor {

    @Value("${server.port}") // Preenche o atributo com o valor no arquivo application.properties
    private String porta;
    @Value("${host}")// Preenche o atributo com o valor no arquivo application.properties
    private String host;

    public URI criarUri(String path, String recurso){
        return UriComponentsBuilder.newInstance().scheme("http").host(host)
                .port(porta).path(path+"/{recurso}").buildAndExpand(recurso).toUri();
    }

    public URI criarUri(String path, int recurso){
        return criarUri(path, ""+recurso);
    }

    public URI criarUri(String path){
        return UriComponentsBuilder.newInstance().scheme("http").host(host)
                .port(porta).path(path).build().toUri();
    }
}
