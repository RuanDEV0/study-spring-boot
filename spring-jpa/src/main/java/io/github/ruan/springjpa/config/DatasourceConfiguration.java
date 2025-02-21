package io.github.ruan.springjpa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;

    /**
     * Operação de datasource mais simples, e não é recomendada pela equipe spring!
     * @return
     */

   @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        return dataSource;
    }

    /**
     * Operação mais recomendada, e é utilizado pelo spring boot, além
     * de ser o DataSource default.
     * @return
     */
    @Bean
    public DataSource hikariConfigDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);

        hikariConfig.setMaximumPoolSize(10); //Máximo de conexões com banco de dados, configuração para peformance.
        hikariConfig.setMinimumIdle(1); // Mínimo de conexões com BD.
        hikariConfig.setPoolName("library-poll");
        hikariConfig.setConnectionTimeout(10000); // máximo de tempo para conexão.
        hikariConfig.setConnectionTestQuery("SELECT 1"); // testar funcionamento do DB.

        /**
         * Para configuração personalizada de performance e configuração, utilizamos essa classe Bean.
         */

        return new HikariDataSource(hikariConfig);
    }
}
