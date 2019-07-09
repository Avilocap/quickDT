    
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
    public class Finder extends DomainEntity {

            private String keyowrd;
private Date startdate;
private Date enddate;
private String maximumfee;


        // Constructors -----------------------------------------------------------

        public Finder() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        
        public String getKeyowrd() {
	    	return this.keyowrd;
	    }

        public void setKeyowrd(String keyowrd) {
	    	this.keyowrd = keyowrd;
    	}

        
        
        public Date getStartdate() {
	    	return this.startdate;
	    }

        public void setStartdate(Date startdate) {
	    	this.startdate = startdate;
    	}

        
        
        public Date getEnddate() {
	    	return this.enddate;
	    }

        public void setEnddate(Date enddate) {
	    	this.enddate = enddate;
    	}

        
        
        public String getMaximumfee() {
	    	return this.maximumfee;
	    }

        public void setMaximumfee(String maximumfee) {
	    	this.maximumfee = maximumfee;
    	}

        
    }
    
    