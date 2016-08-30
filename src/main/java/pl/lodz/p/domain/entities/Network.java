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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "network")
public class Network {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "network_seq_gen")
	@SequenceGenerator(name = "network_seq_gen", sequenceName = "network_seq")
	private Long id;
	@Column
	private String name;
	@JoinColumn(name = "network_address")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private IPaddress networkAddress;
	@Column
	private Integer mask;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "test_script_network", joinColumns = @JoinColumn(name = "network_id"),
			inverseJoinColumns = @JoinColumn(name = "test_script_id"))
	private List<TestScript> testScripts;

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

	public IPaddress getNetworkAddress() {
		return networkAddress;
	}

	public void setNetworkAddress(IPaddress networkAddress) {
		this.networkAddress = networkAddress;
	}

	public Integer getMask() {
		return mask;
	}

	public void setMask(Integer mask) {
		this.mask = mask;
	}

	public List<TestScript> getTestScripts() {
		return testScripts;
	}

	public void setTestScripts(List<TestScript> testScripts) {
		this.testScripts = testScripts;
	}

}
