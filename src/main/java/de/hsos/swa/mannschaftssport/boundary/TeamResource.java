package de.hsos.swa.mannschaftssport.boundary;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;
import de.hsos.swa.mannschaftssport.al.TeamCRUD;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;


import java.net.URI;
import java.util.*;

@Path("/teams")
public class TeamResource {

    @Inject
    TeamCRUD teamCRUD;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeams(@QueryParam("filter[name]") String nameFilter,
                             @QueryParam("filter[category]") String categoryFilter,
                             @QueryParam("page[number]") @DefaultValue("1") int pageNumber,
                             @QueryParam("page[size]") @DefaultValue("10") int pageSize) {
        List<TeamDTO> teams = teamCRUD.getTeams();

        List<TeamDTO> filteredTeams = new ArrayList<>(teams);
        if (nameFilter != null) {
            filteredTeams.removeIf(team -> !team.getAttributes().getName().contains(nameFilter));
        }
        if (categoryFilter != null) {
            Set<String> categories = new HashSet<>(Arrays.asList(categoryFilter.split(",")));
            filteredTeams.removeIf(team -> !categories.contains(team.getAttributes().getCategory()));
        }

        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredTeams.size());
        List<TeamDTO> pagedTeams = filteredTeams.subList(start, end);

        String baseUrl = "http://localhost:8080/teams";
        String first = baseUrl + "?page[number]=1&page[size]=" + pageSize;
        String prev = pageNumber > 1 ? baseUrl + "?page[number]=" + (pageNumber - 1) + "&page[size]=" + pageSize : null;
        String next = end < filteredTeams.size() ? baseUrl + "?page[number]=" + (pageNumber + 1) + "&page[size]=" + pageSize : null;
        String last = baseUrl + "?page[number]=" + ((filteredTeams.size() + pageSize - 1) / pageSize) + "&page[size]=" + pageSize;

        Map<String, Object> response = new HashMap<>();
        response.put("data", pagedTeams);
        Map<String, String> links = new HashMap<>();
        links.put("first", first);
        links.put("prev", prev);
        links.put("next", next);
        links.put("last", last);
        response.put("links", links);

        return Response.ok(response).build();
    }

    @POST
    @Transactional
    public Response createTeam(TeamDTO teamData, @Context UriInfo uriInfo) {
        TeamDTO newTeam = teamCRUD.saveTeam(teamData);

        String selfLink = uriInfo.getAbsolutePathBuilder().path(Long.toString(newTeam.getId())).build().toString();
        JsonObject responseJson = Json.createObjectBuilder()
                .add("data", Json.createObjectBuilder()
                        .add("type", "teams")
                        .add("id", ((Long) newTeam.getId()).toString())
                        .add("attributes", Json.createObjectBuilder()
                                .add("name", newTeam.getAttributes().getName())
                                .add("category", newTeam.getAttributes().getCategory()))
                        .add("links", Json.createObjectBuilder()
                                .add("self", selfLink)))
                .build();

        return Response.created(URI.create(selfLink)).entity(responseJson).build();
    }
}