package crm.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long interviewseekerid;
    private String interviewdate;

    @ElementCollection(targetClass = InterviewStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "seeker_status", joinColumns = @JoinColumn(name = "seeker_id"))
    @Enumerated(EnumType.STRING)
    private Set<InterviewStatus> interviewStatuses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInterviewseekerid() {
        return interviewseekerid;
    }

    public void setInterviewseekerid(long interviewseekerid) {
        this.interviewseekerid = interviewseekerid;
    }

    public String getInterviewdate() {
        return interviewdate;
    }

    public void setInterviewdate(String interviewdate) {
        this.interviewdate = interviewdate;
    }

    public Set<InterviewStatus> getInterviewStatuses() {
        return interviewStatuses;
    }

    public void setInterviewStatuses(Set<InterviewStatus> interviewStatuses) {
        this.interviewStatuses = interviewStatuses;
    }
}
