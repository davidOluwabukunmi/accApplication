package com.example.accapplication.model;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Roles implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "role_id")

    private Integer roleId;
    private String authority;

    public Roles(){
        super();
    }

    public Roles (Integer roleId , String authority){
        this.roleId = roleId;
        this.authority = authority;
    }


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
