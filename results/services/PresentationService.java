    
    package services;

    import domain.Presentation;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.PresentationRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class PresentationService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private PresentationRepository        presentationRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public PresentationService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Presentation create(){
            Presentation result;
            result = new Presentation();
            return result;
        }
        
        public Collection<Presentation> findAll() {
            Collection<Presentation> result;

            result = presentationRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Presentation findOne(int presentationId) {
            Assert.isTrue(presentationId != 0);

            Presentation result;

            result = presentationRepository.findOne(presentationId);
            Assert.notNull(result);

            return result;
        }

        public Presentation save(Presentation presentation) {
            Assert.notNull(presentation);

            Presentation result;

            result = presentationRepository.save(presentation);

            return result;
        }

        public void delete(Presentation presentation) {
            Assert.notNull(presentation);
            Assert.isTrue(presentation.getId() != 0);
            Assert.isTrue(presentationRepository.exists(presentation.getId()));

            presentationRepository.delete(presentation);
        }

        // Other business methods -------------------------------------------------


    }

    