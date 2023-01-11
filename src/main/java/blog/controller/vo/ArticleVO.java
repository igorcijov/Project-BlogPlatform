package blog.controller.vo;

import blog.domain.Article;

import java.util.Date;

public class ArticleVO {

    private int id;

    private String text;

    private String tag;

    private Date created_date;

    private String edited_date;

    private String deleted_date;

    private int author_id;

    private int editor_id;

    private int parent_id;

    public ArticleVO(int id, String text, String tag, Date created_date, String edited_date, String deleted_date, int author_id, int editor_id, int parent_id) {
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.created_date = created_date;
        this.edited_date = edited_date;
        this.deleted_date = deleted_date;
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

    public static ArticleVO valueOf(Article article) {
        return new ArticleVO(article.getId(), article.getText(), article.getTag(), article.getCreated_date(), article.getEdited_date(), article.getDeleted_date(),
                article.getAuthor_id(), article.getEditor_id(), article.getParent_id());
    }
}
