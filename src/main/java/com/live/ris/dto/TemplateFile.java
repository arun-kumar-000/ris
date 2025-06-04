package com.live.ris.dto;

import java.time.Instant;

public class TemplateFile {
    private String name;
    private Instant lastModified;

    public TemplateFile(String name, Instant lastModified) {
        this.name = name;
        this.lastModified = lastModified;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instant getLastModified() {
		return lastModified;
	}

	public void setLastModified(Instant lastModified) {
		this.lastModified = lastModified;
	}

    // Getters and setters
}
