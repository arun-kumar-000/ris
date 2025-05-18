package com.live.ris.services;

import com.live.ris.entities.InvestigationMaster;
import com.live.ris.repositories.InvestigationMasterRepository;
import com.live.ris.services.InvestigationMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationMasterServiceImpl implements InvestigationMasterService {

    @Autowired
    private InvestigationMasterRepository repository;

    @Override
    public InvestigationMaster save(InvestigationMaster investigation) {
        return repository.save(investigation);
    }

    @Override
    public List<InvestigationMaster> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<InvestigationMaster> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<InvestigationMaster> search(String keyword) {
        return repository.findByInvNameContainingIgnoreCaseOrInvCodeContainingIgnoreCase(keyword, keyword);
    }

	@Override
	public List<InvestigationMaster> getByLab(String labName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvestigationMaster> searchByKeywordAndLab(String keyword, Long labId) {
		// TODO Auto-generated method stub
		return null;
	}
}
