    
    package controllers;


    import domain.Reviewer;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.ReviewerService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/reviewer")
    public class ReviewerController extends AbstractController {

        @Autowired
        private ReviewerService    reviewerService;


        public ReviewerController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Reviewer reviewer) {
            ModelAndView result;

            result = ReviewerController.createEditModelAndView(reviewer, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Reviewer reviewer, final String message) {
            ModelAndView result;

            result = new ModelAndView("reviewer/edit");
            result.addObject("reviewer", reviewer);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Reviewer> reviewers;
                reviewers = this.reviewerService.findAll();
                result = new ModelAndView("reviewer/list");
                result.addObject("reviewer",reviewers);
                result.addObject("requestURI", "reviewer/list.do");
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
                final Reviewer reviewer = this.reviewerService.create();
                result = ReviewerController.createEditModelAndView(reviewer);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int reviewerId) {
            ModelAndView result;
            Reviewer reviewer;
            try{
                reviewer = this.reviewerService.findOne(reviewerId);
                Assert.notNull(reviewer);
                result = ReviewerController.createEditModelAndView(reviewer);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Reviewer reviewer, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = ReviewerController.createEditModelAndView(reviewer);
            else
                try {
                    this.reviewerService.save(reviewer);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = ReviewerController.createEditModelAndView(reviewer, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Reviewer reviewer) {
            ModelAndView result;
            try {
                this.reviewerService.delete(reviewer);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    