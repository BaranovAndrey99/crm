package crm.repository;

import crm.dao.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeekerRepository extends JpaRepository<Seeker, Long>{
    List<Seeker> findAll();
    List<Seeker> findSeekerBySurnameAndFirstnameAndFathername(String surname, String firstname, String fathername);
    Seeker findSeekerById(long id);
    Seeker findSeekerByMail(String mail);
}
