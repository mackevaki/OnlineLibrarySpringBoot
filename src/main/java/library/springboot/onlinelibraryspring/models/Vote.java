package library.springboot.onlinelibraryspring.models;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "vote")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@DynamicUpdate
@DynamicInsert
//@SelectBeforeUpdate
public class Vote {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic
    @Column(name = "value")
    private String value;

    @Basic//(optional = false)
    @Column(name = "book_id") //, nullable = false
    private Long bookId;

    @Basic(optional = false)
    @Column(name = "username", nullable = false)
    private String username;
}
