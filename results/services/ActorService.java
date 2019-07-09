    
    package services;

    import domain.Actor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.ActorRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class ActorService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private ActorRepository        actorRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public ActorService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Actor create(){
            Actor result;
            result = new Actor();
            return result;
        }
        
        public Collection<Actor> findAll() {
            Collection<Actor> result;

            result = actorRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Actor findOne(int actorId) {
            Assert.isTrue(actorId != 0);

            Actor result;

            result = actorRepository.findOne(actorId);
            Assert.notNull(result);

            return result;
        }

        public Actor save(Actor actor) {
            Assert.notNull(actor);

            Actor result;

            result = actorRepository.save(actor);

            return result;
        }

        public void delete(Actor actor) {
            Assert.notNull(actor);
            Assert.isTrue(actor.getId() != 0);
            Assert.isTrue(actorRepository.exists(actor.getId()));

            actorRepository.delete(actor);
        }

        // Other business methods -------------------------------------------------


    }

    