package bean;

public class Device {
	private String did;
	private Pond pond;
	private DeviceType type;

	private String dname;
	private String state;
	private String pn;
	private String hn;
	private String h_version;
	private String s_version;

	public Device() {
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public String getH_version() {
		return h_version;
	}

	public void setH_version(String h_version) {
		this.h_version = h_version;
	}

	public String getS_version() {
		return s_version;
	}

	public void setS_version(String s_version) {
		this.s_version = s_version;
	}

	public Pond getPond() {
		return pond;
	}

	public void setPond(Pond pond) {
		this.pond = pond;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}


	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}


	
	
}
