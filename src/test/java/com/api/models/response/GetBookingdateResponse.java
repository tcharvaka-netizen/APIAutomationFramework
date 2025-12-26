package com.api.models.response;



public class GetBookingdateResponse {
	
	
		private String  checkin;
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

		private String  checkout;
		@Override
		public String toString() {
			return "bookingdates [checkin=" + checkin + ", checkout=" + checkout + "]";
		}
		
		public GetBookingdateResponse(String checkin, String checkout) {
			
			this.checkin = checkin;
			this.checkout = checkout;
		}
public GetBookingdateResponse() {
			
			
		}
	}
	
	









