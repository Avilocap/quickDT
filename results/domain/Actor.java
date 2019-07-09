    
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
    public class Actor extends DomainEntity {

            private String name;
private String middlename;
private String surname;
private String photo;
private String email;
private String phone;
private String address;


        // Constructors -----------------------------------------------------------

        public Actor() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @NotBlank
        public String getName() {
	    	return this.name;
	    }

        public void setName(String name) {
	    	this.name = name;
    	}

        
        
        public String getMiddlename() {
	    	return this.middlename;
	    }

        public void setMiddlename(String middlename) {
	    	this.middlename = middlename;
    	}

        
        @NotBlank
        public String getSurname() {
	    	return this.surname;
	    }

        public void setSurname(String surname) {
	    	this.surname = surname;
    	}

        
        @URL
        public String getPhoto() {
	    	return this.photo;
	    }

        public void setPhoto(String photo) {
	    	this.photo = photo;
    	}

        
        @NotBlank@URL
        public String getEmail() {
	    	return this.email;
	    }

        public void setEmail(String email) {
	    	this.email = email;
    	}

        
        
        public String getPhone() {
	    	return this.phone;
	    }

        public void setPhone(String phone) {
	    	this.phone = phone;
    	}

        
        
        public String getAddress() {
	    	return this.address;
	    }

        public void setAddress(String address) {
	    	this.address = address;
    	}

        
    }
    
    