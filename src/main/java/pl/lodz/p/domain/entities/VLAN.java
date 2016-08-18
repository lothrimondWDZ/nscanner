package pl.lodz.p.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "vlan")
public class VLAN implements Testable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vlan_seq_gen")
	@SequenceGenerator(name = "vlan_seq_gen", sequenceName = "vlan_seq")
	private Long id;
	@Column
	private Integer number;
	@Column
	@Enumerated(EnumType.STRING)
	private VLANType type;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "test_script_vlan", joinColumns = @JoinColumn(name = "vlan_id"),
			inverseJoinColumns = @JoinColumn(name = "test_script_id"))
	private List<TestScript> testScripts;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "network_interface_vlan",
			joinColumns = @JoinColumn(name = "vlan_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(
					name = "network_interface_id", referencedColumnName = "id"))
	private List<NetworkInterface> networkInterfaces;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vlan_vlan", joinColumns = @JoinColumn(name = "first_vlan_id"), inverseJoinColumns = @JoinColumn(
			name = "second_vlan_id"))
	private List<VLAN> vlans;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vlan_network", joinColumns = @JoinColumn(name = "vlan_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "network_id", referencedColumnName = "id"))
	private List<Network> networks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public int runScript(TestScript script) {
		// TODO Auto-generated method stub
		return 0;
	}
}
