    
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
    public class Reports extends DomainEntity {

            private String decision;
private List<String> comments;
private Double originalityscore;
private Double qualityscore;
private Double redabilityscore;


        // Constructors -----------------------------------------------------------

        public Reports() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @NotBlank
        public String getDecision() {
	    	return this.decision;
	    }

        public void setDecision(String decision) {
	    	this.decision = decision;
    	}

        
        
        public List<String> getComments() {
	    	return this.comments;
	    }

        public void setComments(List<String> comments) {
	    	this.comments = comments;
    	}

        
        @Range(min = 0.0, max = 10.0)
        public Double getOriginalityscore() {
	    	return this.originalityscore;
	    }

        public void setOriginalityscore(Double originalityscore) {
	    	this.originalityscore = originalityscore;
    	}

        
        @Range(min = 0.0, max = 10.0)
        public Double getQualityscore() {
	    	return this.qualityscore;
	    }

        public void setQualityscore(Double qualityscore) {
	    	this.qualityscore = qualityscore;
    	}

        
        @Range(min = 0.0, max = 10.0)
        public Double getRedabilityscore() {
	    	return this.redabilityscore;
	    }

        public void setRedabilityscore(Double redabilityscore) {
	    	this.redabilityscore = redabilityscore;
    	}

        
    }
    
    