package me.xueyao.repository;

import me.xueyao.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simon.Xue
 * @date 11/25/20 5:33 PM
 **/
@Repository
public interface NovelRepository extends JpaRepository<Novel, Long> {
}
