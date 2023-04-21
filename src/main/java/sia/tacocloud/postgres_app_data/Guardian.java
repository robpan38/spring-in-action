package sia.tacocloud.postgres_app_data;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// embeddable folosit atunci cand vrem ca
// datele unei entitati sa fie in tabelul altei entitati (informatii guardian in informatii student)
// dar nu vrem ca ele sa fie in aceeasi clasa
@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// aici se mapeaza numele campurilor clasa guardian
// la numele atributelor din tabelul Student care include informatii Guardian
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
})
public class Guardian {
    private String name;
    private String email;
    private String mobile;
}
