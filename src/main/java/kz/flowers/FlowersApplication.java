package kz.flowers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowersApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowersApplication.class);
    }
}

// tables:
// Flowers (id, name, size, price, category_id)
// Category (id, name)
// Tags (id, flower_id, tag_name)