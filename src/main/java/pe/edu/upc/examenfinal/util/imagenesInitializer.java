package pe.edu.upc.examenfinal.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class imagenesInitializer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configurar el directorio de uploads como recurso estático
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
