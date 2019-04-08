package bean;

import java.io.Serializable;

public class Param implements Serializable {
    private String pid;
    private String pname;

    public Param() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
