package com.i9.services.impls;

import com.i9.daos.PhaseDao;
import com.i9.models.Phase;
import com.i9.services.PhaseService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class PhaseServiceImp implements PhaseService {

    private PhaseDao phaseDao;

    @Override
    public Phase getPhase(int id) {
        return phaseDao.getPhase(id);
    }

    @Override
    public List<Phase> getPhaseByProject(int projectId) {
        return phaseDao.getPhaseByProject(projectId);
    }

    @Required
    public void setPhaseDao(PhaseDao phaseDao) { this.phaseDao = phaseDao; }
}
