package library.springboot.onlinelibraryspring.models;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "book")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@DynamicUpdate
@DynamicInsert
//@SelectBeforeUpdate
public class Book {
    public Book() {
    }

    public Book(Long id, String name, Integer pageCount, String isbn, Genre genre, Author author, Publisher publisher, Integer publishYear, byte[] image, String descr, long viewCount, long totalRating, long totalVoteCount, int avgRating) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.image = image;
        this.descr = descr;
        this.viewCount = viewCount;
        this.totalRating = totalRating;
        this.totalVoteCount=totalVoteCount;
        this.avgRating = avgRating;
    }

    public Book(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false)
    private String name;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", updatable = false)// updatable = false: при апдейте это поле не будет добавляться (content будем обновлять отдельным запросом)
    private byte[] content;

    @Basic(optional = false)
    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Basic(optional = false)
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @Basic(optional = false)
    @Column(name = "publish_year", nullable = false)
    private Integer publishYear;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image")
    private byte[] image;

    @Basic
    @Column(name = "descr")
    private String descr;

    @Basic
    @Column(name = "view_count")
    private long viewCount;

    @Basic
    @Column(name = "total_rating")
    private long totalRating;

    @Basic
    @Column(name = "total_vote_count")
    private long totalVoteCount;

    @Basic
    @Column(name = "avg_rating")
    private int avgRating;

    @Override
    public String toString() {
        return name;
    }
}
