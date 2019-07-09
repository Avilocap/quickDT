    
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
    public class Sponsorship extends DomainEntity {

            private String banner;
private String targeturl;


        // Constructors -----------------------------------------------------------

        public Sponsorship() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @NotBlank@URL
        public String getBanner() {
	    	return this.banner;
	    }

        public void setBanner(String banner) {
	    	this.banner = banner;
    	}

        
        @NotBlank@URL
        public String getTargeturl() {
	    	return this.targeturl;
	    }

        public void setTargeturl(String targeturl) {
	    	this.targeturl = targeturl;
    	}

        
    }
    
    