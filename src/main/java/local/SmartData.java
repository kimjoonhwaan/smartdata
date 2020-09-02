package local;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SmartData_table")
public class SmartData {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long screeningId;
        private String custNm;
        private String hospitalNm;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getScreeningId() {
            return screeningId;
        }

        public void setScreeningId(Long screeningId) {
            this.screeningId = screeningId;
        }
        public String getCustNm() {
            return custNm;
        }

        public void setCustNm(String custNm) {
            this.custNm = custNm;
        }
        public String getHospitalNm() {
            return hospitalNm;
        }

        public void setHospitalNm(String hospitalNm) {
            this.hospitalNm = hospitalNm;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

}
