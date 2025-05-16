package com.live.ris.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "investigation_master")
public class InvestigationMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inv_id")
    private Integer invId;

    @Column(name = "inv_code", unique = true)
    private String invCode;

    @Column(name = "inv_cat", nullable = false)
    private String invCat;

    @Column(name = "inv_name", nullable = false)
    private String invName;

    @Column(name = "inv_lab")
    private String invLab;

    @Column(name = "inv_room")
    private String invRoom;

    @Column(name = "inv_doc")
    private String invDoc;

    @Column(name = "inv_charge", nullable = false)
    private String invCharge;

    @Column(name = "inv_text1")
    private String invText1;

    @Column(name = "inv_text2")
    private String invText2;

    // Getters and Setters

    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvCat() {
        return invCat;
    }

    public void setInvCat(String invCat) {
        this.invCat = invCat;
    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;
    }

    public String getInvLab() {
        return invLab;
    }

    public void setInvLab(String invLab) {
        this.invLab = invLab;
    }

    public String getInvRoom() {
        return invRoom;
    }

    public void setInvRoom(String invRoom) {
        this.invRoom = invRoom;
    }

    public String getInvDoc() {
        return invDoc;
    }

    public void setInvDoc(String invDoc) {
        this.invDoc = invDoc;
    }

    public String getInvCharge() {
        return invCharge;
    }

    public void setInvCharge(String invCharge) {
        this.invCharge = invCharge;
    }

    public String getInvText1() {
        return invText1;
    }

    public void setInvText1(String invText1) {
        this.invText1 = invText1;
    }

    public String getInvText2() {
        return invText2;
    }

    public void setInvText2(String invText2) {
        this.invText2 = invText2;
    }

    @PrePersist
    public void prePersist() {
        if (this.invCode == null || this.invCode.isEmpty()) {
            this.invCode = "INV" + System.currentTimeMillis(); // Default value strategy
        }
    }
}
