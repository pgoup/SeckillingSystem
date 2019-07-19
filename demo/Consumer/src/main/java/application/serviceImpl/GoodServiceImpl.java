package application.serviceImpl;

import application.dao.GoodDao;
import application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl implements GoodService
{
    @Autowired
    private GoodDao goodDao;
    /**
     * 购买商品
     * @param goodId
     * @param count
     * @return
     */
    @Override
    public boolean purchaseGood( String goodId, int count)
    {

        return goodDao.editGood(goodId,count);
    }
}
