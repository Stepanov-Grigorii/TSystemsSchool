package ru.grandstep.logiweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.service.WagonService;

@Controller
@RequestMapping("wagons")
@RequiredArgsConstructor
public class WagonController {
    private final WagonService wagonService;

    @GetMapping("/list")
    public ModelAndView listOfWagons(){
        return new ModelAndView("admin/wagon/list", "wagons", wagonService.getAll());
    }

    @GetMapping({"/form", "form/{id}"})
    public ModelAndView editWagon(@PathVariable(required = false) Integer id){
        Wagon wagon = id == null ? new Wagon() : wagonService.getById(id);
        return new ModelAndView("admin/wagon/form", "wagon", wagon);
    }

    @PostMapping("/save")
    public RedirectView saveWagon(@ModelAttribute Wagon wagon){
        wagonService.saveOrUpdate(wagon);
        return new RedirectView("list");
    }

    @DeleteMapping("{id}")
    public void deleteDriver(@PathVariable Integer id){
        wagonService.delete(id);
    }
}
