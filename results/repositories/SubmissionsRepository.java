    
    package repositories;

    import domain.Submissions;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface SubmissionsRepository extends JpaRepository<Submissions, Integer> {

    }

    
    