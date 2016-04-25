package pl.lodz.p.domain.entities;

import java.nio.file.Path;

public class TestScript {
	
	private String name;
	private String description;
	private Path path;
	
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
	
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
	
}
