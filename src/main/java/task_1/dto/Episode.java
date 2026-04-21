package task_1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    public int id;
    public String name;
    public String episode;
    public List<String> characters;
}