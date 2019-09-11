package JavaDB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ATMConfig {
    @Bean
    public Account account(){
        return new Account();
    }

    @Bean
    public ATM atm(){
        return new ATM(bank());
    }

    @Bean
    public ATMSimulator atmSimulator(){
        return new ATMSimulator(atm());
    }

    @Bean
    public Bank bank(){
        return new Bank(jdbcSQLiteConnection());
    }

    @Bean
    public ReadDataSource dataSource() {
        return new DataSource("customers.txt");
    }

    @Bean
    public ReadDataSource jdbcSQLiteConnection() {
        return new JdbcSQLiteConnection();
    }
}
