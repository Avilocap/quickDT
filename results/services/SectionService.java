    
    package services;

    import domain.Section;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.SectionRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class SectionService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private SectionRepository        sectionRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public SectionService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Section create(){
            Section result;
            result = new Section();
            return result;
        }
        
        public Collection<Section> findAll() {
            Collection<Section> result;

            result = sectionRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Section findOne(int sectionId) {
            Assert.isTrue(sectionId != 0);

            Section result;

            result = sectionRepository.findOne(sectionId);
            Assert.notNull(result);

            return result;
        }

        public Section save(Section section) {
            Assert.notNull(section);

            Section result;

            result = sectionRepository.save(section);

            return result;
        }

        public void delete(Section section) {
            Assert.notNull(section);
            Assert.isTrue(section.getId() != 0);
            Assert.isTrue(sectionRepository.exists(section.getId()));

            sectionRepository.delete(section);
        }

        // Other business methods -------------------------------------------------


    }

    