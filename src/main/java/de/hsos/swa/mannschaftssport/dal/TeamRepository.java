package de.hsos.swa.mannschaftssport.dal;

import de.hsos.swa.mannschaftssport.bl.Category;
import de.hsos.swa.mannschaftssport.bl.Team;
import de.hsos.swa.mannschaftssport.bl.TeamRegistry;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.List;

@Singleton
public class TeamRepository implements TeamRegistry {

    private final HashMap<Long, Team> teams = new HashMap<>();
    static long idCounter = 1;

    public TeamRepository() {
        Team fcNordenJugend = new Team(idCounter++, "FC Norden Jugend", Category.juniors);
        Team fcEssen = new Team(idCounter++, "FC Essen", Category.masters);
        Team fcOldenburg = new Team(idCounter++, "FC Oldenburg", Category.seniors);
        teams.put(fcNordenJugend.getId(), fcNordenJugend);
        teams.put(fcOldenburg.getId(), fcOldenburg);
        teams.put(fcEssen.getId(), fcEssen);
    }
    @Override
    public List<Team> getTeams() {
        return List.copyOf(teams.values());
    }

    @Override
    public Team saveTeam(Team teamEntity) {
        teamEntity.setId(idCounter++);
        teams.put(teamEntity.getId(), teamEntity);
        return teamEntity;
    }

    @Override
    public Team findTeamById(Long id) {
        return teams.get(id);
    }

    @Override
    public Team updateTeamName(Long id, String name) {
        Team team = teams.get(id);
        team.setName(name);
        return team;
    }

    @Override
    public Team updateTeamCategory(Long id, String category) {
        Team team = teams.get(id);
        team.setCategory(Category.valueOf(category));
        return team;
    }

    @Override
    public boolean deleteTeamById(Long id) {
        return teams.remove(id) != null;
    }
}
