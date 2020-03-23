package com.demo.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class SpringBootTransactionApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringBootTransactionApplication.class);
    private final Environment env;
    public SpringBootTransactionApplication(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) throws UnknownHostException {


        SpringApplication app = new SpringApplication(SpringBootTransactionApplication.class);
        Environment env = app.run(args).getEnvironment();

        String protocol = "http";
        if (env.getProperty("server.ssl.key=store") != null) {
            protocol = "https";
        }
        log.info("\n==========================================================\n\t" +
                        "Application(应用名称) '[{}]' is running! Access URLs:\n\t" +
                        "Local(本地访问URL): \t\t{}://localhost:{}\n\t" +
                        "External(外网访问URL): \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n" +
                        "==========================================================",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }


}
