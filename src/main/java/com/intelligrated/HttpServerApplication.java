package com.intelligrated;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class HttpServerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HttpServerApplication.class, args);
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/http-outbound-config.xml");
		
		RequestGateway gateway = context.getBean("requestGateway", RequestGateway.class);
		String response = gateway.echo("hello");
		System.out.println("reply from HTTP: " + response);
		
		context.close();
	}
}
