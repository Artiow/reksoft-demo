alter table demo.label
  add version bigint not null default 1;

alter table demo.singer
  add version bigint not null default 1;

alter table demo.album
  add version bigint not null default 1;

alter table demo.media
  add version bigint not null default 1;
