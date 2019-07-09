    
    package services;

    import domain.Registration;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.RegistrationRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class RegistrationService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private RegistrationRepository        registrationRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public RegistrationService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Registration create(){
            Registration result;
            result = new Registration();
            return result;
        }
        
        public Collection<Registration> findAll() {
            Collection<Registration> result;

            result = registrationRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Registration findOne(int registrationId) {
            Assert.isTrue(registrationId != 0);

            Registration result;

            result = registrationRepository.findOne(registrationId);
            Assert.notNull(result);

            return result;
        }

        public Registration save(Registration registration) {
            Assert.notNull(registration);

            Registration result;

            result = registrationRepository.save(registration);

            return result;
        }

        public void delete(Registration registration) {
            Assert.notNull(registration);
            Assert.isTrue(registration.getId() != 0);
            Assert.isTrue(registrationRepository.exists(registration.getId()));

            registrationRepository.delete(registration);
        }

        // Other business methods -------------------------------------------------


    }

    