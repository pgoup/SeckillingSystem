package application.dao;

import application.entity.Good;

public interface GoodDao
{
    /**
     * 修改商品数量
     * @param count
     * @return
     */
    public  boolean editGood(String good_id,int count);

    /**
     * 根据商品编号查询商品
      * @param good_id
     * @return
     */
    public Good queryByGoodId(String good_id);
}
