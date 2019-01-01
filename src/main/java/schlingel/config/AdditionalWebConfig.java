package schlingel.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
public class AdditionalWebConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")//.allowedHeaders("x-requested-with", "authorization", "Content-Type", "Authorization", "credential", "X-XSRF-TOKEN")
                .allowedMethods("*")//.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600)
                .allowedOrigins("*");//.allowedOrigins("http://localhost:4000");
    }



    /**
     * Allowing all origins, headers and methods here is only intended to keep this example simple.
     * This is not a default recommended configuration. Make adjustments as
     * necessary to your use case.
     *
     */
//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:4000");
//            }
//        };
//    }
}