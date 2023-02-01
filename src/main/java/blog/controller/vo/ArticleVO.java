package blog.controller.vo;

import blog.domain.Article;
import blog.domain.User;

import java.util.Date;

public class ArticleVO {

    private int id;

    private String text;

    private String title;
    private String tag;

    private Date created_date;

    private Date edited_date;

    private User author_id;

    private User editor_id;

    private Article parent_id;

    public ArticleVO(int id, String text, String title, String tag, Date created_date, Date edited_date, User author_id, User editor_id, Article parent_id) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.tag = tag;
        this.created_date = created_date;
        this.edited_date = edited_date;
        this.author_id = author_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getEdited_date() {
        return edited_date;
    }

    public void setEdited_date(Date edited_date) {
        this.edited_date = edited_date;
    }

    public User getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(User author_id) {
        this.author_id = author_id;
    }

    public User getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(User editor_id) {
        this.editor_id = editor_id;
    }

    public Article getParent_id() {
        return parent_id;
    }

    public void setParent_id(Article parent_id) {
        this.parent_id = parent_id;
    }

    public static ArticleVO valueOf(Article article) {
        return new ArticleVO(article.getId(), article.getText(), article.getTitle(), article.getTag(), article.getCreatedDate(), article.getEditedDate(),
                article.getAuthorId(), article.getEditorId(), article.getParent_id());
    }
}
