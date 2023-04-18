package sia.tacocloud.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import sia.tacocloud.data_model.Ingredient;
import sia.tacocloud.data_model.repositories.IngredientRepository;
import sia.tacocloud.data_model.repositories.OrderRepository;

@Configuration
public class LoadDatabase implements CommandLineRunner {
    private final IngredientRepository ingredientRepository;
    private final OrderRepository orderRepository;

    public LoadDatabase(IngredientRepository ingredientRepository, OrderRepository orderRepository) {
        this.ingredientRepository = ingredientRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        ingredientRepository.deleteAll();
        orderRepository.deleteAll();

        ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }
}
