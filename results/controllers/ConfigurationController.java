    
    package controllers;


    import domain.Configuration;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.ConfigurationService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/configuration")
    public class ConfigurationController extends AbstractController {

        @Autowired
        private ConfigurationService    configurationService;


        public ConfigurationController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Configuration configuration) {
            ModelAndView result;

            result = ConfigurationController.createEditModelAndView(configuration, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Configuration configuration, final String message) {
            ModelAndView result;

            result = new ModelAndView("configuration/edit");
            result.addObject("configuration", configuration);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Configuration> configurations;
                configurations = this.configurationService.findAll();
                result = new ModelAndView("configuration/list");
                result.addObject("configuration",configurations);
                result.addObject("requestURI", "configuration/list.do");
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
                final Configuration configuration = this.configurationService.create();
                result = ConfigurationController.createEditModelAndView(configuration);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int configurationId) {
            ModelAndView result;
            Configuration configuration;
            try{
                configuration = this.configurationService.findOne(configurationId);
                Assert.notNull(configuration);
                result = ConfigurationController.createEditModelAndView(configuration);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Configuration configuration, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = ConfigurationController.createEditModelAndView(configuration);
            else
                try {
                    this.configurationService.save(configuration);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = ConfigurationController.createEditModelAndView(configuration, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Configuration configuration) {
            ModelAndView result;
            try {
                this.configurationService.delete(configuration);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    