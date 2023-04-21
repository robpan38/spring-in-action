package sia.tacocloud.postgres_app_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sia.tacocloud.postgres_app_data.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // selecteaza studenti filtrand dupa first name dat ca argument
    public List<Student> findByFirstName(String firstName);

    // selecteaza studenti ar caror first name contine argumentul
    public List<Student> findByFirstNameContaining(String firstNameSubstring);

    // selecteaza studenti cu last name not null
    public List<Student> findByLastNameNotNull();

    // selecteaza studenti filtrand dupa guardian name dat ca argument
    public List<Student> findByGuardianName(String guardianName);

    // selecteaza studentul filtrand dupa email SI first name date ca argument
    public Student findByEmailIdAndFirstName(String emailId, String firstName);

    // selecteaza studentul filtrand dupa email folosind @Query
    // @Query foloseste sintaxa JPQL care tine cont de implementarea obiectelor
    // nu de tabelele din db; ?1 se refera la primul argument al functiei
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmail(String email);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmail(String email);

    // selecteaza studentul filtrand dupa email folosind @Query
    // sintaxa va fi de SQL nativ pentru ca mentionam asta in anotare
    @Query(
            value = "SELECT * FROM tbl_student WHERE email_address = ?1",
            nativeQuery = true
    )
    public Student getStudentByEmailNative(String email);

    // folosim :nume_param si adnotare @Param("nume_param") pentru readability in query nativ
    @Query(
            value = "SELECT * FROM tbl_student WHERE email_address = :email",
            nativeQuery = true
    )
    public Student getStudentByEmailNativeNamedParam(@Param("email") String email);
}
