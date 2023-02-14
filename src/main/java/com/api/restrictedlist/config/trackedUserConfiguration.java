package com.api.restrictedlist.config;

import com.api.restrictedlist.constants.AllConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class trackedUserConfiguration {

    @Bean
    public AllConstants allConstants(){
        return new AllConstants();
    }

}
