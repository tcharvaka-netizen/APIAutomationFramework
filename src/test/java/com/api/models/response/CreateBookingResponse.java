package com.api.models.response;

public class CreateBookingResponse {
private Integer bookingid;
private Booking booking;

public Integer getBookingid() {
	return bookingid;
}



public void setBookingid(Integer bookingid) {
	this.bookingid = bookingid;
}



public Booking getBooking() {
	return booking;
}



public void setBooking(Booking booking) {
	this.booking = booking;
}





public CreateBookingResponse() {
	
}


public CreateBookingResponse(Integer bookingid, Booking booking) {
	super();
	this.bookingid = bookingid;
	this.booking = booking;
	
}



public class Booking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    private BookingDates bookingdates;
    public Booking(String firstname, String lastname, Integer totalprice, Boolean depositpaid,String additionalneeds,
			BookingDates bookingdates) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.additionalneeds=additionalneeds;
		this.bookingdates = bookingdates;
		
	}
    public Booking() {
		
	}
	
    public String getAdditionalneeds() {
    	return additionalneeds;
    }



    public void setAdditionalneeds(String additionalneeds) {
    	this.additionalneeds = additionalneeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }
    
    
    
    public class BookingDates {

        private String checkin;
        private String checkout;
        public BookingDates(String checkin, String checkout) {
    		super();
    		this.checkin = checkin;
    		this.checkout = checkout;
    	}

    	
    	
    	public BookingDates() {
    		
    	}

        public String getCheckin() {
            return checkin;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        public String getCheckout() {
            return checkout;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }
    }

}









}
