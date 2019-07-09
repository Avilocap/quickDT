    
    package controllers;


    import domain.Category;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.util.Assert;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.servlet.ModelAndView;
    import services.CategoryService;

    import javax.validation.Valid;
    import java.util.Collection;

    @Controller
    @RequestMapping("/category")
    public class CategoryController extends AbstractController {

        @Autowired
        private CategoryService    categoryService;


        public CategoryController() {
            super();
        }

        protected static ModelAndView createEditModelAndView(final Category category) {
            ModelAndView result;

            result = CategoryController.createEditModelAndView(category, null);

            return result;
        }

        protected static ModelAndView createEditModelAndView(final Category category, final String message) {
            ModelAndView result;

            result = new ModelAndView("category/edit");
            result.addObject("category", category);
            result.addObject("message", message);

            return result;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public ModelAndView list() {

            ModelAndView result;
            try {
                Collection<Category> categorys;
                categorys = this.categoryService.findAll();
                result = new ModelAndView("category/list");
                result.addObject("category",categorys);
                result.addObject("requestURI", "category/list.do");
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
                final Category category = this.categoryService.create();
                result = CategoryController.createEditModelAndView(category);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

        }

        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public ModelAndView edit(@RequestParam final int categoryId) {
            ModelAndView result;
            Category category;
            try{
                category = this.categoryService.findOne(categoryId);
                Assert.notNull(category);
                result = CategoryController.createEditModelAndView(category);
                return result;
            }catch(Exception e){
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
        public ModelAndView save(@Valid final Category category, final BindingResult binding) {
            ModelAndView result;
            if (binding.hasErrors())
                result = CategoryController.createEditModelAndView(category);
            else
                try {
                    this.categoryService.save(category);
                    result = this.list();
                } catch (final Throwable oops) {
                    result = CategoryController.createEditModelAndView(category, "general.commit.error");
                }

            return result;
        }

        @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
        public ModelAndView delete(final Category category) {
            ModelAndView result;
            try {
                this.categoryService.delete(category);
                result = new ModelAndView("redirect:list.do");
            } catch (final Throwable e) {
                result = new ModelAndView("administrator/error");
                result.addObject("trace", e.getMessage());
                return result;
            }

            return result;
        }
    }
    