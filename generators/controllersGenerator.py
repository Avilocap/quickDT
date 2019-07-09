import os 

def createController(clase):

    if not (os.path.exists("./results/controllers")):
        os.mkdir("./results/controllers")

    claseminusculas = clase.lower()
    claseMayusculas = clase
    base = """    
    package controllers;


    import domain."""+clase+""";
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services."""+clase+"""Service;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/"""+claseminusculas+"""")
    public class """+clase+"""Controller extends AbstractController {

        @Autowired
        private """+clase+"""Service    """+claseminusculas+"""Service;


        public """+clase+"""Controller() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final """+clase+""" """+claseminusculas+""") {
            ModelAndView result;

            result = """+clase+"""Controller.createEditModelAndView("""+claseminusculas+""", null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final """+clase+""" """+claseminusculas+""", final String message) {
            ModelAndView result;

            result = new ModelAndView("""+'"'+claseminusculas+'/edit");'+'''
            result.addObject('''+'''"'''+claseminusculas+'''", '''+claseminusculas+""");
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<"""+clase+"""> """+claseminusculas+"""s;
                """+claseminusculas+"""s = this."""+claseminusculas+"""Service.findAll();
                result = new ModelAndView("""+'"'+claseminusculas+'/list");'+"""
                result.addObject(""" + '"'+claseminusculas+'",'+claseminusculas+"""s);
                result.addObject("requestURI", """+'"'+claseminusculas+'/list.do");'+"""
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
                final """+clase+""" """+claseminusculas+""" = this."""+claseminusculas+"""Service.create();
                result = """+clase+"""Controller.createEditModelAndView("""+claseminusculas+""");
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int """+claseminusculas+"""Id) {
            ModelAndView result;
            """+clase+""" """+claseminusculas+""";
            try{
                """+claseminusculas+""" = this."""+claseminusculas+"""Service.findOne("""+claseminusculas+"""Id);
                Assert.notNull("""+claseminusculas+""");
                result = """+clase+"""Controller.createEditModelAndView("""+claseminusculas+""");
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final """+clase+""" """+claseminusculas+""", final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = """+clase+"""Controller.createEditModelAndView("""+claseminusculas+""");
            else
                try {
                    this."""+claseminusculas+"""Service.save("""+claseminusculas+""");
                    result = this.list();
                } catch (final Throwable oops) {
                    result = """+clase+"""Controller.createEditModelAndView("""+claseminusculas+""", "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final """+clase+""" """+claseminusculas+""") {
            ModelAndView result;
            try {
                this."""+claseminusculas+"""Service.delete("""+claseminusculas+""");
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    """
    text_file = open(f"results/controllers/{clase}Controller.java", "w")
    text_file.write(base)
    text_file.close()