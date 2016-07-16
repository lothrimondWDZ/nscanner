package pl.lodz.p.domain.entities;

public enum NetworkInterfaceType {
	TRUNK("trunk"), ACCESS("dostępowy");
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	private NetworkInterfaceType(String description) {
		this.description = description;
	}
}
