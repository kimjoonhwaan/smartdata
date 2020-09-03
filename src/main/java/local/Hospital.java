package local;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table(name="Hospital_table")
public class Hospital {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String hospitalId;
    private String hospitalNm;
    private String chkDate;
    private Long pCnt;

    @PostPersist
    public void onPostPersist(){
        HospitalRegistered hospitalRegistered = new HospitalRegistered();
        BeanUtils.copyProperties(this, hospitalRegistered);
        hospitalRegistered.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        HospitalChanged hospitalChanged = new HospitalChanged();
        BeanUtils.copyProperties(this, hospitalChanged);
        hospitalChanged.publishAfterCommit();

        PCntChanged pCntChanged = new PCntChanged();
        BeanUtils.copyProperties(this, pCntChanged);
        pCntChanged.publishAfterCommit();

    }

    @PreRemove
    public void onPreRemove(){
        Deleted deleted = new Deleted();
        BeanUtils.copyProperties(this, deleted);
        deleted.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
    public String getHospitalNm() {
        return hospitalNm;
    }

    public void setHospitalNm(String hospitalNm) {
        this.hospitalNm = hospitalNm;
    }
    public String getChkDate() {
        return chkDate;
    }

    public void setChkDate(String chkDate) {
        this.chkDate = chkDate;
    }
    public Long getPCnt() {
        return pCnt;
    }

    public void setPCnt(Long pCnt) {
        this.pCnt = pCnt;
    }




}
