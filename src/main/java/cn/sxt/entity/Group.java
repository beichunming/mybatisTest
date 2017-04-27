package cn.sxt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by beichunming on 2017/4/22.
 */
public class Group implements Serializable{
    private  int id;
    private  String name;
    private  String remark;
    private List<User> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (getId() != group.getId()) return false;
        if (getName() != null ? !getName().equals(group.getName()) : group.getName() != null) return false;
        return getRemark() != null ? getRemark().equals(group.getRemark()) : group.getRemark() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getRemark() != null ? getRemark().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", userList=" + userList +
                '}';
    }

    public Group() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
