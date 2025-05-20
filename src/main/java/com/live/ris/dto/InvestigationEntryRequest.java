package com.live.ris.dto;

import java.util.List;

public class InvestigationEntryRequest {
    private String pid;
    private String pname;
    private String age;
    private String paymentMode;
    private String invDoc;   // NEW
    private String ins;   // NEW
    private List<InvestigationItem> investigations;

    public static class InvestigationItem {
        private String invId;
        private String invName;
        private String charges;
        private String invRoom;  // NEW
        private String invCat;   // NEW
        private String invDoc;   // NEW

        public String getInvId() { return invId; }
        public void setInvId(String invId) { this.invId = invId; }

        public String getInvName() { return invName; }
        public void setInvName(String invName) { this.invName = invName; }

        public String getCharges() { return charges; }
        public void setCharges(String charges) { this.charges = charges; }

        public String getInvRoom() { return invRoom; }
        public void setInvRoom(String invRoom) { this.invRoom = invRoom; }

        public String getInvCat() { return invCat; }
        public void setInvCat(String invCat) { this.invCat = invCat; }

        public String getInvDoc() { return invDoc; }
        public void setInvDoc(String invDoc) { this.invDoc = invDoc; }
    }

    public String getPid() { return pid; }
    public void setPid(String pid) { this.pid = pid; }
    
    public String getIns() { return ins; }
    public void setIns(String ins) { this.ins = ins; }

    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getInvDoc() { return invDoc; }
    public void setInvDoc(String invDoc) { this.invDoc = invDoc; }

    
    public List<InvestigationItem> getInvestigations() { return investigations; }
    public void setInvestigations(List<InvestigationItem> investigations) { this.investigations = investigations; }
}
