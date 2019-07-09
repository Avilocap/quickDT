    
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
    public class CreditCard extends DomainEntity {

            private String holder;
private String number;
private String brand;
private Date expiration;
private Integer cvv;


        // Constructors -----------------------------------------------------------

        public CreditCard() {
                super();
            }


        // Attributes -------------------------------------------------------------
        
        @NotBlank
        public String getHolder() {
	    	return this.holder;
	    }

        public void setHolder(String holder) {
	    	this.holder = holder;
    	}

        
        @NotBlank
        public String getNumber() {
	    	return this.number;
	    }

        public void setNumber(String number) {
	    	this.number = number;
    	}

        
        @NotBlank
        public String getBrand() {
	    	return this.brand;
	    }

        public void setBrand(String brand) {
	    	this.brand = brand;
    	}

        
        @NotNull
        public Date getExpiration() {
	    	return this.expiration;
	    }

        public void setExpiration(Date expiration) {
	    	this.expiration = expiration;
    	}

        
        @NotNull@Range(min = 100,max=999)
        public Integer getCvv() {
	    	return this.cvv;
	    }

        public void setCvv(Integer cvv) {
	    	this.cvv = cvv;
    	}

        
    }
    
    