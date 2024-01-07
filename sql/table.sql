-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                                                                                                                                                          null comment '用户昵称',
    userAccount  varchar(256)                                                                                                                                                          null comment '账号',
    avatarUrl    varchar(1024) default 'https://images.zsxq.com/FhehgFrmFN298XCGeSv6OSw6LNGL?e=1704038399&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:dMBLtPUO3bquhbFBOj19Y7aU5Eo=' null comment '用户头像',
    gender       tinyint                                                                                                                                                               null comment '性别',
    userPassword varchar(512)                                                                                                                                                          not null comment '密码',
    phone        varchar(128)                                                                                                                                                          null comment '电话',
    email        varchar(512)                                                                                                                                                          null comment '邮箱',
    userStatus   int           default 0                                                                                                                                               not null comment '状态 0 - 正
常',
    createTime   datetime      default CURRENT_TIMESTAMP                                                                                                                               null comment '创建时间',
    updateTime   datetime      default CURRENT_TIMESTAMP                                                                                                                               null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint       default 0                                                                                                                                               not null comment '是否删除（逻
辑删除）',
    userRole     int           default 0                                                                                                                                               not null comment '用户权限 0
- 普通用户 1 - 管理员',
    planetCode   varchar(512)                                                                                                                                                          null comment '星球编号'
);
alter table user add column tags varchar(1024) null comment '标签列表';



-- auto-generated definition
create table tag
(
    id           bigint auto_increment comment 'id'
        primary key,
    tagname      varchar(256)                       null comment '标签名称',
    userId       bigint                             null comment '用户id',
    parentid     bigint                             null comment '父级id',
    isparentid   bigint                             null comment '是否是父级id',
    userPassword varchar(512)                       not null comment '密码',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除（逻
辑删除）',
    constraint uniidx_tagname
        unique (tagname)
);

create index uniidx_userid
    on tag (userId);

