package org.maxkizi.userdemo.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandardObjectMapperConfiguration {

    @Bean(name = "standardModelMapper")
    public ModelMapper defaultObjectMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        return mapper;
    }
}
