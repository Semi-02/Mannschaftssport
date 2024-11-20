package de.hsos.swa.mannschaftssport.al;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;
import de.hsos.swa.mannschaftssport.bl.Team;
import de.hsos.swa.mannschaftssport.bl.TeamRegistry;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.List;

@Dependent
public class TeamController implements TeamCRUD{

    @Inject
    TeamConverter converter;

    @Inject
    TeamRegistry registry;

    @Override
    public List<TeamDTO> getTeams() {
        List<Team> teams = registry.getTeams();
        return converter.toDTOs(teams);
    }

    @Override
    public TeamDTO saveTeam(TeamDTO team) {
        Team teamEntity = converter.toEntity(team);
        teamEntity = registry.saveTeam(teamEntity);
        return converter.toDTO(teamEntity);}

    @Override
    public TeamDTO findTeamById(Long id) {
        Team teamEntity = registry.findTeamById(id);
        return converter.toDTO(teamEntity);
    }

    @Override
    public TeamDTO updateTeamName(Long id, String name) {
        Team teamEntity = registry.updateTeamName(id, name);
        return converter.toDTO(teamEntity);
    }

    @Override
    public TeamDTO updateTeamCategory(Long id, String category) {
        Team teamEntity = registry.updateTeamCategory(id, category);
        return converter.toDTO(teamEntity);
    }

    @Override
    public boolean deleteTeamById(Long id) {
        return registry.deleteTeamById(id);
    }

}
