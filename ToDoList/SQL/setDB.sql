drop table TASKS CASCADE CONSTRAINTS;
create table tasks (
    id_task int,
    task varchar2(200),
    status number(1),
    constraint p_id_task primary key (ID_TASK)
);
DROP SEQUENCE TASK_SEQ;
create SEQUENCE TASK_SEQ
start with 1
increment by 1;