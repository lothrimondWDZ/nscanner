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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "network")
public class Network {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "network_seq_gen")
	@SequenceGenerator(name = "network_seq_gen", sequenceName = "network_seq")
	private Integer id;
	@Column
	private String name;
	@JoinColumn(name = "network_address")
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "network", cascade = CascadeType.ALL)
	private IPaddress networkAddress;
	@JoinColumn(name = "broadcast")
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "network", cascade = CascadeType.ALL)
	private IPaddress broadcast;
	@ManyToMany
	@JoinTable(name = "device_device", joinColumns = @JoinColumn(name = "first_device_id"),
			inverseJoinColumns = @JoinColumn(name = "second_device_id"))
	private List<Device> connectedDevices;
	@Column
	private Integer mask;

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
