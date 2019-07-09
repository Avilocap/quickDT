    
    package services;

    import domain.Reviewer;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.ReviewerRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class ReviewerService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private ReviewerRepository        reviewerRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public ReviewerService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Reviewer create(){
            Reviewer result;
            result = new Reviewer();
            return result;
        }
        
        public Collection<Reviewer> findAll() {
            Collection<Reviewer> result;

            result = reviewerRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Reviewer findOne(int reviewerId) {
            Assert.isTrue(reviewerId != 0);

            Reviewer result;

            result = reviewerRepository.findOne(reviewerId);
            Assert.notNull(result);

            return result;
        }

        public Reviewer save(Reviewer reviewer) {
            Assert.notNull(reviewer);

            Reviewer result;

            result = reviewerRepository.save(reviewer);

            return result;
        }

        public void delete(Reviewer reviewer) {
            Assert.notNull(reviewer);
            Assert.isTrue(reviewer.getId() != 0);
            Assert.isTrue(reviewerRepository.exists(reviewer.getId()));

            reviewerRepository.delete(reviewer);
        }

        // Other business methods -------------------------------------------------


    }

    