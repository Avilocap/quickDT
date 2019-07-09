    
    package controllers;


    import domain.Finder;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.FinderService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/finder")
    public class FinderController extends AbstractController {

        @Autowired
        private FinderService    finderService;


        public FinderController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Finder finder) {
            ModelAndView result;

            result = FinderController.createEditModelAndView(finder, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Finder finder, final String message) {
            ModelAndView result;

            result = new ModelAndView("finder/edit");
            result.addObject("finder", finder);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Finder> finders;
                finders = this.finderService.findAll();
                result = new ModelAndView("finder/list");
                result.addObject("finder",finders);
                result.addObject("requestURI", "finder/list.do");
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
                final Finder finder = this.finderService.create();
                result = FinderController.createEditModelAndView(finder);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int finderId) {
            ModelAndView result;
            Finder finder;
            try{
                finder = this.finderService.findOne(finderId);
                Assert.notNull(finder);
                result = FinderController.createEditModelAndView(finder);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = FinderController.createEditModelAndView(finder);
            else
                try {
                    this.finderService.save(finder);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = FinderController.createEditModelAndView(finder, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Finder finder) {
            ModelAndView result;
            try {
                this.finderService.delete(finder);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    