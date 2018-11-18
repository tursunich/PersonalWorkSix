package kz.education.kstu.personalworksix.model;

public class SaleInfo extends BaseEntity{
    private ListPrice listPrice;

    public SaleInfo() {
    }

    public SaleInfo(ListPrice listPrice) {
        this.listPrice = listPrice;
    }

    public ListPrice getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }
}
