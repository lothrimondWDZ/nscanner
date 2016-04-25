package pl.lodz.p.domain.entities;

public class IPaddress {

	private String address;
	private IPaddressType type;
	private Network network;
	
	public IPaddress(){}
	
	public IPaddress(final String address, final IPaddressType type){
		this.address = address;
		this.type = type;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public IPaddressType getType() {
		return type;
	}
	
	public void setType(IPaddressType type) {
		this.type = type;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
	
}
