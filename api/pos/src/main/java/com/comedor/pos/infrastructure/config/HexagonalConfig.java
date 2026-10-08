package com.comedor.pos.infrastructure.config;

import com.comedor.pos.domain.service.CajaDomainService;
import com.comedor.pos.domain.service.FolioDomainService;
import com.comedor.pos.domain.service.InventarioDomainService;
import com.comedor.pos.domain.service.VentaDomainService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ImpresoraProperties.class)
public class HexagonalConfig {

    @Bean
    public CajaDomainService cajaDomainService() {
        return new CajaDomainService();
    }

    @Bean
    public VentaDomainService ventaDomainService() {
        return new VentaDomainService();
    }

    @Bean
    public InventarioDomainService inventarioDomainService() {
        return new InventarioDomainService();
    }

    @Bean
    public FolioDomainService folioDomainService() {
        return new FolioDomainService();
    }
}
