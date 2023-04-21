package sia.tacocloud.postgres_app_data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Guard;

// entitate avand tabel in db
@Entity
// elimina boilder plate code generand automat getters, setters
@Data
// creeaza constructor cu toate argumentele
@AllArgsConstructor
// creaza constructor fara argumente
@NoArgsConstructor
// builder pattern ca sa creezi lizibil obiecte
@Builder
// suprascrie numele tabelului din db
// seteaza constrangeri de tip unique pe atributul emailId
@Table(
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
            name = "emailid_unique",
            columnNames = "email_address"
        )
)
public class Student {
    // marcheaza studentId ca fiind primary key
    // + desemneaza modalitate prin care studentId este creat prin autoincrementare
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;

    // redenumeste un atribut al bazei de date
    @Column(
            name = "email_address",
            nullable = false
    )
    private String emailId;

    // obiectul tip Guardian va fi prezent in tabelul entitatii Student
    // insa modelarea lui obiect apare in alta clasa
    @Embedded
    private Guardian guardian;
}
