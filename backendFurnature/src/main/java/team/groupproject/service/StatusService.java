package team.groupproject.service;

import team.groupproject.entity.Status;

public interface StatusService {
	Status findByName(String name);
}
