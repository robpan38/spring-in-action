package sia.tacocloud.data_model.repositories;

import sia.tacocloud.data_model.TacoOrder;

public interface OrderRepository {
    public Iterable<TacoOrder> findAll();
    public TacoOrder save(TacoOrder order);
}
