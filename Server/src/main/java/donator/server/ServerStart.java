package donator.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerStart {
    ApplicationContext factory= new ClassPathXmlApplicationContext("classpath:spring-server.xml");

}
