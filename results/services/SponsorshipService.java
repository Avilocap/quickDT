    
    package services;

    import domain.Sponsorship;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.SponsorshipRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class SponsorshipService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private SponsorshipRepository        sponsorshipRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public SponsorshipService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Sponsorship create(){
            Sponsorship result;
            result = new Sponsorship();
            return result;
        }
        
        public Collection<Sponsorship> findAll() {
            Collection<Sponsorship> result;

            result = sponsorshipRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Sponsorship findOne(int sponsorshipId) {
            Assert.isTrue(sponsorshipId != 0);

            Sponsorship result;

            result = sponsorshipRepository.findOne(sponsorshipId);
            Assert.notNull(result);

            return result;
        }

        public Sponsorship save(Sponsorship sponsorship) {
            Assert.notNull(sponsorship);

            Sponsorship result;

            result = sponsorshipRepository.save(sponsorship);

            return result;
        }

        public void delete(Sponsorship sponsorship) {
            Assert.notNull(sponsorship);
            Assert.isTrue(sponsorship.getId() != 0);
            Assert.isTrue(sponsorshipRepository.exists(sponsorship.getId()));

            sponsorshipRepository.delete(sponsorship);
        }

        // Other business methods -------------------------------------------------


    }

    