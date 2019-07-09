    
    package controllers;


    import domain.Tutorial;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.TutorialService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/tutorial")
    public class TutorialController extends AbstractController {

        @Autowired
        private TutorialService    tutorialService;


        public TutorialController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Tutorial tutorial) {
            ModelAndView result;

            result = TutorialController.createEditModelAndView(tutorial, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Tutorial tutorial, final String message) {
            ModelAndView result;

            result = new ModelAndView("tutorial/edit");
            result.addObject("tutorial", tutorial);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Tutorial> tutorials;
                tutorials = this.tutorialService.findAll();
                result = new ModelAndView("tutorial/list");
                result.addObject("tutorial",tutorials);
                result.addObject("requestURI", "tutorial/list.do");
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
                final Tutorial tutorial = this.tutorialService.create();
                result = TutorialController.createEditModelAndView(tutorial);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int tutorialId) {
            ModelAndView result;
            Tutorial tutorial;
            try{
                tutorial = this.tutorialService.findOne(tutorialId);
                Assert.notNull(tutorial);
                result = TutorialController.createEditModelAndView(tutorial);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Tutorial tutorial, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = TutorialController.createEditModelAndView(tutorial);
            else
                try {
                    this.tutorialService.save(tutorial);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = TutorialController.createEditModelAndView(tutorial, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Tutorial tutorial) {
            ModelAndView result;
            try {
                this.tutorialService.delete(tutorial);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    