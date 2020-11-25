package me.xueyao.service;

import me.xueyao.entity.Novel;

/**
 * @author Simon.Xue
 * @date 11/25/20 6:15 PM
 **/
public interface NovelService {

    /**
     * 新增
     *
     * @param novel
     */
    void save(Novel novel);
}
