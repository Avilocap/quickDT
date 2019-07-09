    
    package controllers;


    import domain.Author;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.AuthorService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/author")
    public class AuthorController extends AbstractController {

        @Autowired
        private AuthorService    authorService;


        public AuthorController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Author author) {
            ModelAndView result;

            result = AuthorController.createEditModelAndView(author, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Author author, final String message) {
            ModelAndView result;

            result = new ModelAndView("author/edit");
            result.addObject("author", author);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Author> authors;
                authors = this.authorService.findAll();
                result = new ModelAndView("author/list");
                result.addObject("author",authors);
                result.addObject("requestURI", "author/list.do");
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
                final Author author = this.authorService.create();
                result = AuthorController.createEditModelAndView(author);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int authorId) {
            ModelAndView result;
            Author author;
            try{
                author = this.authorService.findOne(authorId);
                Assert.notNull(author);
                result = AuthorController.createEditModelAndView(author);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Author author, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = AuthorController.createEditModelAndView(author);
            else
                try {
                    this.authorService.save(author);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = AuthorController.createEditModelAndView(author, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Author author) {
            ModelAndView result;
            try {
                this.authorService.delete(author);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    