    
    package controllers;


    import domain.Panel;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.PanelService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/panel")
    public class PanelController extends AbstractController {

        @Autowired
        private PanelService    panelService;


        public PanelController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Panel panel) {
            ModelAndView result;

            result = PanelController.createEditModelAndView(panel, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Panel panel, final String message) {
            ModelAndView result;

            result = new ModelAndView("panel/edit");
            result.addObject("panel", panel);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Panel> panels;
                panels = this.panelService.findAll();
                result = new ModelAndView("panel/list");
                result.addObject("panel",panels);
                result.addObject("requestURI", "panel/list.do");
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
                final Panel panel = this.panelService.create();
                result = PanelController.createEditModelAndView(panel);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int panelId) {
            ModelAndView result;
            Panel panel;
            try{
                panel = this.panelService.findOne(panelId);
                Assert.notNull(panel);
                result = PanelController.createEditModelAndView(panel);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Panel panel, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = PanelController.createEditModelAndView(panel);
            else
                try {
                    this.panelService.save(panel);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = PanelController.createEditModelAndView(panel, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Panel panel) {
            ModelAndView result;
            try {
                this.panelService.delete(panel);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    