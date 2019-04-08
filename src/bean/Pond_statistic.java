package bean;


import java.io.Serializable;

public class Pond_statistic implements Serializable {
    private Pond pond;
    private String pid;
    private Param param;
    private String param_id;
    private String value;
    private String type;
    private String time;

    public Pond_statistic(String pid, String param_id, String value, String type, String time) {
        this.pid = pid;
        this.param_id = param_id;
        this.value = value;
        this.type = type;
        this.time = time;
    }

    public Pond_statistic() {
    }

    public Pond_statistic(Pond pond, Param param, String value, String type, String time) {
        this.pond = pond;
        this.param = param;
        this.value = value;
        this.type = type;
        this.time = time;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getParam_id() {
        return param_id;
    }

    public void setParam_id(String param_id) {
        this.param_id = param_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Pond getPond() {
        return pond;
    }

    public void setPond(Pond pond) {
        this.pond = pond;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
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
