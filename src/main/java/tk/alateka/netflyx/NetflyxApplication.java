package tk.alateka.netflyx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NetflyxApplication {

	private static Logger logConsole = LoggerFactory.getLogger(NetflyxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NetflyxApplication.class, args);
		logConsole.info("===> (Started server)");
	}

}
