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
public class Article implements Comparable<Article> {
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

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "parent_id")
//    private Article parent;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "post_id")
    private Integer postId;

    @Column (name = "is_enabled")
    private boolean isEnabled;

    public Article(User user, String title, String tag, String text, Date createdDate, boolean isEnabled){
        this.authorId = user;
        this.title=title;
        this.tag = tag;
        this.text=text;
        this.createdDate = createdDate;
        this.isEnabled = isEnabled;
    }

    //Constructor for Updates
//    public Article(User editorUser, String title, String tag, String text, Date editedDate, boolean isEnabled) {
//        this.editorId = editorUser;
//        this.title = title;
//        this.tag = tag;
//        this.text = text;
//        this.editedDate = editedDate;
//        this.isEnabled = isEnabled;
//    }

    //Constructor for Comments
    public Article(User user, String text, String tag, Date createdDate, Integer postId, Integer parentId, boolean isEnabled) {
        this.authorId = user;
        this.text = text;
        this.tag = tag;
        this.createdDate = createdDate;
        this.postId = postId;
        this.parentId = parentId;
        this.isEnabled = true;
    }

        // Constructor for Test
        public Article(String text, String title, String tag) {
        this.text = text;
        this.title = title;
        this.tag = tag;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

//    public void setParent(Article parent) {
//        this.parent = parent;
//        this.parentId = (parent != null) ? parent.getParentId() : null;
//    }


    public Integer getParentId() {
        return parentId;
    }

    @Override
    public int compareTo(Article o) {
        return this.getId()-o.getId();
    }
}
