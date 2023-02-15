package blog.controller.vo;

import blog.domain.Article;
import blog.domain.User;

import java.util.Date;

public class ArticleVO {

    private int id;

    private String text;

    private String title;
    private String tag;

    private Date createdDate;

    private Date editedDate;

    private User authorId;

    private User editorId;



    private Integer parentId;

    private boolean isEnabled;

    public ArticleVO(int id, String text, String title, String tag, Date createdDate, Date editedDate, User authorId,
                     User editorId, Integer parentId, boolean isEnabled) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.tag = tag;
        this.createdDate = createdDate;
        this.editedDate = editedDate;
        this.authorId = authorId;
        this.editorId = editorId;
        this.parentId = parentId;
        this.isEnabled = isEnabled;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public User getAuthorId() {
        return authorId;
    }

    public User getEditorId() {
        return editorId;
    }



    public Integer getParentId() {
        return parentId;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public static ArticleVO valueOf(Article article) {
        return new ArticleVO(article.getId(), article.getText(), article.getTitle(), article.getTag(), article.getCreatedDate(), article.getEditedDate(),
                article.getAuthorId(), article.getEditorId(), article.getParentId(), article.getIsEnabled());
    }
}
