package me.xueyao.print;

import me.xueyao.entity.Novel;
import me.xueyao.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 把爬取到数据添加到数据库中
 *
 * @author Simon.Xue
 * @date 11/25/20 6:13 PM
 **/
@Component
public class JdbcPipeline implements Pipeline {

    @Autowired
    private NovelService novelService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Novel novel = resultItems.get("novel");
        if (null != novel) {
            novelService.save(novel);
        }
    }
}
