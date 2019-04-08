package bean;
import java.io.Serializable;
public class Data implements Serializable{
	private static final long serialVersionUID = 1L;

	private String data;
	private String time;
	private Device device;

	public Data(String data, String time, Device device) {
		this.data = data;
		this.time = time;
		this.device = device;
	}

	public Data() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

}
