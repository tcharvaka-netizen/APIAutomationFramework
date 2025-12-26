package com.api.models.request;

public class CreateBookingDatesRequest {
private String  checkin;
private String checkout;
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

public CreateBookingDatesRequest(){
	
}
public CreateBookingDatesRequest(String checkin, String checkout) {
	
	this.checkin = checkin;
	this.checkout = checkout;
}
public static class Builder {
	private String  checkin;
	private String checkout;
	
	public Builder checkin(String checkin) {
		this.checkin = checkin;
		return this;
	}
		public Builder checkout(String checkout) {
			this.checkout = checkout;
			return this;
	}
		
		
}
	public CreateBookingDatesRequest build() {
		CreateBookingDatesRequest signUpRequest = new CreateBookingDatesRequest(checkin, checkout);
		return signUpRequest;

	}

}
