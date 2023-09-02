package restFulBooker;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class RestFulPojo {
	
	private Integer bookingid;
	private String firstname;
	private String lastname;
	private Integer totalprice;
	private boolean depositpaid;
	private BookingDate bookingdates;
	private String additionalneeds;
	
	
	public RestFulPojo(String firstname,String lastname,Integer totalprice,boolean depositpaid,BookingDate bookingdates,String additionalneeds ) {
		this.firstname=firstname;
		this.lastname=lastname;
		this.totalprice=totalprice;
		this.depositpaid=depositpaid;
		this.bookingdates=bookingdates;
		this.additionalneeds=additionalneeds;
	}
	
	
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@JsonInclude(Include.NON_NULL)
	public static class BookingDate{
		private String checkin;
		private String checkout;
	}
	
	
	
	
	

}
