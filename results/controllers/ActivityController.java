    
    package controllers;


    import domain.Activity;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.ActivityService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/activity")
    public class ActivityController extends AbstractController {

        @Autowired
        private ActivityService    activityService;


        public ActivityController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Activity activity) {
            ModelAndView result;

            result = ActivityController.createEditModelAndView(activity, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Activity activity, final String message) {
            ModelAndView result;

            result = new ModelAndView("activity/edit");
            result.addObject("activity", activity);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Activity> activitys;
                activitys = this.activityService.findAll();
                result = new ModelAndView("activity/list");
                result.addObject("activity",activitys);
                result.addObject("requestURI", "activity/list.do");
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/create", method = RequestMethod.GET)
        public ModelAndView create() {

            ModelAndView result;
            try{
                final Activity activity = this.activityService.create();
                result = ActivityController.createEditModelAndView(activity);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int activityId) {
            ModelAndView result;
            Activity activity;
            try{
                activity = this.activityService.findOne(activityId);
                Assert.notNull(activity);
                result = ActivityController.createEditModelAndView(activity);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Activity activity, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = ActivityController.createEditModelAndView(activity);
            else
                try {
                    this.activityService.save(activity);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = ActivityController.createEditModelAndView(activity, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Activity activity) {
            ModelAndView result;
            try {
                this.activityService.delete(activity);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    