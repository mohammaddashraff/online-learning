package com.example.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.admin.Service.adminService;

@Configuration
public class AppConfig {
    
    @Bean
    public adminService adminService() {
        return new adminService();
    }
}
