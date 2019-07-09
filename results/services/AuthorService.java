    
    package services;

    import domain.Author;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.AuthorRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class AuthorService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private AuthorRepository        authorRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public AuthorService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Author create(){
            Author result;
            result = new Author();
            return result;
        }
        
        public Collection<Author> findAll() {
            Collection<Author> result;

            result = authorRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Author findOne(int authorId) {
            Assert.isTrue(authorId != 0);

            Author result;

            result = authorRepository.findOne(authorId);
            Assert.notNull(result);

            return result;
        }

        public Author save(Author author) {
            Assert.notNull(author);

            Author result;

            result = authorRepository.save(author);

            return result;
        }

        public void delete(Author author) {
            Assert.notNull(author);
            Assert.isTrue(author.getId() != 0);
            Assert.isTrue(authorRepository.exists(author.getId()));

            authorRepository.delete(author);
        }

        // Other business methods -------------------------------------------------


    }

    