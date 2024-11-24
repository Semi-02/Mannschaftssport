package de.hsos.swa.mannschaftssport.boundary;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;
import de.hsos.swa.mannschaftssport.al.TeamCRUD;
import de.hsos.swa.mannschaftssport.logging.Log;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log(Log.Level.INFO)
public class TeamIdResource {

    @Inject
    TeamCRUD teamCRUD;

    @GET
    @Path("/{id}")
    @Operation(summary = "get a team", description = "get a team by id")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200", description = "Successfully get a team specified by id",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TeamDTO.class))
                    )
            }
    )
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
    @Operation(summary = "update a team's category", description = "update a team's category")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200", description = "Successfully update a team's category",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TeamDTO.class))
                    )
            }
    )
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
    @Operation(summary = "delete team", description = "delete a team specified by id")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200", description = "Successfully delete an existing team",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = TeamDTO.class))
                    ),
                    @APIResponse(
                            responseCode = "404", description = "Could not find a team with the given id",
                            content = @Content(mediaType = MediaType.TEXT_PLAIN)
                    )
            }
    )
    public Response deleteTeam(@PathParam("id") Long id) {
        boolean deleted = teamCRUD.deleteTeamById(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}