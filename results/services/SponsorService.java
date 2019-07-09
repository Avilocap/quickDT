    
    package services;

    import domain.Sponsor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.SponsorRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class SponsorService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private SponsorRepository        sponsorRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public SponsorService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Sponsor create(){
            Sponsor result;
            result = new Sponsor();
            return result;
        }
        
        public Collection<Sponsor> findAll() {
            Collection<Sponsor> result;

            result = sponsorRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Sponsor findOne(int sponsorId) {
            Assert.isTrue(sponsorId != 0);

            Sponsor result;

            result = sponsorRepository.findOne(sponsorId);
            Assert.notNull(result);

            return result;
        }

        public Sponsor save(Sponsor sponsor) {
            Assert.notNull(sponsor);

            Sponsor result;

            result = sponsorRepository.save(sponsor);

            return result;
        }

        public void delete(Sponsor sponsor) {
            Assert.notNull(sponsor);
            Assert.isTrue(sponsor.getId() != 0);
            Assert.isTrue(sponsorRepository.exists(sponsor.getId()));

            sponsorRepository.delete(sponsor);
        }

        // Other business methods -------------------------------------------------


    }

    