package nogrend.jobs.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class QuartzConfiguration {

    @Value("${spring.quartz.properties.org.quartz.dataSource.quartzDataSource.driver}")
    private String driver;
    @Value("${spring.quartz.properties.org.quartz.dataSource.quartzDataSource.URL}")
    private String url;
    @Value("${spring.quartz.properties.org.quartz.dataSource.quartzDataSource.user}")
    private String username;
    @Value("${spring.quartz.properties.org.quartz.dataSource.quartzDataSource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
