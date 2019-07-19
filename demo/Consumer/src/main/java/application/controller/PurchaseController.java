package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 抢购商品的控制端
 */
@Controller
public class PurchaseController
{
    /**
     * 根据商品id即购买的数量进行商品的购买
     * @param id
     * @param count
     * @return
     */
    @PostMapping("/purchase")
    public String purchase(int id,int count)
    {
        return null;
    }

}
