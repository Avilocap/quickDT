    
    package repositories;

    import domain.Reports;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface ReportsRepository extends JpaRepository<Reports, Integer> {

    }

    
    