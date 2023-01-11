package blog.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "tag")
    private String tag;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "created_date")
    private Date created_date;

    @Column(name = "edited_date")
    private String edited_date;

    @Column(name = "deleted_date")
    private String deleted_date;

    @Column(name = "author_id")
    private int author_id;

    @Column(name = "editor_id")
    private int editor_id;

    @Column(name = "parent_id")
    private int parent_id;

    public Article () {

    }

    public Article(String text, String tag, Date created_date){
        this.text=text;
        this.tag = tag;
        this.created_date=created_date;
    }

    public Article(int id, String text, String tag, Date created_date, String edited_date, String deleted_date, int editor_id, int parent_id) {
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.created_date = created_date;
        this.edited_date = edited_date;
        this.deleted_date = deleted_date;
        this.editor_id = editor_id;
        this.parent_id = parent_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getEdited_date() {
        return edited_date;
    }

    public void setEdited_date(String edited_date) {
        this.edited_date = edited_date;
    }

    public String getDeleted_date() {
        return deleted_date;
    }

    public void setDeleted_date(String deleted_date) {
        this.deleted_date = deleted_date;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(int editor_id) {
        this.editor_id = editor_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", created_date='" + created_date + '\'' +
                ", edited_date='" + edited_date + '\'' +
                ", deleted_date='" + deleted_date + '\'' +
                ", author_id=" + author_id +
                ", editor_id=" + editor_id +
                ", parent_id=" + parent_id +
                '}';
    }
}
