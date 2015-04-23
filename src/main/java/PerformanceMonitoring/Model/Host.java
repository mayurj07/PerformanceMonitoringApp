package PerformanceMonitoring.Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@JsonAutoDetect
public class Host {

    @Id
    private long id;

    @JsonProperty
    private String name;

    private ArrayList<String> vmlist=new ArrayList<String>();

    public Host() {
    }

    public Host(@JsonProperty("name") String name)
    {
        this.name = name;
    }

    public Host(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }


}

