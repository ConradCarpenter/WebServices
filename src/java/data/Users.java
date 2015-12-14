/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tbecker
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID"),
    @NamedQuery(name = "Users.findByPassID", query = "SELECT u FROM Users u WHERE u.passID = :passID")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userID")
    private String userID;
    @Column(name = "passID")
    private String passID;

    public Users() {
    }
    
    public Users(String userID, String name) {
        this.userID = userID;
        this.passID = passID;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPass() {
        return passID;
    }

    public void setName(String name) {
        this.passID = passID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("userID",userID);
        builder.add("passID",passID);
        JsonObject json = builder.build();
        return json.toString();
    }
    
    public JsonObject toJSON(){
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("userID",userID);
        builder.add("passID",passID);
        JsonObject json = builder.build();
        return json;
    }
    
}
