package sia.tacocloud.postgres_app_data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long id;
    private String url;

    // marcheaza faptul ca un material de curs nu poate
    // exista fara un curs
    @OneToOne
    @JoinColumn(
            name = "course_id", // numele coloanei din baza de date (e tot id)
            referencedColumnName = "courseId" // numele campului din clasa Course
    )
    private Course course;
}
