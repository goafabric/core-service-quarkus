package org.goafabric.core.data.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.goafabric.core.data.controller.vo.Organization;
import org.goafabric.core.data.logic.OrganizationLogic;

import java.util.List;

@Path("/organizations")
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationController {
    private final OrganizationLogic logic;

    public OrganizationController(OrganizationLogic logic) {
        this.logic = logic;
    }

    @DELETE
    @Path("deleteById/{id}")
    public void deleteById(@PathParam("id") String id) {
        logic.deleteById(id);
    }

    @GET
    @Path("getById/{id}")
    public Organization getById(@PathParam("id") String id) {
        return logic.getById(id);
    }

    @GET
    @Path("findByName")
    public List<Organization> findByName(@QueryParam("name") String name) {
        return logic.findByName(name);
    }


    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Organization save(@Valid Organization organization) {
        return logic.save(organization);
    }

}
