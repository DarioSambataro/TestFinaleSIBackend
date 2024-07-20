package it.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Forecasts")
public class Forecast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "latitude")
	private float latitude;
	
	@Column(name = "longitude")
	private float longitude;
	
	@Column(name = "period")
	private String period;
	
	@Column(name = "min_temperature")
	private float minTemperature;
	
	@Column(name = "max_temperature")
	private float maxTemperature;
	
	@Column(name = "wind_speed")
	private float windSpeed;
	
	@Column(name = "wind_direction")
	private float windDirection;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public float getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(float minTemperature) {
		this.minTemperature = minTemperature;
	}

	public float getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(float maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public float getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(float windDirection) {
		this.windDirection = windDirection;
	}
	
	
}

//CREATE TABLE IF NOT EXISTS Forecasts(
//		id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
//	    location VARCHAR(255) NOT NULL,
//	    latitude DECIMAL(5, 1) NOT NULL,
//	    longitude DECIMAL(5, 1) NOT NULL,
//	    period VARCHAR(255) NOT NULL,
//	     min_temperature DECIMAL(5,1) NOT NULL,
//		max_temperature DECIMAL(5,1) NOT NULL,
//		wind_speed DECIMAL(5,2) NOT NULL,
//		wind_direction DECIMAL(5,0) NOT NULL
//	);