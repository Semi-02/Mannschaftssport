package de.hsos.swa.mannschaftssport.al;

import de.hsos.swa.mannschaftssport.acl.TeamDTO;

import java.util.List;

public interface TeamCRUD {
    List<TeamDTO> getTeams();
    TeamDTO saveTeam(TeamDTO team);
}
