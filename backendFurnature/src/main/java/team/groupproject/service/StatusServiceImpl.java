package team.groupproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Status;
import team.groupproject.repository.StatusRepo;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepo statusRepo;

    @Override
    public Status findByName(String name) {
        return statusRepo.findByName(name);
    }

}
