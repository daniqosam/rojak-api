package id.rojak.analytics.resource.dto.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/21/17.
 */
public class Series<T> {

    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private List<T> data;

    public Series(String name, List<T> data) {
        this.name = name;
        this.data = data;
    }

    public String name() {
        return this.name;
    }

    public List<T> data() {
        return this.data;
    }
}
