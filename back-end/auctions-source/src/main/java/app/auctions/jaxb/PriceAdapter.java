package app.auctions.jaxb;


import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PriceAdapter extends XmlAdapter<String, Double>{
	public Double unmarshal( String price ) throws Exception
    {
        return Double.valueOf(price.substring(1, price.length()));
    }
 
    public String marshal( Double price ) throws Exception
    {   	

    	Double truncatedDouble=new BigDecimal(price ).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return "$"+ new DecimalFormat("#0.00").format(price);
    }
 
}