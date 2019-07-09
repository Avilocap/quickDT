    
    package repositories;

    import domain.Presentation;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface PresentationRepository extends JpaRepository<Presentation, Integer> {

    }

    
    