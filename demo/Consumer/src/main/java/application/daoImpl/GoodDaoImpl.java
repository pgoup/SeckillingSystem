package application.daoImpl;

import application.dao.GoodDao;
import application.entity.Good;
import application.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodDaoImpl implements GoodDao
{


    @Autowired
    private GoodRepository goodRepository;
    /**
     * 修改商品数量
     * @param count
     * @return
     */
    @Override
    public  boolean editGood(String good_id,int count)
    {
         Good good=goodRepository.queryByGoodId(good_id);
        if(count>good.getCount())
            return false;
        else {
          //  good.setCount(good.getCount() - count);

                if( goodRepository.editGood(good_id,good.getCount()-count)>0)
                    return true;
                else
                    return false;
        }
    }

    @Override
    public Good queryByGoodId(String good_id) {
        return goodRepository.queryByGoodId(good_id);
    }
}
