package pl.lodz.p.web.rest.dto;

import java.util.List;

import pl.lodz.p.domain.entities.IPaddress;
import pl.lodz.p.domain.entities.NetworkInterfaceType;
import pl.lodz.p.domain.entities.NetworkService;
import pl.lodz.p.domain.entities.VLAN;

public class NetworkInterfaceDto {

	private Integer id;
	private String name;
	private String description;
	private NetworkInterfaceType type;
	private List<IPaddress> addresses;
	private List<VLAN> vlans;
	private List<NetworkService> networkServices;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NetworkInterfaceType getType() {
		return type;
	}

	public void setType(NetworkInterfaceType type) {
		this.type = type;
	}

	public List<IPaddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<IPaddress> addresses) {
		this.addresses = addresses;
	}

	public List<VLAN> getVlans() {
		return vlans;
	}

	public void setVlans(List<VLAN> vlans) {
		this.vlans = vlans;
	}

	public List<NetworkService> getNetworkServices() {
		return networkServices;
	}

	public void setNetworkServices(List<NetworkService> networkServices) {
		this.networkServices = networkServices;
	}
}
