package kz.education.kstu.personalworksix.model;

public class VolumeInfo extends BaseEntity{
    private String title;
    private String publisher;
    private String publishedDate;

    public VolumeInfo() {
    }

    public VolumeInfo(String title, String publisher, String publishDate) {
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishedDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishedDate = publishDate;
    }
}
