package me.xueyao.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Simon.Xue
 * @date 11/25/20 7:12 PM
 **/
@Configuration
@Getter
public class NovelConfig {

    @Value("${novel.url}")
    private String url;

    @Value("${novel.suffixUrl}")
    private String suffixUrl;
}
