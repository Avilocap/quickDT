    
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
    public class Conference extends DomainEntity {

            private String title;
private String acronym;
private String venue;
private Date submissiondeadline;
private Date notificationdeadline;
private Date camera_readydeadline;
private Date startdate;
private Date enddate;
private String summary;
private Double fee;


        // Constructors -----------------------------------------------------------

        public Conference() {
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
        public String getAcronym() {
	    	return this.acronym;
	    }

        public void setAcronym(String acronym) {
	    	this.acronym = acronym;
    	}

        
        @NotBlank
        public String getVenue() {
	    	return this.venue;
	    }

        public void setVenue(String venue) {
	    	this.venue = venue;
    	}

        
        
        public Date getSubmissiondeadline() {
	    	return this.submissiondeadline;
	    }

        public void setSubmissiondeadline(Date submissiondeadline) {
	    	this.submissiondeadline = submissiondeadline;
    	}

        
        
        public Date getNotificationdeadline() {
	    	return this.notificationdeadline;
	    }

        public void setNotificationdeadline(Date notificationdeadline) {
	    	this.notificationdeadline = notificationdeadline;
    	}

        
        
        public Date getCamera_readydeadline() {
	    	return this.camera_readydeadline;
	    }

        public void setCamera_readydeadline(Date camera_readydeadline) {
	    	this.camera_readydeadline = camera_readydeadline;
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

        
        @NotBlank
        public String getSummary() {
	    	return this.summary;
	    }

        public void setSummary(String summary) {
	    	this.summary = summary;
    	}

        
        @Range(min = 0.0)
        public Double getFee() {
	    	return this.fee;
	    }

        public void setFee(Double fee) {
	    	this.fee = fee;
    	}

        
    }
    
    