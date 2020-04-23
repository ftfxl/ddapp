package cn.edu.scujcc.diandian;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Channel implements Serializable {
    private String id;
    private String title;
    private String quality;
    private String cover;
    private String url;
    private List comments;

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", quality='" + quality + '\'' +
                ", cover=" + cover +
                ", url='" + url + '\'' +
                ", comments=" + comments +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getComments() {
        return comments;
    }

    public void setComments(List comments) {
        this.comments = comments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return cover == channel.cover &&
                Objects.equals(title, channel.title) &&
                Objects.equals(quality, channel.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, quality, cover);
    }

}
