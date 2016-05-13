package com.goeuro.apiReader.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mohamed.alaaeldin
 * API response Entity 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {

	@JsonProperty("_id")
	private Integer id;

	private String name;

	private String type;

	@JsonProperty("geo_position")
	private GeoPosition geoPosition;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	public static class GeoPosition {
		private Double latitude;

		private Double longitude;

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

	}
}
