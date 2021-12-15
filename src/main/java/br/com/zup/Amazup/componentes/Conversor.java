package br.com.zup.Amazup.componentes;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

@Component
public class Conversor {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
