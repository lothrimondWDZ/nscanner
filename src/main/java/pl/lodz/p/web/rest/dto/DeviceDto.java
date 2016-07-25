package pl.lodz.p.web.rest.dto;

import java.util.List;

import pl.lodz.p.domain.entities.Device;
import pl.lodz.p.domain.entities.NetworkInterface;
import pl.lodz.p.domain.entities.TestScript;

public class DeviceDto {

	private Integer id;
	private String name;
	private String description;
	private List<NetworkInterface> networkInterfaces;
	private List<Device> devices;
	private List<TestScript> testScripts;

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

	public List<NetworkInterface> getNetworkInterfaces() {
		return networkInterfaces;
	}

	public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<TestScript> getTestScripts() {
		return testScripts;
	}

	public void setTestScripts(List<TestScript> testScripts) {
		this.testScripts = testScripts;
	}
}
