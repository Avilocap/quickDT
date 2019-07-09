    
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
    public class Section extends DomainEntity {

            private String title;
private String summary;
private List<String> picture;


        // Constructors -----------------------------------------------------------

        public Section() {
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

        
        @NotBlank
        public String getSummary() {
	    	return this.summary;
	    }

        public void setSummary(String summary) {
	    	this.summary = summary;
    	}

        
        @ElementCollection@URL
        public List<String> getPicture() {
	    	return this.picture;
	    }

        public void setPicture(List<String> picture) {
	    	this.picture = picture;
    	}

        
    }
    
    