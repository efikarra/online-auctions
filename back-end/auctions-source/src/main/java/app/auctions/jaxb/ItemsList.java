package app.auctions.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import app.auctions.dto.ItemDTO;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Items")
public class ItemsList {
	
@XmlElement(name="Item")
private List<ItemJaxb> items;

public List<ItemJaxb> getItems() {
	return items;
}

public void setItems(List<ItemJaxb> items) {
	this.items = items;
}
}
