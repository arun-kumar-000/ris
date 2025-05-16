package com.live.ris.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "investigation_entry")
public class InvestigationEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invId;

    private String invName;
    private String invCode;
    private String pType;
    private String pId;
    private String pName;
    private String invDoctor;
    private String invCharges;
    private String invPerformed;
    private String invOperator;
    private String invRoom;
    private String invReportId;
    private String invReportName1;
    private String invReportName2;
    private String invReportName3;
    private String invReportName4;
    private String invResult1;
    private String invResult2;
    private String invResult3;
    private String invResult4;
    private String invResult5;
    private String invNote1;
    private String invNote2;
    private String invCat;
    private String invReceiptId;
    private String invReportUrl;
    private String invDocType;
    private String invDocUploaded;
    private String invComment;

    private Timestamp invDateTime;

    // Getters and Setters
    public int getInvId() { return invId; }
    public void setInvId(int invId) { this.invId = invId; }

    public String getInvName() { return invName; }
    public void setInvName(String invName) { this.invName = invName; }

    public String getInvCode() { return invCode; }
    public void setInvCode(String invCode) { this.invCode = invCode; }

    public String getpType() { return pType; }
    public void setpType(String pType) { this.pType = pType; }

    public String getpId() { return pId; }
    public void setpId(String pId) { this.pId = pId; }

    public String getpName() { return pName; }
    public void setpName(String pName) { this.pName = pName; }

    public String getInvDoctor() { return invDoctor; }
    public void setInvDoctor(String invDoctor) { this.invDoctor = invDoctor; }

    public String getInvCharges() { return invCharges; }
    public void setInvCharges(String invCharges) { this.invCharges = invCharges; }

    public String getInvPerformed() { return invPerformed; }
    public void setInvPerformed(String invPerformed) { this.invPerformed = invPerformed; }

    public String getInvOperator() { return invOperator; }
    public void setInvOperator(String invOperator) { this.invOperator = invOperator; }

    public String getInvRoom() { return invRoom; }
    public void setInvRoom(String invRoom) { this.invRoom = invRoom; }

    public String getInvReportId() { return invReportId; }
    public void setInvReportId(String invReportId) { this.invReportId = invReportId; }

    public String getInvReportName1() { return invReportName1; }
    public void setInvReportName1(String invReportName1) { this.invReportName1 = invReportName1; }

    public String getInvReportName2() { return invReportName2; }
    public void setInvReportName2(String invReportName2) { this.invReportName2 = invReportName2; }

    public String getInvReportName3() { return invReportName3; }
    public void setInvReportName3(String invReportName3) { this.invReportName3 = invReportName3; }

    public String getInvReportName4() { return invReportName4; }
    public void setInvReportName4(String invReportName4) { this.invReportName4 = invReportName4; }

    public String getInvResult1() { return invResult1; }
    public void setInvResult1(String invResult1) { this.invResult1 = invResult1; }

    public String getInvResult2() { return invResult2; }
    public void setInvResult2(String invResult2) { this.invResult2 = invResult2; }

    public String getInvResult3() { return invResult3; }
    public void setInvResult3(String invResult3) { this.invResult3 = invResult3; }

    public String getInvResult4() { return invResult4; }
    public void setInvResult4(String invResult4) { this.invResult4 = invResult4; }

    public String getInvResult5() { return invResult5; }
    public void setInvResult5(String invResult5) { this.invResult5 = invResult5; }

    public String getInvNote1() { return invNote1; }
    public void setInvNote1(String invNote1) { this.invNote1 = invNote1; }

    public String getInvNote2() { return invNote2; }
    public void setInvNote2(String invNote2) { this.invNote2 = invNote2; }

    public String getInvCat() { return invCat; }
    public void setInvCat(String invCat) { this.invCat = invCat; }

    public String getInvReceiptId() { return invReceiptId; }
    public void setInvReceiptId(String invReceiptId) { this.invReceiptId = invReceiptId; }

    public String getInvReportUrl() { return invReportUrl; }
    public void setInvReportUrl(String invReportUrl) { this.invReportUrl = invReportUrl; }

    public String getInvDocType() { return invDocType; }
    public void setInvDocType(String invDocType) { this.invDocType = invDocType; }

    public String getInvDocUploaded() { return invDocUploaded; }
    public void setInvDocUploaded(String invDocUploaded) { this.invDocUploaded = invDocUploaded; }

    public String getInvComment() { return invComment; }
    public void setInvComment(String invComment) { this.invComment = invComment; }

    public Timestamp getInvDateTime() { return invDateTime; }
    public void setInvDateTime(Timestamp invDateTime) { this.invDateTime = invDateTime; }
}
