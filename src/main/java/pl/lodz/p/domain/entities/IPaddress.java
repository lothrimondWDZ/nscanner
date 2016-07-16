package pl.lodz.p.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ip_address")
public class IPaddress {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ip_address_seq_gen")
	@SequenceGenerator(name = "ip_address_seq_gen", sequenceName = "ip_address_seq")
	private Integer id;
	@Column
	private String address;
	@Column
	private IPaddressType type;
	@OneToOne(fetch = FetchType.LAZY)
	private Network network;
	@ManyToOne
	@JoinColumn(name = "network_interface_id")
	private NetworkInterface networkInterface;

	public IPaddress() {
	}

	public IPaddress(final String address, final IPaddressType type) {
		this.address = address;
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public IPaddressType getType() {
		return type;
	}

	public void setType(IPaddressType type) {
		this.type = type;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NetworkInterface getNetworkInterface() {
		return networkInterface;
	}

	public void setNetworkInterface(NetworkInterface networkInterface) {
		this.networkInterface = networkInterface;
	}

}
