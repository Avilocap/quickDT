    
    package controllers;


    import domain.Presentation;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.PresentationService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/presentation")
    public class PresentationController extends AbstractController {

        @Autowired
        private PresentationService    presentationService;


        public PresentationController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Presentation presentation) {
            ModelAndView result;

            result = PresentationController.createEditModelAndView(presentation, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Presentation presentation, final String message) {
            ModelAndView result;

            result = new ModelAndView("presentation/edit");
            result.addObject("presentation", presentation);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Presentation> presentations;
                presentations = this.presentationService.findAll();
                result = new ModelAndView("presentation/list");
                result.addObject("presentation",presentations);
                result.addObject("requestURI", "presentation/list.do");
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
                final Presentation presentation = this.presentationService.create();
                result = PresentationController.createEditModelAndView(presentation);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int presentationId) {
            ModelAndView result;
            Presentation presentation;
            try{
                presentation = this.presentationService.findOne(presentationId);
                Assert.notNull(presentation);
                result = PresentationController.createEditModelAndView(presentation);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Presentation presentation, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = PresentationController.createEditModelAndView(presentation);
            else
                try {
                    this.presentationService.save(presentation);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = PresentationController.createEditModelAndView(presentation, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Presentation presentation) {
            ModelAndView result;
            try {
                this.presentationService.delete(presentation);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    