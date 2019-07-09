    
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
    public class Submissions extends DomainEntity {

            private String ticker;
private Date moment;
private String status;


        // Constructors -----------------------------------------------------------

        public Submissions() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @NotBlank
        public String getTicker() {
	    	return this.ticker;
	    }

        public void setTicker(String ticker) {
	    	this.ticker = ticker;
    	}

        
        @Past
        public Date getMoment() {
	    	return this.moment;
	    }

        public void setMoment(Date moment) {
	    	this.moment = moment;
    	}

        
        @NotBlank
        public String getStatus() {
	    	return this.status;
	    }

        public void setStatus(String status) {
	    	this.status = status;
    	}

        
    }
    
    