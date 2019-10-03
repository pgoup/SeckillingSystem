package application.dao;

import application.entity.Good;

import java.util.List;

public interface GoodDao
{
    /**
     * 修改商品数量
     * @param count
     * @return
     */
    public  boolean editGood(String good_id,int count);

    /**
     * 根据商品编号查询商品库存
      * @param goodId
     * @return
     */
    public List<Integer> queryCountByGoodId(String goodId);


    List<Good> queryByGoodId(String goodId);
}
