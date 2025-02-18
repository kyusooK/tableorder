package tableorder.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "menu", url = "${api.url.menu}")
public interface MenuService {
    @GetMapping(path = "/menus")
    public List<Menu> getMenus(@RequestParam("ids") List<Long> ids);
}