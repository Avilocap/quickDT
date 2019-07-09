    
    package services;

    import domain.Configuration;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.ConfigurationRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class ConfigurationService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private ConfigurationRepository        configurationRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public ConfigurationService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Configuration create(){
            Configuration result;
            result = new Configuration();
            return result;
        }
        
        public Collection<Configuration> findAll() {
            Collection<Configuration> result;

            result = configurationRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Configuration findOne(int configurationId) {
            Assert.isTrue(configurationId != 0);

            Configuration result;

            result = configurationRepository.findOne(configurationId);
            Assert.notNull(result);

            return result;
        }

        public Configuration save(Configuration configuration) {
            Assert.notNull(configuration);

            Configuration result;

            result = configurationRepository.save(configuration);

            return result;
        }

        public void delete(Configuration configuration) {
            Assert.notNull(configuration);
            Assert.isTrue(configuration.getId() != 0);
            Assert.isTrue(configurationRepository.exists(configuration.getId()));

            configurationRepository.delete(configuration);
        }

        // Other business methods -------------------------------------------------


    }

    