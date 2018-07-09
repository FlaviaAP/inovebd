package com.i9.daos;

import com.i9.models.Phase;

import java.util.List;

public interface PhaseDao extends GenericDao<Phase> {
    Phase getPhase(int id);

    List<Phase> getPhaseByProject(int projectId);
}
