package kz.education.kstu.personalworksix.model;

import java.util.List;

public class Response extends BaseEntity{
    private List<Book> items;

    public Response() {
    }

    public Response(List<Book> items) {
        this.items = items;
    }

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }
}
