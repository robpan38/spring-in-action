package sia.tacocloud.data_model.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.data_model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> { }
