    
    package services;

    import domain.Paper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.PaperRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class PaperService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private PaperRepository        paperRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public PaperService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Paper create(){
            Paper result;
            result = new Paper();
            return result;
        }
        
        public Collection<Paper> findAll() {
            Collection<Paper> result;

            result = paperRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Paper findOne(int paperId) {
            Assert.isTrue(paperId != 0);

            Paper result;

            result = paperRepository.findOne(paperId);
            Assert.notNull(result);

            return result;
        }

        public Paper save(Paper paper) {
            Assert.notNull(paper);

            Paper result;

            result = paperRepository.save(paper);

            return result;
        }

        public void delete(Paper paper) {
            Assert.notNull(paper);
            Assert.isTrue(paper.getId() != 0);
            Assert.isTrue(paperRepository.exists(paper.getId()));

            paperRepository.delete(paper);
        }

        // Other business methods -------------------------------------------------


    }

    