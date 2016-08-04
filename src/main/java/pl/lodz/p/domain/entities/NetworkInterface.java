package pl.lodz.p.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "network_interface")
public class NetworkInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "network_interface_seq_gen")
	@SequenceGenerator(name = "network_interface_seq_gen", sequenceName = "network_interface_seq")
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	@Enumerated(EnumType.STRING)
	private NetworkInterfaceType type;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "networkInterface")
	private List<IPaddress> addresses;
	@ManyToMany
	@JoinTable(name = "network_interface_vlan", joinColumns = @JoinColumn(name = "network_interface_id",
			referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "vlan_id",
			referencedColumnName = "id"))
	private List<VLAN> vlans;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "networkInterface")
	private List<NetworkService> networkServices;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NetworkInterfaceType getType() {
		return type;
	}

	public void setType(NetworkInterfaceType type) {
		this.type = type;
	}

	public List<IPaddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<IPaddress> addresses) {
		this.addresses = addresses;
	}

	public List<VLAN> getVlans() {
		return vlans;
	}

	public void setVlans(List<VLAN> vlans) {
		this.vlans = vlans;
	}

	public List<NetworkService> getNetworkServices() {
		return networkServices;
	}

	public void setNetworkServices(List<NetworkService> networkServices) {
		this.networkServices = networkServices;
	}

}
