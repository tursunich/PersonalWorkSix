package kz.education.kstu.personalworksix.model;

public class Book extends BaseEntity{
    private String title;
    private VolumeInfo volumeInfo;
    private SaleInfo saleInfo;

    public Book() {
    }

    public Book(String title, VolumeInfo volumeInfo, SaleInfo saleInfo) {
        this.title = title;
        this.volumeInfo = volumeInfo;
        this.saleInfo = saleInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }
}
