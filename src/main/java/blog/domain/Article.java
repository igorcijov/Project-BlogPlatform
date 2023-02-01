package blog.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="articles", schema = "public")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "title")
    private String title;

    @Column(name = "tag")
    private String tag;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "edited_date")
    private Date editedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User authorId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editor_id")
    private User editorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Article parent_id;

    @Column(name = "parent_id", updatable = false, insertable = false)
    private Integer parentId;

    @Column (name = "is_enabled")
    private boolean isEnabled;

    public Article(User user, String text, String title, String tag, Date createdDate, boolean isEnabled){
        this.authorId = user;
        this.text=text;
        this.title=title;
        this.tag = tag;
        this.createdDate =createdDate;
        this.isEnabled = isEnabled;

    }

    public Article(String text, String title, String tag, Date editedDate, User editorUser, boolean isEnabled) {
        this.text = text;
        this.title = title;
        this.tag = tag;
        this.editedDate = editedDate;
        this.editorId = editorUser;
        this.isEnabled = isEnabled;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
