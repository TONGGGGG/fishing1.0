package bean;

import java.io.Serializable;

public class Device_statistic implements Serializable {

    private Device device;
    private String did;
    private Param param;
    private String paramId;
    private String value;
    private String type;
    private String time;

    public Device_statistic() {
    }

    public Device_statistic(Device device, Param param, String value, String type, String time) {
        this.device = device;
        this.param = param;
        this.value = value;
        this.type = type;
        this.time = time;
    }

    public Device_statistic(String did, String paramId, String value, String type, String time) {
        this.did = did;
        this.paramId = paramId;
        this.value = value;
        this.type = type;
        this.time = time;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
