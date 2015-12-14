/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.table;

import data.Users;
import java.io.StringReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tbecker
 */
@javax.ejb.Stateless
@Path("data.table")
public class UsersFacadeREST extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "ProjectPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
        em = Persistence.createEntityManagerFactory("ProjectPU").createEntityManager();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String start(String content){
        String uid, name;
        JsonReader reader;
        int topics;
        try{
            //read the posted data
            reader = Json.createReader(new StringReader(content));
            JsonObject json = reader.readObject();
            reader.close();
            uid = json.getString("uid");
            name = json.getString("name");
            topics = json.getInt("topics");
            
        } catch(Exception e){
            uid = "chunk";
            name = "track";
            topics = 0;
        }
        create(new Users(uid,name, topics));
        Users u = super.find(uid);
        return u.toString();
    }
    
    @POST
    @Path("signup")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String signup(String content){
        String uid, name;
        JsonReader reader;
        int topics;
        try{
            //read the posted data
            reader = Json.createReader(new StringReader(content));
            JsonObject json = reader.readObject();
            reader.close();
            uid = json.getString("uid");
            name = json.getString("name");
            topics = json.getInt("topics");
            
        } catch(Exception e){
            return "{'signup':0}";
        }
        
        Users u = super.find(uid);
        if (u != null)
            return "{'signup':0}";
        
        create(new Users(uid,name, topics));
        
        return "{'signup':1}";
    }
    
    @POST
    @Path("login")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String login(String content){
        String uid, name;
        JsonReader reader;

        try{
            //read the posted data
            reader = Json.createReader(new StringReader(content));
            JsonObject json = reader.readObject();
            reader.close();
            uid = json.getString("uid");
            name = json.getString("name");
            
        } catch(Exception e){
            return "{'login':0}";
        }
        
        Users u = find(uid);
        if(u != null && u.getName().equals(Hasher.getSaltedHash(uid, name))) 
            return "{'login':1}";
        
        return "{'login':0}";
    }
    
    @Override
    public void create(Users entity){
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") String id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Users find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}