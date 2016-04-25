package pl.lodz.p.domain.entities;

import java.util.List;

public class Network {

	private String name;
	private IPaddress networkAddress;
	private IPaddress broadcast;
	private List<Device> connectedDevices;
	private Integer mask;
	
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
