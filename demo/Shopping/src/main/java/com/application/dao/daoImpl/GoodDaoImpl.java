package com.application.dao.daoImpl;

import com.application.dao.GoodDao;
import com.application.entity.Good;
import com.application.repository.GoodRepository;
import com.application.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodDaoImpl implements GoodDao {

    @Autowired
    private GoodRepository goodRepository;
    /**
     * 根据商品编号查询具体的一个商品
     * @param goodId
     * @return
             */
    @Override
    public Good queryGoodByGoodId(String goodId) {
        List<Good> goods=goodRepository.queryGoodByGoodId(goodId);
        if(goods.size()==0){
            return null;
        }else{
            return goods.get(0);
        }
    }

    /**
     * 根据商品是否打折查询商品
     * @param discount
     * @return
     */
    @Override
    public List<Good> queryGoodByDiscount(int discount) {
        return goodRepository.queryGoodByDiscount(discount);
    }

    /**
     * 根据商品类型查找商品
     * @param kind
     * @return
     */
    @Override
    public List<Good> queryGoodByKind(String kind) {
        return goodRepository.queryGoodByKind(kind);
    }
}
