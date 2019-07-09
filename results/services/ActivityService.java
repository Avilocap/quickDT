    
    package services;

    import domain.Activity;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.ActivityRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class ActivityService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private ActivityRepository        activityRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public ActivityService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Activity create(){
            Activity result;
            result = new Activity();
            return result;
        }
        
        public Collection<Activity> findAll() {
            Collection<Activity> result;

            result = activityRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Activity findOne(int activityId) {
            Assert.isTrue(activityId != 0);

            Activity result;

            result = activityRepository.findOne(activityId);
            Assert.notNull(result);

            return result;
        }

        public Activity save(Activity activity) {
            Assert.notNull(activity);

            Activity result;

            result = activityRepository.save(activity);

            return result;
        }

        public void delete(Activity activity) {
            Assert.notNull(activity);
            Assert.isTrue(activity.getId() != 0);
            Assert.isTrue(activityRepository.exists(activity.getId()));

            activityRepository.delete(activity);
        }

        // Other business methods -------------------------------------------------


    }

    