package sia.tacocloud.postgres_app_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.postgres_app_data.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
