package org.goafabric.core.data.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.goafabric.core.data.controller.vo.Practitioner;
import org.goafabric.core.data.logic.PractitionerLogic;

import java.util.List;

@Path("/practitioners")
@Produces(MediaType.APPLICATION_JSON)
public class PractitionerController {
    private final PractitionerLogic logic;

    public PractitionerController(PractitionerLogic logic) {
        this.logic = logic;
    }

    @DELETE
    @Path("deleteById/{id}")
    public void deleteById(@PathParam("id") String id) {
        logic.deleteById(id);
    }

    @GET
    @Path("getById/{id}")
    public Practitioner getById(@PathParam("id") String id) {
        return logic.getById(id);
    }

    @GET
    @Path("findByGivenName")
    public List<Practitioner> findByGivenName(@QueryParam("givenName") String givenName) {
        return logic.findByGivenName(givenName);
    }

    @GET
    @Path("findByFamilyName")
    public List<Practitioner> findByFamilyName(@QueryParam("familyName") String familyName) {
        return logic.findByFamilyName(familyName);
    }
    
    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Practitioner save(@Valid Practitioner patient) {
        return logic.save(patient);
    }
    
}
