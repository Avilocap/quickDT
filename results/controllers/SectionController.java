    
    package controllers;


    import domain.Section;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.SectionService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/section")
    public class SectionController extends AbstractController {

        @Autowired
        private SectionService    sectionService;


        public SectionController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Section section) {
            ModelAndView result;

            result = SectionController.createEditModelAndView(section, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Section section, final String message) {
            ModelAndView result;

            result = new ModelAndView("section/edit");
            result.addObject("section", section);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Section> sections;
                sections = this.sectionService.findAll();
                result = new ModelAndView("section/list");
                result.addObject("section",sections);
                result.addObject("requestURI", "section/list.do");
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
                final Section section = this.sectionService.create();
                result = SectionController.createEditModelAndView(section);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int sectionId) {
            ModelAndView result;
            Section section;
            try{
                section = this.sectionService.findOne(sectionId);
                Assert.notNull(section);
                result = SectionController.createEditModelAndView(section);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Section section, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = SectionController.createEditModelAndView(section);
            else
                try {
                    this.sectionService.save(section);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = SectionController.createEditModelAndView(section, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Section section) {
            ModelAndView result;
            try {
                this.sectionService.delete(section);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    