package test;

import annotations.Bean;
import annotations.ComponentScan;
import annotations.Configuration;
import annotations.Scope;

@Configuration
@ComponentScan(packag = "test")
public class MyConfig {

    @Bean(scope = Scope.SINGLETON)
    public String applicationName() {
        return "myApp";
    }

}
