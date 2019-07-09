    
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
    public class Paper extends DomainEntity {

            private String title;
private String summary;
private String document;
private Boolean cameraready;


        // Constructors -----------------------------------------------------------

        public Paper() {
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

        
        @NotBlank@URL
        public String getDocument() {
	    	return this.document;
	    }

        public void setDocument(String document) {
	    	this.document = document;
    	}

        
        @NotNull
        public Boolean getCameraready() {
	    	return this.cameraready;
	    }

        public void setCameraready(Boolean cameraready) {
	    	this.cameraready = cameraready;
    	}

        
    }
    
    