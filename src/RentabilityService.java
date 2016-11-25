import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

class RentabilityService extends Rentability
{
	Rentability roomRentabilities[] = {
		new Rentability("01/11/2016", "14/11/2016", "friday,monday", "friday,monday", 3, 14, 1),
		new Rentability("21/11/2016", "02/12/2016", "monday", "friday",  3, 14, 1),
		new Rentability("03/12/2016", "02/01/2017", "monday,friday", "monday,friday",	7, 21, 1),
		new Rentability("03/12/2016", "02/01/2017", "monday,friday", "monday,friday", 2, 5, 2)
	};

	
	public boolean isBookable(int roomId, Date userArrivalDate, Date userDepartureDate) throws Exception 
	{
		ArrayList rentabilities = roomRantabitity(roomId);
		boolean isvalidBooking = false;
		for (int i = 0; i < rentabilities.size(); i++)
		{
			isvalidBooking = validateBooking(i, userArrivalDate, userDepartureDate, rentabilities );
			if (isvalidBooking) 
			{
				return true;
			}
		}
		return false;
	}

	public ArrayList roomRantabitity( int roomId )
	{
		ArrayList roomRantabity = new ArrayList();
		for (int i = 0;  i < roomRentabilities.length; i++)
		{
			if ( roomRentabilities[i].getRoomId() == roomId )
				roomRantabity.add(roomRentabilities[i]);
		}
		return roomRantabity;
	}

	public Rentability continuesRentaibility(int currentIndex, ArrayList rentabilities) throws Exception
	{
		int continuesRentaibilityIndex = currentIndex;
		Date departureToDate = ((Rentability) rentabilities.get(currentIndex)).getDepartureTo();
		for (int i = currentIndex;  i < rentabilities.size(); i++)
		{
			Date arrivalDate = ((Rentability)rentabilities.get(i)).getArrivalFrom();
			long dateDiff = dateDiff(departureToDate, arrivalDate);
			continuesRentaibilityIndex = (dateDiff == 1) ? i : continuesRentaibilityIndex;
			departureToDate =  ((Rentability) rentabilities.get(continuesRentaibilityIndex)).getDepartureTo();
		}
		return ((Rentability) rentabilities.get(continuesRentaibilityIndex));
	}

	/* need check thorw exception is need or not */
	public boolean validateBooking(int i, Date userArrivalDate, Date userDepartureDate, ArrayList rentabilities) throws Exception 
	{
		Date arrivalFrom = ((Rentability) rentabilities.get(i)).getArrivalFrom();
		// find continues rentability departure date.
		Date departureTo = continuesRentaibility(i, rentabilities).getDepartureTo();
		int minStay = ((Rentability) rentabilities.get(i)).getMinLenOfStay();
		int maxStay = ((Rentability) rentabilities.get(i)).getMaxLenOfSaty();

		// find user stying days count
		long userStayDays = dateDiff(userArrivalDate, userDepartureDate);
		ArrayList checkIndays = ((Rentability) rentabilities.get(i)).getCheckInDay();
		ArrayList checkOutdays = continuesRentaibility(i, rentabilities).checkOutDay();

		// find the user arrival and departure days
		String userArrivalDay = getDayFromDate(userArrivalDate);
		String userDepartureDay = getDayFromDate(userDepartureDate);
		return (
			isDateIncludedInRange(arrivalFrom, departureTo, userArrivalDate) &&
			isDateIncludedInRange(arrivalFrom, departureTo, userDepartureDate) && 
			userStayDays >= minStay &&
			userStayDays <= maxStay &&
			checkIndays.contains(userArrivalDay.toLowerCase()) &&
			checkOutdays.contains(userDepartureDay.toLowerCase())
			);
	}
}