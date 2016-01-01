# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table exam (
  id                        serial not null,
  constraint pk_exam primary key (id))
;

create table question_paper1 (
  id                        serial not null,
  constraint pk_question_paper1 primary key (id))
;

create table questions (
  id                        serial not null,
  question_paper1_id        integer not null,
  question                  varchar(255),
  option1                   varchar(255),
  option2                   varchar(255),
  option3                   varchar(255),
  option4                   varchar(255),
  right_option              varchar(255),
  constraint pk_questions primary key (id))
;

create table student (
  id                        serial not null,
  exam_id                   integer not null,
  user_name                 varchar(255),
  password1                 varchar(255),
  email                     varchar(255),
  constraint pk_student primary key (id))
;

alter table questions add constraint fk_questions_question_paper1_1 foreign key (question_paper1_id) references question_paper1 (id);
create index ix_questions_question_paper1_1 on questions (question_paper1_id);
alter table student add constraint fk_student_exam_2 foreign key (exam_id) references exam (id);
create index ix_student_exam_2 on student (exam_id);



# --- !Downs

drop table if exists exam cascade;

drop table if exists question_paper1 cascade;

drop table if exists questions cascade;

drop table if exists student cascade;

