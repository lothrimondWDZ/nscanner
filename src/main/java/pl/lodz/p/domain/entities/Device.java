package pl.lodz.p.domain.entities;

import java.util.List;

import org.bouncycastle.util.IPAddress;

public class Device implements Testable{

	private String name;
	private List<NetworkInterface> networkInterfaces;
	private List<VLAN> availableVlans;
	private List<TestScript> testScripts;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<NetworkInterface> getNetworkInterfaces() {
		return networkInterfaces;
	}

	public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}

	public List<VLAN> getAvailableVlans() {
		return availableVlans;
	}
	
	public void setAvailableVlans(List<VLAN> availableVlans) {
		this.availableVlans = availableVlans;
	}

	public List<TestScript> getTestScripts() {
		return testScripts;
	}

	public void setTestScripts(List<TestScript> tests) {
		this.testScripts = tests;
	}

	@Override
	public int runScript(TestScript script) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
