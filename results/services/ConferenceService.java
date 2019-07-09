    
    package services;

    import domain.Conference;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.ConferenceRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class ConferenceService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private ConferenceRepository        conferenceRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public ConferenceService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Conference create(){
            Conference result;
            result = new Conference();
            return result;
        }
        
        public Collection<Conference> findAll() {
            Collection<Conference> result;

            result = conferenceRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Conference findOne(int conferenceId) {
            Assert.isTrue(conferenceId != 0);

            Conference result;

            result = conferenceRepository.findOne(conferenceId);
            Assert.notNull(result);

            return result;
        }

        public Conference save(Conference conference) {
            Assert.notNull(conference);

            Conference result;

            result = conferenceRepository.save(conference);

            return result;
        }

        public void delete(Conference conference) {
            Assert.notNull(conference);
            Assert.isTrue(conference.getId() != 0);
            Assert.isTrue(conferenceRepository.exists(conference.getId()));

            conferenceRepository.delete(conference);
        }

        // Other business methods -------------------------------------------------


    }

    