    
    package services;

    import domain.Panel;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.PanelRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class PanelService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private PanelRepository        panelRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public PanelService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Panel create(){
            Panel result;
            result = new Panel();
            return result;
        }
        
        public Collection<Panel> findAll() {
            Collection<Panel> result;

            result = panelRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Panel findOne(int panelId) {
            Assert.isTrue(panelId != 0);

            Panel result;

            result = panelRepository.findOne(panelId);
            Assert.notNull(result);

            return result;
        }

        public Panel save(Panel panel) {
            Assert.notNull(panel);

            Panel result;

            result = panelRepository.save(panel);

            return result;
        }

        public void delete(Panel panel) {
            Assert.notNull(panel);
            Assert.isTrue(panel.getId() != 0);
            Assert.isTrue(panelRepository.exists(panel.getId()));

            panelRepository.delete(panel);
        }

        // Other business methods -------------------------------------------------


    }

    