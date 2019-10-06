package BaseModule;

import com.application.SeckillingSystem.BaseModule.dao.GoodDao;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author PG
 */
@SpringBootApplication
public class ModuleTest {
    @Resource
    private GoodDao goodDao;

    @Test
    public void test() {
        int num=goodDao.queryDistinctCountByGoodId("1");
        System.out.println(num);
    }
}
