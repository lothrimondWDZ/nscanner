package pl.lodz.p.web.rest.dto;

import java.util.List;

import pl.lodz.p.domain.entities.NetworkInterface;
import pl.lodz.p.domain.entities.TestScript;

public class NetworkServiceDto {

	private Integer id;
	private String name;
	private String description;
	private Integer port;
	private NetworkInterface networkInterface;
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

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public NetworkInterface getNetworkInterface() {
		return networkInterface;
	}

	public void setNetworkInterface(NetworkInterface networkInterface) {
		this.networkInterface = networkInterface;
	}

	public List<TestScript> getTestScripts() {
		return testScripts;
	}

	public void setTestScripts(List<TestScript> testScripts) {
		this.testScripts = testScripts;
	}
}
