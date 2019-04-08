package bean;

import java.io.Serializable;
import java.util.Set;

public class Creature implements Serializable {

    private String cid;
    private String cname;

    private Set<Cre_param> cre_params;

    public Creature() {
    }

    public Set<Cre_param> getCre_params() {
        return cre_params;
    }

    public void setCre_params(Set<Cre_param> cre_params) {
        this.cre_params = cre_params;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
