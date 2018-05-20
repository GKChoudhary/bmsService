package com.rank.ccms.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "employee_call_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeCallStatus.findAll", query = "SELECT e FROM EmployeeCallStatus e")})
public class EmployeeCallStatus implements Serializable,Comparator<EmployeeCallStatus> {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean status;
    private int callCount;
    private Timestamp endTime;
    private EmployeeMst empId;
    private Date readyTime;

    public EmployeeCallStatus() {
    }

    public EmployeeCallStatus(Long id) {
        this.id = id;
    }

    public EmployeeCallStatus(Long id, int callCount, Timestamp endTime) {
        this.id = id;
        this.callCount = callCount;
        this.endTime = endTime;
    }

    @Id
    @SequenceGenerator(name = "employeeCallStatusSeq", sequenceName = "EMPLOYEE_CALL_STATUS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "employeeCallStatusSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "READY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Date readyTime) {
        this.readyTime = readyTime;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic(optional = false)
    @Column(name = "call_count")
    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    @Basic(optional = true)
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EmployeeMst getEmpId() {
        return empId;
    }

    public void setEmpId(EmployeeMst empId) {
        this.empId = empId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeCallStatus)) {
            return false;
        }
        EmployeeCallStatus other = (EmployeeCallStatus) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.futuregenerali.entities.EmployeeCallStatus[ id=" + id + " ]";
    }

    @Override
    public int compare(EmployeeCallStatus o1, EmployeeCallStatus o2) {
        int t = 0;

        if (o1.getReadyTime().after(o2.getReadyTime())) {
            t = 1;
        }
        if (t == 1) {
            t = 0;
        } else {
            if (o1.getReadyTime().before(o2.getReadyTime())) {
                t = -1;
            } else {
                long difference = o1.getReadyTime().getTime() - o2.getReadyTime().getTime();

                if (o1.getReadyTime().getTime() > o2.getReadyTime().getTime()) {
                    t = 1;
                } else {
                    if (o1.getReadyTime().getTime() < o2.getReadyTime().getTime()) {
                        t = -1;
                    } else {
                        t = 0;
                    }
                }

            }
        }

        return t;
    }

}
