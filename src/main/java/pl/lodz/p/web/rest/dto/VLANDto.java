package pl.lodz.p.web.rest.dto;

import java.util.List;

import pl.lodz.p.domain.entities.Network;
import pl.lodz.p.domain.entities.NetworkInterface;
import pl.lodz.p.domain.entities.TestScript;
import pl.lodz.p.domain.entities.VLAN;
import pl.lodz.p.domain.entities.VLANType;

public class VLANDto {

	private Integer id;
	private Integer number;
	private VLANType type;
	private List<TestScript> testScripts;
	private List<NetworkInterface> networkInterfaces;
	private List<VLAN> vlans;
	private List<Network> networks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public VLANType getType() {
		return type;
	}

	public void setType(VLANType type) {
		this.type = type;
	}

	public List<TestScript> getTestScripts() {
		return testScripts;
	}

	public void setTestScripts(List<TestScript> testScripts) {
		this.testScripts = testScripts;
	}

	public List<NetworkInterface> getNetworkInterfaces() {
		return networkInterfaces;
	}

	public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}

	public List<VLAN> getVlans() {
		return vlans;
	}

	public void setVlans(List<VLAN> vlans) {
		this.vlans = vlans;
	}

	public List<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(List<Network> networks) {
		this.networks = networks;
	}

}
