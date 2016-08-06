package pl.lodz.p.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ip_address")
public class IPaddress {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ip_address_seq_gen")
	@SequenceGenerator(name = "ip_address_seq_gen", sequenceName = "ip_address_seq")
	private Long id;
	@Column
	private String address;
	@Column
	@Enumerated(EnumType.STRING)
	private IPaddressType type;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "network_interface_id")
	private NetworkInterface networkInterface;

	public IPaddress() {
	}

	public IPaddress(final String address, final IPaddressType type) {
		this.address = address;
		this.type = type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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

	public NetworkInterface getNetworkInterface() {
		return networkInterface;
	}

	public void setNetworkInterface(NetworkInterface networkInterface) {
		this.networkInterface = networkInterface;
	}

}
