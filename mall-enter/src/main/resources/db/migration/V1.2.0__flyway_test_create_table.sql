CREATE TABLE `flyway_test`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username`    varchar(64)  DEFAULT NULL COMMENT '用户名',
  `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8 COMMENT ='测试表';