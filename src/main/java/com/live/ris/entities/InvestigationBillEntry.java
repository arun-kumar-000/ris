package com.live.ris.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "investigation_bill_entry")
public class InvestigationBillEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invReceiptId;

    private String pType;
    private String pOpdIpdId;
    private String pId;
    private String pAge;
    private String pName;
    private String refDoctor;
    private String invTotalCharges;
    private String invChargesPaid;
    private String invEntryUser;
    private String invReceiptIdStr;
    private String pInsurance;
    private String invPaymentMode;
    private String invBookingStatus;
    private String invReportUrl;

    private Timestamp invDateTime;

    // Getters and Setters
    public int getInvReceiptId() { return invReceiptId; }
    public void setInvReceiptId(int invReceiptId) { this.invReceiptId = invReceiptId; }

    public String getpType() { return pType; }
    public void setpType(String pType) { this.pType = pType; }

    public String getpOpdIpdId() { return pOpdIpdId; }
    public void setpOpdIpdId(String pOpdIpdId) { this.pOpdIpdId = pOpdIpdId; }

    public String getpId() { return pId; }
    public void setpId(String pId) { this.pId = pId; }

    public String getpAge() { return pAge; }
    public void setpAge(String pAge) { this.pAge = pAge; }

    public String getpName() { return pName; }
    public void setpName(String pName) { this.pName = pName; }

    public String getRefDoctor() { return refDoctor; }
    public void setRefDoctor(String refDoctor) { this.refDoctor = refDoctor; }

    public String getInvTotalCharges() { return invTotalCharges; }
    public void setInvTotalCharges(String invTotalCharges) { this.invTotalCharges = invTotalCharges; }

    public String getInvChargesPaid() { return invChargesPaid; }
    public void setInvChargesPaid(String invChargesPaid) { this.invChargesPaid = invChargesPaid; }

    public String getInvEntryUser() { return invEntryUser; }
    public void setInvEntryUser(String invEntryUser) { this.invEntryUser = invEntryUser; }

    public String getInvReceiptIdStr() { return invReceiptIdStr; }
    public void setInvReceiptIdStr(String invReceiptIdStr) { this.invReceiptIdStr = invReceiptIdStr; }

    public String getpInsurance() { return pInsurance; }
    public void setpInsurance(String pInsurance) { this.pInsurance = pInsurance; }

    public String getInvPaymentMode() { return invPaymentMode; }
    public void setInvPaymentMode(String invPaymentMode) { this.invPaymentMode = invPaymentMode; }

    public String getInvBookingStatus() { return invBookingStatus; }
    public void setInvBookingStatus(String invBookingStatus) { this.invBookingStatus = invBookingStatus; }

    public String getInvReportUrl() { return invReportUrl; }
    public void setInvReportUrl(String invReportUrl) { this.invReportUrl = invReportUrl; }

    public Timestamp getInvDateTime() { return invDateTime; }
    public void setInvDateTime(Timestamp invDateTime) { this.invDateTime = invDateTime; }
}
