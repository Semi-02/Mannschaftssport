package de.hsos.swa.mannschaftssport.boundary;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;
import de.hsos.swa.mannschaftssport.al.TeamCRUD;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamIdResource {

    @Inject
    TeamCRUD teamCRUD;

    @GET
    @Path("/{id}")
    public Response getTeam(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        TeamDTO team = teamCRUD.findTeamById(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        String selfLink = uriInfo.getAbsolutePathBuilder().build().toString();

        JsonObject responseJson = Json.createObjectBuilder()
                .add("data", Json.createObjectBuilder()
                        .add("type", "teams")
                        .add("id", ((Long)team.getId()).toString())
                        .add("attributes", Json.createObjectBuilder()
                                .add("name", team.getAttributes().getName())
                                .add("category", team.getAttributes().getCategory()))
                        .add("links", Json.createObjectBuilder()
                                .add("self", selfLink)))
                .build();

        return Response.ok(responseJson).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateTeamCategory(@PathParam("id") Long id, JsonObject jsonObject, @Context UriInfo uriInfo) {
        TeamDTO team = teamCRUD.findTeamById(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        JsonObject attributes = jsonObject.getJsonObject("data").getJsonObject("attributes");
        if (attributes.containsKey("name")) {
            team = teamCRUD.updateTeamName(id, attributes.getString("name"));
        }
        if (attributes.containsKey("category")) {
            team = teamCRUD.updateTeamCategory(id, attributes.getString("category"));
        }

        // Construct the self link
        String selfLink = uriInfo.getAbsolutePathBuilder().build().toString();

        // Create the response JSON
        JsonObject responseJson = Json.createObjectBuilder()
                .add("data", Json.createObjectBuilder()
                        .add("type", "teams")
                        .add("id", ((Long)team.getId()).toString())
                        .add("attributes", Json.createObjectBuilder()
                                .add("name", team.getAttributes().getName())
                                .add("category", team.getAttributes().getCategory()))
                        .add("links", Json.createObjectBuilder()
                                .add("self", selfLink)))
                .build();

        return Response.ok(responseJson).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTeam(@PathParam("id") Long id) {
        boolean deleted = teamCRUD.deleteTeamById(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}