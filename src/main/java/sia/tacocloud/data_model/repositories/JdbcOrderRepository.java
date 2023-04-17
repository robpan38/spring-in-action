package sia.tacocloud.data_model.repositories;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloud.data_model.Ingredient;
import sia.tacocloud.data_model.Taco;
import sia.tacocloud.data_model.TacoOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO Taco_Order "
                        + "(delivery_name, delivery_street, delivery_city, "
                        + "delivery_state, delivery_zip, cc_number, "
                        + "cc_expiration, cc_cvv, placed_at) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt()
                        )
                );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTaco(orderId, taco);
        }

        return order;
    }

    private long saveTaco(long orderId, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO Taco "
                        + "(name, taco_order, created_at) "
                        + "VALUES (?, ?, ?)",
                        Types.VARCHAR, Types.BIGINT, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        orderId,
                        taco.getCreatedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);

        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;
    }

    public void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "INSERT INTO Ingredient_Ref (ingredient, taco) "
                    + "VALUES (?, ?)",
                    ingredient.getId(),
                    tacoId
            );
        }
    }

    // only use this in order to check if taco orders are saved correctly
    @Override
    public Iterable<TacoOrder> findAll() {
        return jdbcOperations.query("SELECT * FROM Taco_Order", new RowMapper<TacoOrder>() {
            @Override
            public TacoOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println(rs.getString("delivery_Name"));
                return new TacoOrder();
            }
        });
    }
}
