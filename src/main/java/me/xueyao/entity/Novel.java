package me.xueyao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Simon.Xue
 * @date 11/25/20 5:19 PM
 **/
@Data
@Entity
@Table(name = "dpcq_novel")
public class Novel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "int(7) comment '章节编号'")
    private Integer no;

    @Column(columnDefinition = "varchar(64) comment '小说标题'")
    private String title;

    @Column(columnDefinition = "text comment '小说内容'")
    private String content;
}
