package models.users;

import lombok.Data;

import java.util.List;
@Data
public class Users {
    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<Datum> data = null;
    private Ad ad;



}
