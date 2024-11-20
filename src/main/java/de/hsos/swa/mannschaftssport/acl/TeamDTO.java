package de.hsos.swa.mannschaftssport.acl;

public class TeamDTO {
    private long id;
    private String type;
    private Attributes attributes;

    public TeamDTO() {
    }

    public TeamDTO(String name, String category) {
        this.id = 0;
        this.type = "teams";
        this.attributes = new Attributes(name, category);
    }

    public TeamDTO(long id, String type, Attributes attributes) {
        this.id = id;
        this.type = type;
        this.attributes = attributes;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TeamDTO teamDTO = (TeamDTO) obj;
        return id == teamDTO.id;
    }

    public static class Attributes {
        private String name;
        private String category;

        public Attributes() {
        }

        public Attributes(String name, String category) {
            this.name = name;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String toString() {
            return "Attributes{" +
                    "name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }

        public int hashCode() {
            return name.hashCode() + category.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Attributes attributes = (Attributes) obj;
            return name.equals(attributes.name) && category.equals(attributes.category);
        }
    }
}
