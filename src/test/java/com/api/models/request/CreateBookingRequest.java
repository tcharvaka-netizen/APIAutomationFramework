package com.api.models.request;

public class CreateBookingRequest {
	private String firstname;
	


	private String lastname;
	private Integer totalprice;
	private Boolean depositpaid;
	private String additionalneeds;
	private CreateBookingDatesRequest bookingdates;
	@Override
	public String toString() {
		return "CreateBookingRequest [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", additionalneeds=" + additionalneeds + ", bookingdates="
				+ bookingdates + "]";
	}



	
	
	
	public CreateBookingRequest(String firstname, String lastname, Integer totalprice, Boolean depositpaid,
			String additionalneeds, CreateBookingDatesRequest bookingdates) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.additionalneeds = additionalneeds;
		this.bookingdates = bookingdates;
	}
	

	
public CreateBookingRequest() {
		
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



public String getAdditionalneeds() {
	return additionalneeds;
}



public void setAdditionalneeds(String additionalneeds) {
	this.additionalneeds = additionalneeds;
}



public CreateBookingDatesRequest getBookingdates() {
	return bookingdates;
}



public void setBookingdates(CreateBookingDatesRequest bookingdates) {
	this.bookingdates = bookingdates;
}

	
public static class Builder{
private String firstname;
private String lastname;
private Integer totalprice;
private Boolean depositpaid;
private String additionalneeds;
private CreateBookingDatesRequest bookingdates;
	
public Builder firstname(String firstname) {
	this.firstname = firstname;
	return this;
}

public Builder lastname(String lastname) {
	this.lastname = lastname;
	return this;
}

public Builder totalprice(Integer totalprice) {
	this.totalprice = totalprice;
	return this;
}

public Builder depositpaid(Boolean depositpaid) {
	this.depositpaid = depositpaid;
	return this;
}

public Builder additionalneeds(String additionalneeds) {
	this.additionalneeds = additionalneeds;
	return this;
}

public Builder bookingdates(CreateBookingDatesRequest bookingdates) {
	this.bookingdates = bookingdates;
	return this;
}
	
public static Builder builder() {
    return new Builder();
}
//public CreateBookingRequest build() {
//	CreateBookingRequest profileRequest = new CreateBookingRequest(firstname, lastname, totalprice, depositpaid,additionalneeds,bookingdates);
//	return profileRequest;
//}

public CreateBookingRequest build() {
	return new CreateBookingRequest(
            firstname,
            lastname,
            totalprice,
            depositpaid,
            additionalneeds,
            bookingdates
    );
}
}
	




}
