package modelos;

public class Geo {
	private String lat;
	private String lng;
	//recuerda que cuando una api trae objetos dentro en java devemos trabajar cada objeto en un modelo distinto siempre si? recuerda
	public Geo() {
		
	}
	/**
	 * @param lat
	 * @param lng
	 */
	public Geo(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	//ahora ya que esto se va a usar de manera local es necesario crear el metodo toString
	@Override
	public String toString() {
		return "lat: " + lat + ", lng: " + lng;
 	}
	

}
