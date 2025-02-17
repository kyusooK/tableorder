package tableorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import tableorder.MenuApplication;
import tableorder.domain.MenuRegistere됨;

@Entity
@Table(name = "Menu_table")
@Data
//<<< DDD / Aggregate Root
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String menuName;

    private Long price;

    private Integer stock;

    private String menuInfo;

    private String ingredients;

    private String reviewId;

    @PostPersist
    public void onPostPersist() {
        MenuRegistere됨 menuRegistere됨 = new MenuRegistere됨(this);
        menuRegistere됨.publishAfterCommit();
    }

    public static MenuRepository repository() {
        MenuRepository menuRepository = MenuApplication.applicationContext.getBean(
            MenuRepository.class
        );
        return menuRepository;
    }
}
//>>> DDD / Aggregate Root
