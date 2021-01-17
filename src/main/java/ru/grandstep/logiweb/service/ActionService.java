package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.repository.ActionRepository;

@Service
@RequiredArgsConstructor
public class ActionService {
    private final ActionRepository actionRepository;

    public Action getByCargoId(Integer id){
        return actionRepository.getByCargoId(id);
    }
}
