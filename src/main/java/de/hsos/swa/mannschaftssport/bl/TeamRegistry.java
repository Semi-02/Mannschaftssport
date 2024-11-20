package de.hsos.swa.mannschaftssport.bl;

import java.util.List;

public interface TeamRegistry {

    List<Team> getTeams();

    Team saveTeam(Team teamEntity);

    Team findTeamById(Long id);

    Team updateTeamName(Long id, String name);

    Team updateTeamCategory(Long id, String category);

    boolean deleteTeamById(Long id);
}
