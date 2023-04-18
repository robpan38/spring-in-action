package sia.tacocloud.data_model.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.data_model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> { }
