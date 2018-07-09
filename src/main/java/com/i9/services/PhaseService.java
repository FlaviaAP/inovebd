package com.i9.services;

import com.i9.models.Phase;

import java.util.List;

public interface PhaseService {

    Phase getPhase(int id);

    List<Phase> getPhaseByProject(int projectId);
}
