    
    package services;

    import domain.Comment;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.util.Assert;
    import repositories.CommentRepository;

    import java.util.Collection;

    @Service
    @Transactional
    public class CommentService {

        // Managed repository -----------------------------------------------------

        @Autowired
        private CommentRepository        commentRepository;

        // Supporting services ----------------------------------------------------



        // Constructors -----------------------------------------------------------

        public CommentService() {
            super();
        }

        // Simple CRUD methods ----------------------------------------------------

        public Comment create(){
            Comment result;
            result = new Comment();
            return result;
        }
        
        public Collection<Comment> findAll() {
            Collection<Comment> result;

            result = commentRepository.findAll();
            Assert.notNull(result);

            return result;
        }

        public Comment findOne(int commentId) {
            Assert.isTrue(commentId != 0);

            Comment result;

            result = commentRepository.findOne(commentId);
            Assert.notNull(result);

            return result;
        }

        public Comment save(Comment comment) {
            Assert.notNull(comment);

            Comment result;

            result = commentRepository.save(comment);

            return result;
        }

        public void delete(Comment comment) {
            Assert.notNull(comment);
            Assert.isTrue(comment.getId() != 0);
            Assert.isTrue(commentRepository.exists(comment.getId()));

            commentRepository.delete(comment);
        }

        // Other business methods -------------------------------------------------


    }

    