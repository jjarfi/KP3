/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.42 : Database - demi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demi` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `demi`;

/*Table structure for table `bagian` */

DROP TABLE IF EXISTS `bagian`;

CREATE TABLE `bagian` (
  `id_bagian` varchar(10) NOT NULL,
  `id_perusahaan` varchar(10) DEFAULT NULL,
  `tgl_ijin` text,
  `nm_bagian` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_bagian`),
  KEY `bagian_ibfk_1` (`id_perusahaan`),
  CONSTRAINT `bagian_ibfk_1` FOREIGN KEY (`id_perusahaan`) REFERENCES `perusahaan` (`id_perusahaan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bagian` */

insert  into `bagian`(`id_bagian`,`id_perusahaan`,`tgl_ijin`,`nm_bagian`) values ('B12','P124','2018-09-01','SOPIR'),('B13','P123','2018-08-26','SATPAM');

/*Table structure for table `karyawan` */

DROP TABLE IF EXISTS `karyawan`;

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(10) NOT NULL,
  `nm_karyawan` varchar(30) DEFAULT NULL,
  `tmpt_lahir` varchar(30) DEFAULT NULL,
  `tgl_lahir` text,
  `jk` text,
  `agama` text,
  `alamat` varchar(30) DEFAULT NULL,
  `st_karyawan` text,
  `pendidikan` text,
  `tgl_masuk` text,
  `no_hp` text,
  `id_klasifikasi` varchar(10) DEFAULT NULL,
  `no_ktp` text,
  `no_rek` text,
  `no_bpjs_kenaker` text,
  `no_bpjs_sehat` text,
  `npwp` text,
  `email` text,
  `surat_lamaran` text,
  `foto_ktp` text,
  `foto_kk` text,
  `foto_ijasah` text,
  `foto_npwp` text,
  `foto_bpjs_kenaker` text,
  `foto_bpjs_kesehatan` text,
  `pas_foto` text,
  `foto` text,
  PRIMARY KEY (`id_karyawan`),
  KEY `id_klasifikasi` (`id_klasifikasi`),
  CONSTRAINT `karyawan_ibfk_1` FOREIGN KEY (`id_klasifikasi`) REFERENCES `klasifikasi` (`id_klasifikasi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `karyawan` */

insert  into `karyawan`(`id_karyawan`,`nm_karyawan`,`tmpt_lahir`,`tgl_lahir`,`jk`,`agama`,`alamat`,`st_karyawan`,`pendidikan`,`tgl_masuk`,`no_hp`,`id_klasifikasi`,`no_ktp`,`no_rek`,`no_bpjs_kenaker`,`no_bpjs_sehat`,`npwp`,`email`,`surat_lamaran`,`foto_ktp`,`foto_kk`,`foto_ijasah`,`foto_npwp`,`foto_bpjs_kenaker`,`foto_bpjs_kesehatan`,`pas_foto`,`foto`) values ('KR0001','HAERUDIN','JAYAPURA','1993-08-13','Laki-Laki','Islam','ABEPURA','Kontrak','SLTA','2018-08-12','081344444444','K12A','11111111111111','11111111111','11111111111','111111111111','111111111111','udin@gmail.com','ADA','ADA','ADA','ADA','ADA','ADA','ADA','ADA','E:\\@ifra Sr\\FB_IMG_1522576256263.jpg'),('KR0002','AMIN','JAYAPURA','2018-08-25','Laki-Laki','Islam','ABEPURA','Kontrak','SLTA','2018-08-10','081344444444','K12B','11111111111111','11111111111','11111111111','111111111111','111111111111','iam@gmail.com','ADA','ADA','ADA','ADA','ADA','ADA','ADA','ADA','E:\\@ifra Sr\\DSCF7904.JPG'),('KR0003','ABDULAH','JAYAPURA','2018-08-25','Laki-Laki','Islam','ABEPURA','Kontrak','SLTA','2018-08-10','081344444444','K13B','11111111111111','11111111111','11111111111','111111111111','111111111111','adlh@gmail.com','ADA','ADA','ADA','ADA','ADA','ADA','ADA','ADA','E:\\@ifra Sr\\Harga-Motor-Kawasaki-Ninja-4-tak.jpg'),('KR0004','OZAMA','JAYAPURA','2018-08-25','Laki-Laki','Islam','ABEPURA','Kontrak','SLTA','2018-08-10','081344444444','K13A','11111111111111','11111111111','11111111111','111111111111','111111111111','oz@gmail.com','ADA','ADA','ADA','ADA','ADA','ADA','ADA','ADA','E:\\@ifra Sr\\DSCF7911.JPG'),('KR0005','AMROZI','JAYAPURA','2018-08-25','Laki-Laki','Islam','ABEPURA','Kontrak','SLTA','2018-08-10','081344444444','K12C','11111111111111','11111111111','11111111111','111111111111','111111111111','amz@gmail.com','ADA','ADA','ADA','ADA','ADA','ADA','ADA','ADA','E:\\@ifra Sr\\DSCF8005.JPG'),('KR0006','IMAM SAMUDRA','JAYAPURA','2018-08-25','Laki-Laki','Islam','ABEPURA','Kontrak','SLTA','2018-08-10','081344444444','K13C','11111111111111','11111111111','11111111111','111111111111','111111111111','imams@gmail.com','ADA','ADA','ADA','ADA','ADA','ADA','ADA','ADA','E:\\@ifra Sr\\DSCF7919.JPG');

/*Table structure for table `klasifikasi` */

DROP TABLE IF EXISTS `klasifikasi`;

CREATE TABLE `klasifikasi` (
  `id_klasifikasi` varchar(10) NOT NULL,
  `id_bagian` varchar(10) DEFAULT NULL,
  `nm_klasifikasi` text,
  PRIMARY KEY (`id_klasifikasi`),
  KEY `klasifikasi_ibfk_1` (`id_bagian`),
  CONSTRAINT `klasifikasi_ibfk_1` FOREIGN KEY (`id_bagian`) REFERENCES `bagian` (`id_bagian`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `klasifikasi` */

insert  into `klasifikasi`(`id_klasifikasi`,`id_bagian`,`nm_klasifikasi`) values ('K12A','B12','SOPIR BUS'),('K12B','B12','SOPIR TAKSI'),('K12C','B12','SOPIR TRUK'),('K13A','B13','SATPAM PAGI'),('K13B','B13','SATPAM MALAM'),('K13C','B13','SATPAM SORE');

/*Table structure for table `perusahaan` */

DROP TABLE IF EXISTS `perusahaan`;

CREATE TABLE `perusahaan` (
  `id_perusahaan` varchar(10) NOT NULL,
  `nm_perusahaan` varchar(20) DEFAULT NULL,
  `nm_direktur` text,
  `nm_wadir` text,
  `tgl_berdiri` text,
  `nmr_perusahaan` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_perusahaan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `perusahaan` */

insert  into `perusahaan`(`id_perusahaan`,`nm_perusahaan`,`nm_direktur`,`nm_wadir`,`tgl_berdiri`,`nmr_perusahaan`) values ('P123','PT. PERTAMINA','JONNY','HOTMAN','2018-08-08','1923000000'),('P124','PT. HAWALI','LAODE','ADBULAH','2018-08-15','24440000');

/*Table structure for table `safety` */

DROP TABLE IF EXISTS `safety`;

CREATE TABLE `safety` (
  `id_safety` int(100) NOT NULL AUTO_INCREMENT,
  `no_sepatu` text,
  `no_baju` text,
  `no_celana` text,
  `no_helm` text,
  `no_sarung` text,
  `id_karyawan` varchar(10) NOT NULL,
  PRIMARY KEY (`id_safety`,`id_karyawan`),
  UNIQUE KEY `id_karyawan` (`id_karyawan`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

/*Data for the table `safety` */

insert  into `safety`(`id_safety`,`no_sepatu`,`no_baju`,`no_celana`,`no_helm`,`no_sarung`,`id_karyawan`) values (48,'20','L','23','S','20','KR0001'),(49,'24','XL','24','M','20','KR0002'),(50,'25','XXL','23','XXL','20','KR0004'),(51,'20','S','20','S','20','KR0006'),(52,'23','M','22','M','20','KR0005'),(53,'28','XL','20','M','20','KR0003');

/*Table structure for table `cetak` */

DROP TABLE IF EXISTS `cetak`;

/*!50001 DROP VIEW IF EXISTS `cetak` */;
/*!50001 DROP TABLE IF EXISTS `cetak` */;

/*!50001 CREATE TABLE  `cetak`(
 `nm_karyawan` varchar(30) ,
 `nm_bagian` varchar(20) ,
 `nm_klasifikasi` text ,
 `tmpt_lahir` varchar(30) ,
 `tgl_lahir` text ,
 `jk` text ,
 `st_karyawan` text ,
 `pendidikan` text ,
 `tgl_masuk` text ,
 `no_hp` text 
)*/;

/*Table structure for table `dash` */

DROP TABLE IF EXISTS `dash`;

/*!50001 DROP VIEW IF EXISTS `dash` */;
/*!50001 DROP TABLE IF EXISTS `dash` */;

/*!50001 CREATE TABLE  `dash`(
 `nm_karyawan` varchar(30) ,
 `id_karyawan` varchar(10) ,
 `nm_bagian` varchar(20) ,
 `nm_klasifikasi` text ,
 `tmpt_lahir` varchar(30) ,
 `tgl_lahir` text ,
 `alamat` varchar(30) ,
 `agama` text ,
 `no_ktp` text ,
 `npwp` text ,
 `no_rek` text ,
 `no_bpjs_sehat` text ,
 `no_bpjs_kenaker` text ,
 `no_hp` text ,
 `no_sepatu` text ,
 `no_baju` text ,
 `no_celana` text ,
 `no_helm` text ,
 `no_sarung` text ,
 `surat_lamaran` text ,
 `foto_ktp` text ,
 `foto_kk` text ,
 `foto_ijasah` text ,
 `foto_npwp` text ,
 `foto_bpjs_kenaker` text ,
 `foto_bpjs_kesehatan` text ,
 `pas_foto` text ,
 `foto` text ,
 `email` text 
)*/;

/*Table structure for table `viewbagian` */

DROP TABLE IF EXISTS `viewbagian`;

/*!50001 DROP VIEW IF EXISTS `viewbagian` */;
/*!50001 DROP TABLE IF EXISTS `viewbagian` */;

/*!50001 CREATE TABLE  `viewbagian`(
 `id_bagian` varchar(10) ,
 `nm_bagian` varchar(20) ,
 `nm_klasifikasi` text ,
 `nm_karyawan` varchar(30) 
)*/;

/*Table structure for table `viewklasifikasi` */

DROP TABLE IF EXISTS `viewklasifikasi`;

/*!50001 DROP VIEW IF EXISTS `viewklasifikasi` */;
/*!50001 DROP TABLE IF EXISTS `viewklasifikasi` */;

/*!50001 CREATE TABLE  `viewklasifikasi`(
 `nm_karyawan` varchar(30) ,
 `id_klasifikasi` varchar(10) ,
 `nm_klasifikasi` text 
)*/;

/*View structure for view cetak */

/*!50001 DROP TABLE IF EXISTS `cetak` */;
/*!50001 DROP VIEW IF EXISTS `cetak` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cetak` AS select `karyawan`.`nm_karyawan` AS `nm_karyawan`,`bagian`.`nm_bagian` AS `nm_bagian`,`klasifikasi`.`nm_klasifikasi` AS `nm_klasifikasi`,`karyawan`.`tmpt_lahir` AS `tmpt_lahir`,`karyawan`.`tgl_lahir` AS `tgl_lahir`,`karyawan`.`jk` AS `jk`,`karyawan`.`st_karyawan` AS `st_karyawan`,`karyawan`.`pendidikan` AS `pendidikan`,`karyawan`.`tgl_masuk` AS `tgl_masuk`,`karyawan`.`no_hp` AS `no_hp` from ((((`klasifikasi` join `bagian` on((`klasifikasi`.`id_bagian` = `bagian`.`id_bagian`))) join `karyawan` on((`karyawan`.`id_klasifikasi` = `klasifikasi`.`id_klasifikasi`))) join `perusahaan` on((`bagian`.`id_perusahaan` = `perusahaan`.`id_perusahaan`))) join `safety` on((`safety`.`id_karyawan` = `karyawan`.`id_karyawan`))) */;

/*View structure for view dash */

/*!50001 DROP TABLE IF EXISTS `dash` */;
/*!50001 DROP VIEW IF EXISTS `dash` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dash` AS select `karyawan`.`nm_karyawan` AS `nm_karyawan`,`karyawan`.`id_karyawan` AS `id_karyawan`,`bagian`.`nm_bagian` AS `nm_bagian`,`klasifikasi`.`nm_klasifikasi` AS `nm_klasifikasi`,`karyawan`.`tmpt_lahir` AS `tmpt_lahir`,`karyawan`.`tgl_lahir` AS `tgl_lahir`,`karyawan`.`alamat` AS `alamat`,`karyawan`.`agama` AS `agama`,`karyawan`.`no_ktp` AS `no_ktp`,`karyawan`.`npwp` AS `npwp`,`karyawan`.`no_rek` AS `no_rek`,`karyawan`.`no_bpjs_sehat` AS `no_bpjs_sehat`,`karyawan`.`no_bpjs_kenaker` AS `no_bpjs_kenaker`,`karyawan`.`no_hp` AS `no_hp`,`safety`.`no_sepatu` AS `no_sepatu`,`safety`.`no_baju` AS `no_baju`,`safety`.`no_celana` AS `no_celana`,`safety`.`no_helm` AS `no_helm`,`safety`.`no_sarung` AS `no_sarung`,`karyawan`.`surat_lamaran` AS `surat_lamaran`,`karyawan`.`foto_ktp` AS `foto_ktp`,`karyawan`.`foto_kk` AS `foto_kk`,`karyawan`.`foto_ijasah` AS `foto_ijasah`,`karyawan`.`foto_npwp` AS `foto_npwp`,`karyawan`.`foto_bpjs_kenaker` AS `foto_bpjs_kenaker`,`karyawan`.`foto_bpjs_kesehatan` AS `foto_bpjs_kesehatan`,`karyawan`.`pas_foto` AS `pas_foto`,`karyawan`.`foto` AS `foto`,`karyawan`.`email` AS `email` from (((`klasifikasi` join `bagian` on((`klasifikasi`.`id_bagian` = `bagian`.`id_bagian`))) join `karyawan` on((`karyawan`.`id_klasifikasi` = `klasifikasi`.`id_klasifikasi`))) join `safety` on((`safety`.`id_karyawan` = `karyawan`.`id_karyawan`))) */;

/*View structure for view viewbagian */

/*!50001 DROP TABLE IF EXISTS `viewbagian` */;
/*!50001 DROP VIEW IF EXISTS `viewbagian` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewbagian` AS select `bagian`.`id_bagian` AS `id_bagian`,`bagian`.`nm_bagian` AS `nm_bagian`,`klasifikasi`.`nm_klasifikasi` AS `nm_klasifikasi`,`karyawan`.`nm_karyawan` AS `nm_karyawan` from ((`klasifikasi` join `bagian` on((`klasifikasi`.`id_bagian` = `bagian`.`id_bagian`))) join `karyawan` on((`karyawan`.`id_klasifikasi` = `klasifikasi`.`id_klasifikasi`))) */;

/*View structure for view viewklasifikasi */

/*!50001 DROP TABLE IF EXISTS `viewklasifikasi` */;
/*!50001 DROP VIEW IF EXISTS `viewklasifikasi` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewklasifikasi` AS select `karyawan`.`nm_karyawan` AS `nm_karyawan`,`klasifikasi`.`id_klasifikasi` AS `id_klasifikasi`,`klasifikasi`.`nm_klasifikasi` AS `nm_klasifikasi` from (`karyawan` join `klasifikasi` on((`karyawan`.`id_klasifikasi` = `klasifikasi`.`id_klasifikasi`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
