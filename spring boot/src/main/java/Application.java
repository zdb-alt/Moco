import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by XULE on 2018/11/13.
 */
@SpringBootApplication
@ComponentScan("com")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }
}
