package com.linkedbear.mybatis.encapsulate.c_extra.entity;

import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Id;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.LogicDelete;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Table;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Transient;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.Version;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.query.Equals;
import com.linkedbear.mybatis.encapsulate.c_extra.annotation.query.Like;

import java.io.Serializable;
import java.util.Objects;

@Table("tbl_department")
public class Department implements Serializable {
    private static final long serialVersionUID = -2062845216604443970L;
    
    @Id
    @Equals
    private String id;
    
    @Version
    private Integer version;
    
    @Like
    private String name;
    
    @Like
    private String tel;
    
    @Transient
    private String redundant;
    
    @LogicDelete
    @Equals
    private Boolean isdel;
    
    @Override
    public String toString() {
        return "Department{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", tel='" + tel + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public String getRedundant() {
        return redundant;
    }
    
    public void setRedundant(String redundant) {
        this.redundant = redundant;
    }
    
    public Boolean getIsdel() {
        return isdel;
    }
    
    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }
}
