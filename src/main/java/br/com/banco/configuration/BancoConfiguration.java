package br.com.banco.configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class BancoConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    @Bean
public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();

    // Configura o formato de serialização do timestamp
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    mapper.setDateFormat(dateFormat);

    module.addSerializer(Float.class, new JsonSerializer<Float>() {
        @Override
        public void serialize(Float value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            BigDecimal bd = new BigDecimal(Float.toString(value));
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            gen.writeNumber(bd);
        }
    });
    mapper.registerModule(module);
    return mapper;
}


}