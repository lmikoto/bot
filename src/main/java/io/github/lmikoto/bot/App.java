package io.github.lmikoto.bot;

import io.github.lmikoto.jpa.query.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
