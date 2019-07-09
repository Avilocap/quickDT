    
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
    public class Message extends DomainEntity {

            private Date senddate;
private String subject;
private String body;
private String topic;


        // Constructors -----------------------------------------------------------

        public Message() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @Past
        public Date getSenddate() {
	    	return this.senddate;
	    }

        public void setSenddate(Date senddate) {
	    	this.senddate = senddate;
    	}

        
        @NotBlank
        public String getSubject() {
	    	return this.subject;
	    }

        public void setSubject(String subject) {
	    	this.subject = subject;
    	}

        
        @NotBlank
        public String getBody() {
	    	return this.body;
	    }

        public void setBody(String body) {
	    	this.body = body;
    	}

        
        @NotBlank
        public String getTopic() {
	    	return this.topic;
	    }

        public void setTopic(String topic) {
	    	this.topic = topic;
    	}

        
    }
    
    