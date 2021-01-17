package ru.grandstep.logiweb.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ShowOrderFormDTO {
    private Integer id;
    private String number;
    private Integer wagonId;
    private List<WagonDTO> wagonDtoList;
    private Integer cargoId;
    private List<CargoDTO> cargoDtoList;
    private Integer waypointId;
    private List<WaypointDTO> waypointDtoList;

    @Data
    @AllArgsConstructor
    public static class WagonDTO{
        private Integer id;
        private String registryNumber;
    }

    @Data
    @AllArgsConstructor
    public static class CargoDTO {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class WaypointDTO{
        private Integer id;
        private String name;
    }
}
