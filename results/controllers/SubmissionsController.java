    
    package controllers;


    import domain.Submissions;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.SubmissionsService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/submissions")
    public class SubmissionsController extends AbstractController {

        @Autowired
        private SubmissionsService    submissionsService;


        public SubmissionsController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Submissions submissions) {
            ModelAndView result;

            result = SubmissionsController.createEditModelAndView(submissions, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Submissions submissions, final String message) {
            ModelAndView result;

            result = new ModelAndView("submissions/edit");
            result.addObject("submissions", submissions);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Submissions> submissionss;
                submissionss = this.submissionsService.findAll();
                result = new ModelAndView("submissions/list");
                result.addObject("submissions",submissionss);
                result.addObject("requestURI", "submissions/list.do");
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
                final Submissions submissions = this.submissionsService.create();
                result = SubmissionsController.createEditModelAndView(submissions);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int submissionsId) {
            ModelAndView result;
            Submissions submissions;
            try{
                submissions = this.submissionsService.findOne(submissionsId);
                Assert.notNull(submissions);
                result = SubmissionsController.createEditModelAndView(submissions);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Submissions submissions, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = SubmissionsController.createEditModelAndView(submissions);
            else
                try {
                    this.submissionsService.save(submissions);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = SubmissionsController.createEditModelAndView(submissions, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Submissions submissions) {
            ModelAndView result;
            try {
                this.submissionsService.delete(submissions);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    