package pl.lodz.p.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name = "device")
public class Device implements Testable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_seq_gen")
	@SequenceGenerator(name = "device_seq_gen", sequenceName = "device_seq")
	private Long id;
	@Column
	private String name;
	@Column(name = "expiration_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expirationDate;
	@Column
	private String description;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "network_interface_device", joinColumns = @JoinColumn(name = "device_id"),
			inverseJoinColumns = @JoinColumn(name = "network_interface_id"))
	private List<NetworkInterface> networkInterfaces;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "device_device", joinColumns = @JoinColumn(name = "first_device_id"),
			inverseJoinColumns = @JoinColumn(name = "second_device_id"))
	private List<Device> devices;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "test_script_device", joinColumns = @JoinColumn(name = "device_id"),
			inverseJoinColumns = @JoinColumn(name = "test_script_id"))
	private List<TestScript> testScripts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
	private List<NetworkService> networkServices;

	public Device() {
		this.devices = new ArrayList<>();
		this.testScripts = new ArrayList<>();
	}

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

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
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
		this.devices.addAll(devices);
	}

	public List<TestScript> getTestScripts() {
		return testScripts;
	}

	public void setTestScripts(List<TestScript> tests) {
		this.testScripts.addAll(tests);
	}

	public List<NetworkService> getNetworkServices() {
		return networkServices;
	}

	public void setNetworkServices(List<NetworkService> networkServices) {
		this.networkServices = networkServices;
	}

	@Override
	public int runScript(TestScript script) {
		// TODO Auto-generated method stub
		return 0;
	}

}
