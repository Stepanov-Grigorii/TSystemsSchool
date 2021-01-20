package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.dto.ShowWagonFormDTO;
import ru.grandstep.logiweb.dto.WagonDTO;
import ru.grandstep.logiweb.mapper.WagonMapper;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.service.CityService;
import ru.grandstep.logiweb.service.WagonService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("wagons")
@RequiredArgsConstructor
public class WagonController {
    private final WagonService wagonService;
    private final WagonMapper wagonMapper;
    private final CityService cityService;

    @GetMapping("/list")
    public ModelAndView listOfWagons(){
        List<WagonDTO> wagonDTOList = wagonService.getAll().stream().map(wagonMapper::getWagonDTO).collect(Collectors.toList());
        return new ModelAndView("admin/wagon/list", "wagons", wagonDTOList);
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editWagon(@PathVariable(required = false) Integer id){
        Wagon wagon = id == null ? new Wagon() : wagonService.getById(id);
        ShowWagonFormDTO dto = wagonMapper.getShowWagonFormDTO(id, wagon, cityService.getAll());
        return new ModelAndView("admin/wagon/form", "wagonDTO", dto);
    }

    @PostMapping("/save")
    public RedirectView saveWagon(@ModelAttribute ShowWagonFormDTO wagonDTO){
        Wagon wagon = wagonMapper.getWagon(wagonDTO);
        wagonService.saveOrUpdate(wagon);
        return new RedirectView("list");
    }

    @DeleteMapping("{id}")
    public void deleteDriver(@PathVariable Integer id){
        wagonService.delete(id);
    }
}
