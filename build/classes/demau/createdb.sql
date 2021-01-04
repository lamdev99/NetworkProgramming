create table tblsinhvien(id integer primary key auto_increment, name varchar(20), dob date,grade varchar(20),hometown varchar(20));
create table tblsubject(id  integer primary key auto_increment, name varchar(20), numoftc integer, des varchar(20));
create table tblpoint(id integer primary key auto_increment, 
			idsv integer,ids integer,
            diemcc float,diemtbkt float,diembtl float,diemkt float,
            foreign key(idsv) references tblsinhvien(id),
            foreign key(ids) references tblsubject(id));