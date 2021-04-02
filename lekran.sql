# Host: localhost  (Version: 5.5.5-10.4.17-MariaDB)
# Date: 2021-04-02 19:12:15
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "dt_pemesanan"
#

DROP TABLE IF EXISTS `dt_pemesanan`;
CREATE TABLE `dt_pemesanan` (
  `id_detail` varchar(11) NOT NULL DEFAULT '',
  `id_pemesanan` varchar(20) DEFAULT NULL,
  `id_item` varchar(20) DEFAULT NULL,
  `nama_item` varchar(255) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_detail`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "dt_pemesanan"
#

/*!40000 ALTER TABLE `dt_pemesanan` DISABLE KEYS */;
INSERT INTO `dt_pemesanan` VALUES ('DT0001','ORD-0001','M001','Capucino Panas','MINUMAN',9000,3,27000),('DT0002','ORD-0001','M002','Capucino Dingin','MINUMAN',10000,3,27000),('DT0003','ORD-0002','M001','Capucino Panas','MINUMAN',9000,2,18000),('DT0004','ORD-0002','M002','Capucino Dingin','MINUMAN',10000,2,20000),('DT0005','ORD-0003','M001','Capucino Panas','MINUMAN',9000,2,18000),('DT0006','ORD-0004','M001','Capucino Panas','MINUMAN',9000,1,9000),('DT0007','ORD-0005','M004','Teh Tarek Dingin','MINUMAN',10000,3,30000),('DT0008','ORD-0006','M003','Teh Tarek Panas','MINUMAN',9000,3,27000),('DT0009','ORD-0007','M004','Teh Tarek Dingin','MINUMAN',10000,2,20000),('DT0010','ORD-0008','M003','Teh Tarek Panas','MINUMAN',9000,2,18000),('DT0011','ORD-0009','M001','Capucino Panas','MINUMAN',9000,2,18000),('DT0012','ORD-0009','M002','Capucino Dingin','MINUMAN',10000,1,10000),('DT0013','ORD-0010','M001','Capucino Panas','MINUMAN',9000,1,9000);
/*!40000 ALTER TABLE `dt_pemesanan` ENABLE KEYS */;

#
# Structure for table "tb_menu"
#

DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id_menu` varchar(11) NOT NULL DEFAULT '',
  `nama_menu` varchar(255) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_menu`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "tb_menu"
#

/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES ('M001','Capucino Panas','MINUMAN',9000),('M002','Capucino Dingin','MINUMAN',10000),('M003','Teh Tarek Panas','MINUMAN',9000),('M004','Teh Tarek Dingin','MINUMAN',10000);
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;

#
# Structure for table "tb_pemesanan"
#

DROP TABLE IF EXISTS `tb_pemesanan`;
CREATE TABLE `tb_pemesanan` (
  `id_pemesanan` varchar(11) NOT NULL DEFAULT '',
  `tanggal` date DEFAULT NULL,
  `no_meja` varchar(50) DEFAULT NULL,
  `total_item` int(11) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `bayar` int(11) DEFAULT NULL,
  `kembalian` int(11) DEFAULT NULL,
  `nama_kasir` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_pemesanan`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Data for table "tb_pemesanan"
#

/*!40000 ALTER TABLE `tb_pemesanan` DISABLE KEYS */;
INSERT INTO `tb_pemesanan` VALUES ('ORD-0001','2020-11-09','No 1',6,54000,55000,1000,'Tika123','SELESAI'),('ORD-0002','2020-11-10','No 2',4,38000,38000,0,'Tika123','SELESAI'),('ORD-0003','2020-11-10','No 1',2,18000,20000,2000,'Tika123','SELESAI'),('ORD-0004','2020-11-10','No 1',1,9000,10000,1000,'Tika123','SELESAI'),('ORD-0005','2020-11-10','No 1',3,30000,50000,20000,'Tika123','SELESAI'),('ORD-0006','2020-11-10','No 6',3,27000,30000,3000,'Tika123','SELESAI'),('ORD-0007','2020-11-10','No 1',2,20000,20000,0,'Tika123','SELESAI'),('ORD-0008','2020-11-11','No 3',2,18000,20000,2000,'tika123','SELESAI'),('ORD-0009','2020-11-30','No 1',3,28000,30000,2000,'tika123','SELESAI'),('ORD-0010','2020-11-30','No 1',1,9000,10000,1000,'tika123','SELESAI');
/*!40000 ALTER TABLE `tb_pemesanan` ENABLE KEYS */;

#
# Structure for table "tb_pengguna"
#

DROP TABLE IF EXISTS `tb_pengguna`;
CREATE TABLE `tb_pengguna` (
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tb_pengguna"
#

INSERT INTO `tb_pengguna` VALUES ('hengky123','123456','SUPERADMIN'),('tika123','123456','ADMIN'),('zila123','123456','ADMIN');
