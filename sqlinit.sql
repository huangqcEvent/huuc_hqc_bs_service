create table if not exists club_recruitment_news
(
    id          varchar(32)  not null comment '社团纳新/公告id'
        primary key,
    club_id     varchar(32)  not null comment '社团id',
    title       varchar(100) not null comment '标题',
    type        int          not null comment '类型 1纳新 2公告',
    daily       longtext     null comment '社团工作',
    welfare     longtext     null comment '社团福利',
    claim       longtext     not null comment '社团要求/公告内容',
    number      int          null comment '计划纳新人数',
    already     int          null comment '已招人数',
    time_type   int          null comment '时间类型 1长期 2限时',
    start_time  datetime     null comment '开始时间',
    end_time    datetime     null comment '结束时间',
    status      int          not null comment '状态',
    create_time datetime     not null comment '创建时间',
    update_time datetime     null comment '更新时间',
    delete_time datetime     null comment '删除时间',
    sort        int          null comment '排序字段',
    image1      varchar(100) null comment '图片1',
    image2      varchar(100) null comment '图片2',
    image3      varchar(100) null comment '图片3'
)
    comment '社团纳新/公告表';

create table if not exists club_resume
(
    id           varchar(255)  not null comment '简历id'
        primary key,
    club_news_id varchar(255)  not null comment '社团纳新贴id',
    user_id      varchar(255)  not null comment '投递用户id',
    age          int           not null comment '年龄',
    sex          int           not null comment '性别',
    phone        varchar(255)  not null comment '联系方式',
    specialized  varchar(255)  not null comment '专业',
    grade        int           not null comment '年级',
    file_address varchar(255)  not null comment '简历地址',
    create_time  datetime      not null comment '投递时间',
    status       int default 0 not null comment '状态 0待筛选 1待面试 2已录用 3不录用',
    down_reason  varchar(255)  null comment '不录用原因',
    name         varchar(255)  not null comment '姓名'
)
    comment '社团纳新招聘表';

create table if not exists comment
(
    id          varchar(255)  not null comment '评论id'
        primary key,
    post_id     varchar(255)  not null comment '帖子id',
    post_type   varchar(255)  not null comment '帖子类型 1表白墙 2寻物 3社团',
    user_id     varchar(255)  not null comment '用户id',
    content     longtext      not null comment '评论内容',
    image_url   varchar(255)  not null comment '评论图片',
    create_time datetime      not null comment '评论时间',
    status      int default 1 not null comment '状态 1正常 2删除',
    is_top      int default 0 null comment '是否置顶 0否 1是'
);

create table if not exists confession_log
(
    id           varchar(32)       not null comment '(表白,找对象) 记录id'
        primary key,
    user_id      varchar(32)       not null comment '发帖用户id',
    type         int     default 0 not null comment '类型',
    is_anonymous int     default 0 not null comment '是否匿名 0否 1是',
    title        varchar(20)       not null comment '(表白,找对象) 标题',
    summary      varchar(40)       not null comment '(表白,找对象) 概要',
    content      varchar(2000)     not null comment '(表白,找对象) 内容',
    to_user_info varchar(50)       null comment '被表白用户简短信息(理想型信息)',
    create_time  datetime          not null comment '创建时间',
    update_time  datetime          null comment '更新时间',
    delete_time  datetime          null comment '删除时间',
    status       int     default 1 not null comment '状态',
    is_top       tinyint default 0 not null comment '是否置顶 0否 1是',
    image_1      varchar(200)      null comment '图片1',
    image_2      varchar(200)      null comment '图片2',
    image_3      varchar(200)      null comment '图片3'
);

create table if not exists confession_status_update_log
(
    id             varchar(32)  not null comment '表白墙贴状态更改记录id'
        primary key,
    confession_id  varchar(32)  not null comment '贴id',
    old_status     int          not null comment '旧状态',
    new_status     int          not null comment '新状态',
    update_time    datetime     not null comment '更新时间',
    update_user_id varchar(32)  not null comment '更新人id',
    update_reason  varchar(255) null comment '更新原因'
)
    comment '表白墙贴状态更改记录';

create table if not exists down_log
(
    id                varchar(32)  not null comment '封禁记录id'
        primary key,
    user_id           varchar(32)  not null comment '封禁用户id',
    down_time         datetime     not null comment '封禁时间',
    down_reason       varchar(100) not null comment '封禁原因',
    down_admin_id     varchar(32)  not null comment '封禁人id',
    down_end          int          not null comment '是否已解封 1已解封 0未解封',
    down_end_time     datetime     null comment '解封时间',
    down_end_admin_id varchar(32)  null comment '解封人id'
)
    comment '账号封禁记录表';

create table if not exists lost_item
(
    id          varchar(32)   not null comment '寻物贴id'
        primary key,
    user_id     varchar(32)   not null comment '发布者id',
    item_name   varchar(100)  not null comment '失物名称',
    type        int           not null comment '类型 1寻物 2寻主',
    item_desc   text          not null comment '失物描述',
    location    varchar(100)  not null comment '丢失地点',
    phone       varchar(100)  not null comment '联系方式（电话）',
    create_time datetime      not null comment '发布时间',
    update_time datetime      null comment '更新时间',
    delete_time datetime      null comment '删除时间',
    is_top      int default 0 not null comment '是否置顶 1置顶 0未置顶',
    status      int           not null comment '状态',
    image1      varchar(200)  null comment '图片1',
    image2      varchar(200)  null comment '图片2',
    image3      varchar(200)  null comment '图片3'
);

create table if not exists post_audit
(
    id           varchar(255)  not null comment '审核流水号'
        primary key,
    post_id      varchar(255)  not null comment '帖子id',
    post_type    varchar(255)  not null comment '帖子类型 1表白墙 2寻物 3社团',
    status       int           not null comment '审核状态 1自动敏感词审核 2待人工审核 3通过 4待修改',
    release_type int default 1 null comment '发布类型 1公布发布 2私密发布'
)
    comment '帖子审核表';

create table if not exists post_audit_log
(
    id           varchar(255)                    not null comment '审核记录id'
        primary key,
    audit_id     varchar(255)                    not null comment '审核流水号',
    audit_type   int                             not null comment '审核类型 1机器敏感词 2人工',
    audit_time   datetime                        not null comment '审核时间',
    audit_user   varchar(255) default '自动审核' not null comment '审核人名称',
    audit_result int                             not null comment '审核结果 1通过 2不通过',
    audit_remark varchar(255)                    null comment '审核备注',
    is_latest    int                             null comment '1是  0不是'
)
    comment '帖子审核记录表';

create table if not exists post_interaction
(
    id           varchar(255)  not null comment '主键'
        primary key,
    user_id      varchar(255)  not null comment '用户ID',
    post_id      varchar(255)  not null comment '帖子ID',
    post_type    int           not null comment '帖子类型',
    is_liked     int default 0 not null comment '是否点赞',
    is_commented int default 0 not null comment '是否评论',
    is_favored   int default 0 not null comment '是否收藏',
    total_score  int default 0 not null,
    create_time  datetime      null,
    update_time  datetime      null
)
    comment '分数表（用于协同过滤）';

create table if not exists sensitive_word_allow
(
    id             varchar(32)  not null comment '敏感词白名单id'
        primary key,
    sensitive_word varchar(100) not null comment '词语'
);

create table if not exists sensitive_word_deny
(
    id             varchar(32)  not null comment '敏感词黑名单id'
        primary key,
    sensitive_word varchar(100) not null comment '词语'
);

create table if not exists user
(
    id         varchar(32)   not null comment '用户ID雪花算法'
        primary key,
    userName   varchar(20)   not null comment '用户名',
    pWord      varchar(1000) not null comment '用户密码MD5',
    identity   int           null comment '用户身份 0普通用户 1管理员 2社团账号',
    head       varchar(100)  not null comment '用户头像',
    createTime datetime      null comment '注册时间',
    state      int           null comment '帐号状态 1正常 0停用'
)
    comment '用户表';

create table if not exists user_identity_update_log
(
    id              varchar(32)  not null comment '身份转换记录id'
        primary key,
    user_id         varchar(32)  not null comment '更新用户id',
    old_identity    int          not null comment '用户原身份',
    new_identity    int          not null comment '用户新身份',
    update_admin_id varchar(32)  not null comment '操作管理员id',
    update_time     datetime     not null comment '更新时间',
    update_reason   varchar(100) not null comment '更新原因'
)
    comment '用户身份信息转化记录表';

