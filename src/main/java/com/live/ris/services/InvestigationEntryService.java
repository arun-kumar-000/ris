package com.live.ris.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live.ris.dto.InvestigationEntryRequest;
import com.live.ris.entities.InvestigationBillEntry;
import com.live.ris.entities.InvestigationEntry;
import com.live.ris.repositories.InvestigationBillEntryRepository;
import com.live.ris.repositories.InvestigationEntryRepository;
import com.live.ris.repositories.InvestigationMasterRepository;

import jakarta.transaction.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InvestigationEntryService {

    @Autowired
    private InvestigationEntryRepository repository;
    
    @Autowired
    private InvestigationBillEntryRepository billRepo;
    
    @Autowired
    private InvestigationMasterRepository invMstRepo;

    @Autowired
    private InvestigationEntryRepository entryRepo;

    public List<InvestigationEntry> getAll() {
        return repository.findAll();
    }

    public Optional<InvestigationEntry> getById(int id) {
        return repository.findById(id);
    }

    public InvestigationEntry save(InvestigationEntry entry) {
        return repository.save(entry);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public String saveInvestigationWithBilling(InvestigationEntryRequest request) {
        InvestigationBillEntry bill = new InvestigationBillEntry();
        bill.setpId(request.getPid());
        bill.setpName(request.getPname());
        bill.setRefDoctor(request.getInvDoc());
        bill.setInvTotalCharges(
                request.getInvestigations().stream()
                        .mapToDouble(i -> Double.parseDouble(i.getCharges()))
                        .sum() + "");
        bill.setInvChargesPaid(bill.getInvTotalCharges());
        bill.setInvDateTime(new Timestamp(System.currentTimeMillis()));
        bill.setInvBookingStatus("Pending");
        bill.setpAge(request.getAge());
        bill.setpType("OPD");
        bill.setInvPaymentMode(request.getPaymentMode());
        bill.setRefDoctor("NA");
        bill.setpInsurance(request.getIns());
        bill.setInvEntryUser("system");

        billRepo.save(bill);

        String receiptIdStr = bill.getInvReceiptId()+"";
        bill.setInvReceiptIdStr(receiptIdStr);
        billRepo.save(bill);

        for (InvestigationEntryRequest.InvestigationItem item : request.getInvestigations()) {
            InvestigationEntry entry = new InvestigationEntry();
            entry.setInvName(item.getInvName());
            entry.setInvCode(item.getInvId());
            entry.setpId(request.getPid());
            entry.setpName(request.getPname());
            entry.setInvDoctor(item.getInvDoc());
            entry.setInvCharges(item.getCharges());
            entry.setInvCat(item.getInvCat());
            entry.setInvRoom(item.getInvRoom());
            entry.setInvOperator(item.getInvDoc());
            entry.setInvReceiptId(receiptIdStr);
            entry.setInvPerformed("NO");
            entry.setInvDocUploaded("NO");
            entry.setInvDateTime(new Timestamp(System.currentTimeMillis()));

            entryRepo.save(entry);
        }
        return receiptIdStr;
    }
}
