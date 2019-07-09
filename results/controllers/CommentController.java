    
    package controllers;


    import domain.Comment;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.CommentService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/comment")
    public class CommentController extends AbstractController {

        @Autowired
        private CommentService    commentService;


        public CommentController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Comment comment) {
            ModelAndView result;

            result = CommentController.createEditModelAndView(comment, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Comment comment, final String message) {
            ModelAndView result;

            result = new ModelAndView("comment/edit");
            result.addObject("comment", comment);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Comment> comments;
                comments = this.commentService.findAll();
                result = new ModelAndView("comment/list");
                result.addObject("comment",comments);
                result.addObject("requestURI", "comment/list.do");
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
                final Comment comment = this.commentService.create();
                result = CommentController.createEditModelAndView(comment);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int commentId) {
            ModelAndView result;
            Comment comment;
            try{
                comment = this.commentService.findOne(commentId);
                Assert.notNull(comment);
                result = CommentController.createEditModelAndView(comment);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Comment comment, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = CommentController.createEditModelAndView(comment);
            else
                try {
                    this.commentService.save(comment);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = CommentController.createEditModelAndView(comment, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Comment comment) {
            ModelAndView result;
            try {
                this.commentService.delete(comment);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    