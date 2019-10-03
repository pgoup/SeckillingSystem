package application.service;

public interface GoodService
{
    /**
     * 购买商品
     * @param goodId
     * @param count
     * @return
     */
    public boolean purchaseGood(String userId,String goodId,int count);


}
