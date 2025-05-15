// Directory: com.live.ris

// 1. Entity Class
package com.live.ris.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_detail")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long pid;

    @Column(name = "p_name", nullable = false)
    private String pName;

    @Column(name = "p_dob")
    private LocalDate dob;

    @Column(name = "p_sex")
    private String sex;

    @Column(name = "p_address")
    private String address;

    @Column(name = "p_city")
    private String city;

    @Column(name = "p_pincode")
    private String pincode;

    @Column(name = "p_telephone")
    private String telephone;

    @Column(name = "p_bloodtype")
    private String bloodType;

    @Column(name = "p_guardiantype")
    private String guardianType;

    @Column(name = "p_guardian_name")
    private String guardianName;

    @Column(name = "p_insurance")
    private String insurance;

    @Column(name = "p_insurance_no")
    private String insuranceNo;

    @Column(name = "p_entry_datetime")
    private LocalDateTime entryDatetime;

    @Column(name = "p_active")
    private Integer active;

    @Column(name = "mobile_verified")
    private String mobileVerified;

    @Column(name = "p_ins_category")
    private String insCategory;

    @Column(name = "encrypted_aadhaar_no")
    private String aadhaar;

    // Getters and Setters
    public Long getPid() { return pid; }
    public void setPid(Long pid) { this.pid = pid; }

    public String getPName() { return pName; }
    public void setPName(String pName) { this.pName = pName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getGuardianType() { return guardianType; }
    public void setGuardianType(String guardianType) { this.guardianType = guardianType; }

    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }

    public String getInsurance() { return insurance; }
    public void setInsurance(String insurance) { this.insurance = insurance; }

    public String getInsuranceNo() { return insuranceNo; }
    public void setInsuranceNo(String insuranceNo) { this.insuranceNo = insuranceNo; }

    public LocalDateTime getEntryDatetime() { return entryDatetime; }
    public void setEntryDatetime(LocalDateTime entryDatetime) { this.entryDatetime = entryDatetime; }

    public Integer getActive() { return active; }
    public void setActive(Integer active) { this.active = active; }

    public String getMobileVerified() { return mobileVerified; }
    public void setMobileVerified(String mobileVerified) { this.mobileVerified = mobileVerified; }

    public String getInsCategory() { return insCategory; }
    public void setInsCategory(String insCategory) { this.insCategory = insCategory; }

    public String getAadhaar() { return aadhaar; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }
}