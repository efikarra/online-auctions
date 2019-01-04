package app.auctions.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.beans.factory.annotation.Autowired;

import app.auctions.dto.LocationDTO;
import app.auctions.dto.UserDTO;
import app.auctions.dto.utils.HibernateMapperFactory;
import app.auctions.model.User;
import app.auctions.service.UserService;

public class LocationAdapter extends XmlAdapter<LocationJaxb,LocationDTO>{

	@Autowired
	UserService userService;
	@Autowired
	HibernateMapperFactory mapperFactory;
	
	public LocationDTO unmarshal(LocationJaxb l) throws Exception
    {
		LocationDTO ldto = new LocationDTO();
		ldto.setCountry(l.getCountry());
		ldto.setLatitude(l.getLocation().getLatitude());
		ldto.setLongitude(l.getLocation().getLongitude());
		ldto.setLocationName(l.getLocation().getLocationName());
		return ldto;
    }
 
    public LocationJaxb marshal( LocationDTO ldto ) throws Exception
    {
    	LocationJaxb l=new LocationJaxb();
    	l.setLocation(ldto);
    	l.setCountry(ldto.getCountry());
    	
        return l;
    }
 

}
