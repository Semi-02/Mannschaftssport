package de.hsos.swa.mannschaftssport.al;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;

import java.util.List;

public interface TeamCRUD {
    List<TeamDTO> getTeams();
    TeamDTO saveTeam(TeamDTO team);

    TeamDTO findTeamById(Long id);

    TeamDTO updateTeamName(Long id, String name);

    TeamDTO updateTeamCategory(Long id, String category);

    boolean deleteTeamById(Long id);
}
