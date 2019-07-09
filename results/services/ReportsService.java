    
    package services;

    import domain.Reports;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.ReportsRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class ReportsService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private ReportsRepository        reportsRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public ReportsService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Reports create(){
            Reports result;
            result = new Reports();
            return result;
        }
        
        public Collection<Reports> findAll() {
            Collection<Reports> result;

            result = reportsRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Reports findOne(int reportsId) {
            Assert.isTrue(reportsId != 0);

            Reports result;

            result = reportsRepository.findOne(reportsId);
            Assert.notNull(result);

            return result;
        }

        public Reports save(Reports reports) {
            Assert.notNull(reports);

            Reports result;

            result = reportsRepository.save(reports);

            return result;
        }

        public void delete(Reports reports) {
            Assert.notNull(reports);
            Assert.isTrue(reports.getId() != 0);
            Assert.isTrue(reportsRepository.exists(reports.getId()));

            reportsRepository.delete(reports);
        }

        // Other business methods -------------------------------------------------


    }

    