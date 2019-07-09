package br.com.puc.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MonitoramentoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoramentoServiceApplication.class, args);
	}

}
