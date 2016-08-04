package pl.lodz.p.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "network_service")
public class NetworkService implements Testable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "network_service_seq_gen")
	@SequenceGenerator(name = "network_service_seq_gen", sequenceName = "network_service_seq")
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private Integer port;
	@ManyToOne
	@JoinColumn(name = "network_interface_id")
	private NetworkInterface networkInterface;
	@OneToMany
	@JoinTable(name = "test_script_network_service", joinColumns = @JoinColumn(name = "network_service_id"),
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

	@Override
	public int runScript(TestScript script) {
		// TODO Auto-generated method stub
		return 0;
	}

}
