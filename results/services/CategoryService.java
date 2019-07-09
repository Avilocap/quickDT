    
    package services;

    import domain.Category;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.CategoryRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class CategoryService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private CategoryRepository        categoryRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public CategoryService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Category create(){
            Category result;
            result = new Category();
            return result;
        }
        
        public Collection<Category> findAll() {
            Collection<Category> result;

            result = categoryRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Category findOne(int categoryId) {
            Assert.isTrue(categoryId != 0);

            Category result;

            result = categoryRepository.findOne(categoryId);
            Assert.notNull(result);

            return result;
        }

        public Category save(Category category) {
            Assert.notNull(category);

            Category result;

            result = categoryRepository.save(category);

            return result;
        }

        public void delete(Category category) {
            Assert.notNull(category);
            Assert.isTrue(category.getId() != 0);
            Assert.isTrue(categoryRepository.exists(category.getId()));

            categoryRepository.delete(category);
        }

        // Other business methods -------------------------------------------------


    }

    