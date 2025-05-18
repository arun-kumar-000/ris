package com.live.ris.dto;

import java.util.List;

public class InvestigationEntryRequest {
    private String pid;
    private String pname;
    private String doctorName;
    private String age;           // NEW
    private String paymentMode;   // NEW
    private List<InvestigationItem> investigations;

    public static class InvestigationItem {
        private String invId;
        private String invName;
        private String charges;

        public String getInvId() { return invId; }
        public void setInvId(String invId) { this.invId = invId; }

        public String getInvName() { return invName; }
        public void setInvName(String invName) { this.invName = invName; }

        public String getCharges() { return charges; }
        public void setCharges(String charges) { this.charges = charges; }
    }

    public String getPid() { return pid; }
    public void setPid(String pid) { this.pid = pid; }

    public String getPname() { return pname; }
    public void setPname(String pname) { this.pname = pname; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getAge() { return age; }                       // NEW
    public void setAge(String age) { this.age = age; }           // NEW

    public String getPaymentMode() { return paymentMode; }       // NEW
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; } // NEW

    public List<InvestigationItem> getInvestigations() { return investigations; }
    public void setInvestigations(List<InvestigationItem> investigations) { this.investigations = investigations; }
}
