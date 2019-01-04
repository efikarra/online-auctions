package app.auctions.jaxb;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter ;

public class TimestampAdapter extends XmlAdapter<String, Timestamp>{
	public Timestamp unmarshal( String date ) throws Exception
		    {
		        DateFormat format=new SimpleDateFormat("MMM-d-yy HH:mm:ss");
		        return new Timestamp( format.parse(date).getTime());
		    }
		 
		    public String marshal( Timestamp date ) throws Exception
		    {
		    	DateFormat format=new SimpleDateFormat("MMM-d-yy HH:mm:ss");
		        return format.format(date.getTime());
		    }
		 
}
