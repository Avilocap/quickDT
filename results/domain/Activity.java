    
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
    public class Activity extends DomainEntity {

            private String title;
private List<String> speakers;
private Date startdate;
private Double duration;
private String room;
private String summary;
private List<String> attachment;


        // Constructors -----------------------------------------------------------

        public Activity() {
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

        
        @ElementCollection@NotBlank
        public List<String> getSpeakers() {
	    	return this.speakers;
	    }

        public void setSpeakers(List<String> speakers) {
	    	this.speakers = speakers;
    	}

        
        @NotBlank
        public Date getStartdate() {
	    	return this.startdate;
	    }

        public void setStartdate(Date startdate) {
	    	this.startdate = startdate;
    	}

        
        @Range(min = 0.0)
        public Double getDuration() {
	    	return this.duration;
	    }

        public void setDuration(Double duration) {
	    	this.duration = duration;
    	}

        
        @NotBlank
        public String getRoom() {
	    	return this.room;
	    }

        public void setRoom(String room) {
	    	this.room = room;
    	}

        
        @NotBlank
        public String getSummary() {
	    	return this.summary;
	    }

        public void setSummary(String summary) {
	    	this.summary = summary;
    	}

        
        @ElementCollection@URL
        public List<String> getAttachment() {
	    	return this.attachment;
	    }

        public void setAttachment(List<String> attachment) {
	    	this.attachment = attachment;
    	}

        
    }
    
    