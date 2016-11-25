import java.util.Date;

public class BookingTest
{

	public static void main(String[] args) throws Exception
	{
		RentabilityService rentyabilityService = new RentabilityService();
		Date arrivalDate = rentyabilityService.toDate("28/11/2016");
		Date departureDate = rentyabilityService.toDate("05/12/2016");
		if (rentyabilityService.isBookable(1, arrivalDate, departureDate))
			System.out.println(" Booking is available on mentioned date");
		else
			System.out.println("Booking not available on mentioned date");
	}

}
