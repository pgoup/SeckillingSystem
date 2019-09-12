package application.dao.Impl;

import application.dao.GoodDao;
import application.entity.Good;
import application.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodDaoImpl implements GoodDao {

  @Resource private GoodRepository goodRepository;

  /**
   * 修改商品数量
   *
   * @param count
   * @return
   */
  @Override
  public boolean editGood(String good_id, int count) {

    if (goodRepository.editGood(good_id, count) > 0) return true;
    else return false;
  }

  /**
   * 根据商品编号查找商品的库存余量
   *
   * @param goodId
   * @return
   */
  @Override
  public List<Integer> queryCountByGoodId(String goodId) {
    return goodRepository.queryCountByGoodId(goodId);
  }

  @Override
  public List<Good> queryByGoodId(String goodId) {
    return goodRepository.queryByGoodId(goodId);
  }
}
