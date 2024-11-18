package de.hsos.swa.mannschaftssport.al;

import de.hsos.swa.mannschaftssport.bl.Team;
import jakarta.inject.Inject;

public class TeamController implements TeamCRUD{

    @Inject
    TeamConverter converter;

}
