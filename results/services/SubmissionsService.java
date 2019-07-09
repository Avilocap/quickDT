    
    package services;

    import domain.Submissions;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.SubmissionsRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class SubmissionsService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private SubmissionsRepository        submissionsRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public SubmissionsService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Submissions create(){
            Submissions result;
            result = new Submissions();
            return result;
        }
        
        public Collection<Submissions> findAll() {
            Collection<Submissions> result;

            result = submissionsRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Submissions findOne(int submissionsId) {
            Assert.isTrue(submissionsId != 0);

            Submissions result;

            result = submissionsRepository.findOne(submissionsId);
            Assert.notNull(result);

            return result;
        }

        public Submissions save(Submissions submissions) {
            Assert.notNull(submissions);

            Submissions result;

            result = submissionsRepository.save(submissions);

            return result;
        }

        public void delete(Submissions submissions) {
            Assert.notNull(submissions);
            Assert.isTrue(submissions.getId() != 0);
            Assert.isTrue(submissionsRepository.exists(submissions.getId()));

            submissionsRepository.delete(submissions);
        }

        // Other business methods -------------------------------------------------


    }

    