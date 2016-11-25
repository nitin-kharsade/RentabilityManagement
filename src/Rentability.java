import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Rentability
{
	String arrivalFrom;
	String departureTo;
	String checkInDay;
	String checkOutDay;
	int minLenOfStay;
	int maxLenOfSaty;
	int roomId;
	
	public Rentability(String arrivalFrom, String departureTo, String checkInDay, String checkOutDay, 
		String minLenOfStay, String maxLenOfSaty, int roomId) {
		this.arrivalFrom = arrivalFrom;
		this.departureTo = departureTo;
		this.checkInDay = checkInDay;
		this.checkOutDay = checkOutDay;
		this.minLenOfStay = minLenOfStay;
		this.maxLenOfSaty = maxLenOfSaty;
		this.roomId = roomId;
	}

	public Date getArrivalFrom() throws Exception
	{
		return toDate(arrivalFrom);
	}

	public Date getDepartureTo() throws Exception
	{
		return toDate(departureTo);
	}

	public ArrayList getCheckInDay()
	{
		return toArrayList(checkInDay.split(","));
	}

	public ArrayList checkOutDay()
	{
		return toArrayList(checkOutDay.split(","));
	}

	public int getMinLenOfStay()
	{
		return (minLenOfStay == null ? -1 : Integer.parseInt(minLenOfStay));
	}

	public int getMaxLenOfSaty()
	{
		return ( maxLenOfSaty == null ? -1 : Integer.parseInt(maxLenOfSaty) );
	}

	public int getRoomId()
	{
		return roomId;
	}

	private ArrayList toArrayList(String[] array)
	{
		ArrayList list = new ArrayList();
		for(int i = 0; i < array.length; i++)
			list.add(array[i]);
		return list;
	}

	private Date toDate( String date )
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return (sdf.parse(arrivalFrom));
	}

	private boolean isDateIncludedInRange(Date startDate, Date endDate, Date checkDate)
	{	
		return(checkDate.after(startDate) && checkDate.before(endDate) || 
			startDate.compareTo(checkDate) == 0);
	}

	private long dateDiff(Date startDate, Date endDate)
	{
		long seconds = endDate.getTime() - startDate.getTime();
		return (TimeUnit.DAYS.convert(seconds,TimeUnit.MILLISECONDS)); 
	}

	private String getDayFromDate(Date date)
	{
		return (new SimpleDateFormat("EEEE",Locale.ENGLISH).format(date));
	}
}
