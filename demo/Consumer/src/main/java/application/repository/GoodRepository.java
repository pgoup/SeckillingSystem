package application.repository;

import application.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GoodRepository  extends JpaRepository<Good, Integer> {

    @Query(value = "select * from good where good_id=?1",nativeQuery = true)
    public Good queryByGoodId(String good_id);

    @Transactional
    @Modifying(clearAutomatically=true)
    @Query(value = "update good set count=?2 where good_id=?1",nativeQuery = true)
    public int editGood(String good_id,int count);
}
