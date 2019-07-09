    
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
    public class Comment extends DomainEntity {

            private String title;
private Date moment;
private String text;


        // Constructors -----------------------------------------------------------

        public Comment() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @NotBlank
        public String getTitle() {
	    	return this.title;
	    }

        public void setTitle(String title) {
	    	this.title = title;
    	}

        
        @Past
        public Date getMoment() {
	    	return this.moment;
	    }

        public void setMoment(Date moment) {
	    	this.moment = moment;
    	}

        
        @NotBlank
        public String getText() {
	    	return this.text;
	    }

        public void setText(String text) {
	    	this.text = text;
    	}

        
    }
    
    