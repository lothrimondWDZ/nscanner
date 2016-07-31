package pl.lodz.p.web.rest.dto;

import java.util.List;

import pl.lodz.p.domain.entities.Device;
import pl.lodz.p.domain.entities.IPaddress;

public class NetworkDto {

	private Long id;
	private String name;
	private IPaddress networkAddress;
	private IPaddress broadcast;
	private List<Device> connectedDevices;
	private Integer mask;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IPaddress getNetworkAddress() {
		return networkAddress;
	}

	public void setNetworkAddress(IPaddress networkAddress) {
		this.networkAddress = networkAddress;
	}

	public IPaddress getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(IPaddress broadcast) {
		this.broadcast = broadcast;
	}

	public List<Device> getConnectedDevices() {
		return connectedDevices;
	}

	public void setConnectedDevices(List<Device> connectedDevices) {
		this.connectedDevices = connectedDevices;
	}

	public Integer getMask() {
		return mask;
	}

	public void setMask(Integer mask) {
		this.mask = mask;
	}

}
