package application.repository;

import application.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Integer> {

  @Query(value = "select count from good where good_id=?1", nativeQuery = true)
  List<Integer> queryCountByGoodId(String good_id);

  @Query(value = "select * from good where good_id=?1",nativeQuery = true)
  List<Good> queryByGoodId(String good_id);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query(value = "update good set count=?2 where good_id=?1", nativeQuery = true)
  int editGood(String good_id, int count);
}
