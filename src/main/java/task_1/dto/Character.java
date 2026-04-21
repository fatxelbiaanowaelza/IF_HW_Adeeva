package task_1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    public int id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public Location origin;
    public Location location;
    public List<String> episode;
}