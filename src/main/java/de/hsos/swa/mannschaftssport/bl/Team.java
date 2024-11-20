package de.hsos.swa.mannschaftssport.bl;

import jakarta.inject.Inject;

import java.util.List;

public class Team {

    private long id;
    private String name;
    private Category category;
    private Person coach;
    private List<Person> players;

    public Team() {
    }

    public Team(long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Team(long id, String name, Category category, Person coach, List<Person> players) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.coach = coach;
        this.players = players;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Person getCoach() {
        return coach;
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }

    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", coach=" + coach +
                ", players=" + players +
                '}';
    }

    public int hashCode() {
        return (int) (id + name.hashCode() + category.hashCode() + coach.hashCode() + players.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Team team = (Team) obj;
        return id == team.id;
    }
}
