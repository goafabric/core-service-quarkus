package org.goafabric.core.data.controller;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.goafabric.core.data.controller.vo.Patient;
import org.goafabric.core.data.logic.PatientLogic;

import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
public class PatientController {
    private final PatientLogic logic;

    public PatientController(PatientLogic logic) {
        this.logic = logic;
    }

    @DELETE
    @Path("deleteById/{id}")
    public void deleteById(@PathParam("id") String id) {
        logic.deleteById(id);
    }

    @GET
    @Path("getById/{id}")
    public Patient getById(@PathParam("id") String id) {
        return logic.getById(id);
    }

    @GET
    @Path("findByGivenName")
    public List<Patient> findByGivenName(@QueryParam("givenName") String givenName) {
        return logic.findByGivenName(givenName);
    }

    @GET
    @Path("findByFamilyName")
    public List<Patient> findByFamilyName(@QueryParam("familyName") String familyName) {
        return logic.findByFamilyName(familyName);
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public Patient save(@Valid Patient patient) {
        return logic.save(patient);
    }

}
