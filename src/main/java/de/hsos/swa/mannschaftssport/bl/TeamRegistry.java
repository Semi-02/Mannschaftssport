package de.hsos.swa.mannschaftssport.bl;

import java.util.List;

public interface TeamRegistry {
    List<Team> getTeams();
    Team saveTeam(Team teamEntity);
}
