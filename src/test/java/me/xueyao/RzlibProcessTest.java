package me.xueyao;

import me.xueyao.config.NovelConfig;
import me.xueyao.print.JdbcPipeline;
import me.xueyao.util.RzlibProcess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.webmagic.Spider;

/**
 * @author Simon.Xue
 * @date 11/25/20 5:35 PM
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimonSpiderToolApplication.class)
public class RzlibProcessTest {

    @Autowired
    private JdbcPipeline jdbcPipeline;

    @Autowired
    private NovelConfig novelConfig;

    @Autowired
    private RzlibProcess rzlibProcess;

    /**
     * 下载
     */
    @Test
    public void testLoadNovel() {
        Spider.create(rzlibProcess)
                .addUrl(novelConfig.getUrl())
                .addPipeline(this.jdbcPipeline)
                .thread(1)
                .run();
    }


}
