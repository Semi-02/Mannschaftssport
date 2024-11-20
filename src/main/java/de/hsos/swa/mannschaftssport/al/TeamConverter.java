package de.hsos.swa.mannschaftssport.al;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;
import de.hsos.swa.mannschaftssport.bl.Category;
import de.hsos.swa.mannschaftssport.bl.Team;
import jakarta.enterprise.context.Dependent;

import java.util.List;

@Dependent
public class TeamConverter {

    public TeamDTO toDTO(Team team) {
        long id = team.getId();
        String name = team.getName();
        String category = team.getCategory().name();
        TeamDTO.Attributes attributes = new TeamDTO.Attributes(name, category);
        return new TeamDTO(id, "team", attributes);
    }

    public Team toEntity(TeamDTO teamDTO) {
        long id = teamDTO.getId();
        String name = teamDTO.getAttributes().getName();
        Category category = Category.valueOf(teamDTO.getAttributes().getCategory());
        return new Team(id, name, category);
    }

    public List<TeamDTO> toDTOs(List<Team> teams) {
        return teams.stream().map(this::toDTO).toList();
    }
}
