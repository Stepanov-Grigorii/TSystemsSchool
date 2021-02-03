package ru.grandstep.logiweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.repository.ActionRepository;

@Service
@RequiredArgsConstructor
public class ActionService {
    private final ActionRepository actionRepository;

    public Action getById(Integer id) throws NotFoundException {
        return actionRepository.getById(id);
    }

    public Action getByCargoId(Integer id) {
        return actionRepository.getByCargoId(id);
    }

    public Action saveOrUpdate(Action action) {
        return actionRepository.saveOrUpdate(action);
    }

    public Action getByCargoIdAndType(Integer cargoId, Action.Type type) {
        return actionRepository.getByCargoIdAndType(cargoId, type);
    }

    public void delete(Integer id) {
        actionRepository.delete(id);
    }
}
