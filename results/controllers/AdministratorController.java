    
    package controllers;


    import domain.Administrator;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.AdministratorService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/administrator")
    public class AdministratorController extends AbstractController {

        @Autowired
        private AdministratorService    administratorService;


        public AdministratorController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Administrator administrator) {
            ModelAndView result;

            result = AdministratorController.createEditModelAndView(administrator, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Administrator administrator, final String message) {
            ModelAndView result;

            result = new ModelAndView("administrator/edit");
            result.addObject("administrator", administrator);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Administrator> administrators;
                administrators = this.administratorService.findAll();
                result = new ModelAndView("administrator/list");
                result.addObject("administrator",administrators);
                result.addObject("requestURI", "administrator/list.do");
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
                final Administrator administrator = this.administratorService.create();
                result = AdministratorController.createEditModelAndView(administrator);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int administratorId) {
            ModelAndView result;
            Administrator administrator;
            try{
                administrator = this.administratorService.findOne(administratorId);
                Assert.notNull(administrator);
                result = AdministratorController.createEditModelAndView(administrator);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = AdministratorController.createEditModelAndView(administrator);
            else
                try {
                    this.administratorService.save(administrator);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = AdministratorController.createEditModelAndView(administrator, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Administrator administrator) {
            ModelAndView result;
            try {
                this.administratorService.delete(administrator);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    