DROP TABLE news;
CREATE TABLE news  (
   nid                bigint        auto_increment,
   title               varchar(30),
   content           text,
   photo             varchar(50);
   constraint pk_nid primary key (nid)
) engine=innodb ;
COMMIT;