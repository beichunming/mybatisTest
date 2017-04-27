package cn.sxt.entity;

import java.io.Serializable;

/**
 * Created by beichunming on 2017/4/22.
 */
public class User implements Serializable{
    private  int id;
    private  String name;
    private  String loginName;
    private  String loginPswd;
    private  String remark;
    private  int groupId;
    private Group group;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getGroupId() != user.getGroupId()) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getLoginName() != null ? !getLoginName().equals(user.getLoginName()) : user.getLoginName() != null) return false;
        if (getLoginPswd() != null ? !getLoginPswd().equals(user.getLoginPswd()) : user.getLoginPswd() != null) return false;
        return getRemark() != null ? getRemark().equals(user.getRemark()) : user.getRemark() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLoginName() != null ? getLoginName().hashCode() : 0);
        result = 31 * result + (getLoginPswd() != null ? getLoginPswd().hashCode() : 0);
        result = 31 * result + (getRemark() != null ? getRemark().hashCode() : 0);
        result = 31 * result + getGroupId();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPswd='" + loginPswd + '\'' +
                ", remark='" + remark + '\'' +
                ", groupId=" + groupId +
                ", group=" + group +
                '}';
    }

    public User() {
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPswd() {
        return loginPswd;
    }

    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
