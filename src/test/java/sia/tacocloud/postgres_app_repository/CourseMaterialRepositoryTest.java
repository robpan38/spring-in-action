package sia.tacocloud.postgres_app_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sia.tacocloud.postgres_app_data.Course;
import sia.tacocloud.postgres_app_data.CourseMaterial;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterialWithNewCourse() {
        Course course =
                Course.builder()
                        .title("Data Structures and Algorithms")
                        .credit(69)
                        .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }
}