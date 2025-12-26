package com.api.models.request;

public class UpdateBookingDatesRequest {
	private String  checkin;
	public UpdateBookingDatesRequest(String checkin, String checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}
	private String checkout;
	public UpdateBookingDatesRequest() {
		
	}
	@Override
	public String toString() {
		return "UpdateBookingDatesRequest [checkin=" + checkin + ", checkout=" + checkout + "]";
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
