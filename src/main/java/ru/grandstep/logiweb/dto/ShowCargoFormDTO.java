package ru.grandstep.logiweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ShowCargoFormDTO {
    private Integer id;
    private String name;
    private String number;
    private String weight;
    private Integer waypointId;
    private List<WaypointDTO> waypointDtoList;

    @Data
    @AllArgsConstructor
    public static class WaypointDTO{
        Integer id;
        String name;
    }
}
