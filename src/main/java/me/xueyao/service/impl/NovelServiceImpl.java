package me.xueyao.service.impl;

import me.xueyao.entity.Novel;
import me.xueyao.repository.NovelRepository;
import me.xueyao.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Simon.Xue
 * @date 11/25/20 6:16 PM
 **/
@Service
public class NovelServiceImpl implements NovelService {
    @Autowired
    private NovelRepository novelRepository;

    @Override
    public void save(Novel novel) {
        novelRepository.save(novel);
    }
}
