package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.model.Recruiter;

public interface IRecruiterRepository extends JpaRepository<Recruiter, Long> {
}
