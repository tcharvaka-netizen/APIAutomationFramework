package com.api.models.response;

import com.api.models.response.GetBookingdateResponse;


public class GetBookingResponse {
	private String firstname;
	private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    private GetBookingdateResponse bookingdates;

    @Override
	public String toString() {
		return "GetBookingResponse [firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", additionalneeds=" + additionalneeds + ", bookingdates="
				+ bookingdates + "]";
	}
	
	    public GetBookingResponse(String firstname, String lastname, Integer totalprice, Boolean depositpaid,
			String additionalneeds, GetBookingdateResponse bookingdates) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.additionalneeds = additionalneeds;
		this.bookingdates = bookingdates;
	}
		
	    public GetBookingResponse() {
			
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
		public GetBookingdateResponse getBookingdates() {
			return bookingdates;
		}
		public void setBookingdates(GetBookingdateResponse bookingdates) {
			this.bookingdates = bookingdates;
		}
		
	    
	
	}


