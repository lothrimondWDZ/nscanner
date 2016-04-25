package pl.lodz.p.domain.entities;

import java.util.List;

public class NetworkInterface {

	private String name;
	private List<IPaddress> addresses;
	private VLAN vlan;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<IPaddress> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<IPaddress> addresses) {
		this.addresses = addresses;
	}
	
	public VLAN getVlan() {
		return vlan;
	}

	public void setVlan(VLAN vlan) {
		this.vlan = vlan;
	}
	
}
