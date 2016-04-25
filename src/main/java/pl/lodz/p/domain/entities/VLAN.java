package pl.lodz.p.domain.entities;

import java.util.List;

public class VLAN implements Testable{

	private Integer number;
	private VLANType type;
	private List<TestScript> testScripts;
	private List<NetworkInterface> networkInterfaces;
	
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

	@Override
	public int runScript(TestScript script) {
		// TODO Auto-generated method stub
		return 0;
	}
}
