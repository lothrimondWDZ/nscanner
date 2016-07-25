package pl.lodz.p.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class Device implements Testable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_seq_gen")
	@SequenceGenerator(name = "device_seq_gen", sequenceName = "device_seq")
	private Integer id;
	@Column
	private String name;
	@Column
	private String description;
	@OneToMany
	@JoinTable(name = "network_interface_device", joinColumns = @JoinColumn(name = "device_id"),
			inverseJoinColumns = @JoinColumn(name = "network_interface_id"))
	private List<NetworkInterface> networkInterfaces;
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "device_device", joinColumns = { @JoinColumn(name = "first_device_id",
			referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "second_device_id",
			referencedColumnName = "id") })
	private List<Device> devices;
	@OneToMany
	@JoinTable(name = "test_script_device", joinColumns = @JoinColumn(name = "device_id"),
			inverseJoinColumns = @JoinColumn(name = "test_script_id"))
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

	public void setTestScripts(List<TestScript> tests) {
		this.testScripts = tests;
	}

	@Override
	public int runScript(TestScript script) {
		// TODO Auto-generated method stub
		return 0;
	}

}
