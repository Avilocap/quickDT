    
    package domain;


    import javax.persistence.Access;
    import javax.persistence.AccessType;
    import javax.persistence.Entity;

    import org.hibernate.validator.constraints.NotBlank;
    import org.hibernate.validator.constraints.Range;
    import org.hibernate.validator.constraints.URL;

    import java.util.Date;
    import java.util.List;

    @Entity
    @Access(AccessType.PROPERTY)
    public class Reviewer extends Actor {

            private List<String> keyword;


        // Constructors -----------------------------------------------------------

        public Reviewer() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @ElementCollection@URL
        public List<String> getKeyword() {
	    	return this.keyword;
	    }

        public void setKeyword(List<String> keyword) {
	    	this.keyword = keyword;
    	}

        
    }
    
    