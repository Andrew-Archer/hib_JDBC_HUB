package configuration;

import com.ebsco.ordermanagement.shared.hub.driver.jdbc.HubDriver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JdbcHubConfig {

    @Bean
    public RestTemplate hubRestTemplate() {
        var restTemplate = new RestTemplate();

        // Adding ClientHttpRequestInterceptor to interceptors list for authentication
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer ... ");
            return execution.execute(request, body);
        });

        // Adding HttpComponentsClientHttpRequestFactory to support PATCH requests
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return restTemplate;
    }

    @Bean
    public DataSource dataSource() {
        var properties = new Properties();
        properties.put("connection.scope", "query:order-subscription-history");
        var hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(HubDriver.class.getCanonicalName());
        hikariConfig.setDataSourceProperties(properties);
        hikariConfig.setJdbcUrl("JUST_A_STUB");

        return new HikariDataSource(hikariConfig);
    }
}
