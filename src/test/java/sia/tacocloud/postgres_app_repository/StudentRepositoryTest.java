package sia.tacocloud.postgres_app_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sia.tacocloud.postgres_app_data.Guardian;
import sia.tacocloud.postgres_app_data.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    // injectam repository-ul de student in clasa de testare
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("r.pandele@yahoo.com")
                .firstName("Robert")
                .lastName("Pandele")
//                .guardianName("Matei Popovici")
//                .guardianEmail("ppcarte@gmail.com")
//                .guardianMobile("123456789")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Matei Popovici")
                .email("ppcarte@gmail.com")
                .mobile("123456789")
                .build();

        Student student = Student.builder()
                .firstName("Robert")
                .emailId("r.pandele2@yahoo.com")
                .lastName("Pandele")
                .guardian(guardian)
                .build();
        
        studentRepository.save(student);
    }
    
    @Test
    public void printStudentsWithFirstName() {
        List<Student> students = studentRepository.findByFirstName("Robert");
        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentsWithFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("Rob");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentsWithGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Matei Popovici");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentWithEmailAndFirstName() {
        Student student = studentRepository.findByEmailIdAndFirstName("r.pandele@yahoo.com", "Robert");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentWithEmailWithQueryAnnotation() {
        Student student = studentRepository.getStudentByEmail("r.pandele2@yahoo.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameWithEmailWithQueryAnnotation() {
        String studentFirstName = studentRepository.getStudentFirstNameByEmail("r.pandele2@yahoo.com");
        System.out.println("studentFirstName = " + studentFirstName);
    }

    @Test
    public void printStudentWithEmailWithQueryAnnotationNative() {
        Student student = studentRepository.getStudentByEmailNative("r.pandele2@yahoo.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentWithEmailWithQueryAnnotationNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailNativeNamedParam("r.pandele2@yahoo.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println("students = " + students);
    }
}