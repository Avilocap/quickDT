    
    package services;

    import domain.Tutorial;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.TutorialRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class TutorialService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private TutorialRepository        tutorialRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public TutorialService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Tutorial create(){
            Tutorial result;
            result = new Tutorial();
            return result;
        }
        
        public Collection<Tutorial> findAll() {
            Collection<Tutorial> result;

            result = tutorialRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Tutorial findOne(int tutorialId) {
            Assert.isTrue(tutorialId != 0);

            Tutorial result;

            result = tutorialRepository.findOne(tutorialId);
            Assert.notNull(result);

            return result;
        }

        public Tutorial save(Tutorial tutorial) {
            Assert.notNull(tutorial);

            Tutorial result;

            result = tutorialRepository.save(tutorial);

            return result;
        }

        public void delete(Tutorial tutorial) {
            Assert.notNull(tutorial);
            Assert.isTrue(tutorial.getId() != 0);
            Assert.isTrue(tutorialRepository.exists(tutorial.getId()));

            tutorialRepository.delete(tutorial);
        }

        // Other business methods -------------------------------------------------


    }

    