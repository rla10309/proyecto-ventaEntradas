


INSERT INTO `salacorazon`.`salas` (`aforo`, `direccion`, `nombre`) VALUES ('500', 'C/ La Luz', 'Sala Corazón');

INSERT INTO `salacorazon`.`conciertos` (`fecha`, `grupo`, `hora`, `plazas`, `precioanticipado`, `preciotaquilla`, `idsala`) VALUES ('2023-8-15', 'Oasis', '22:30', '398', '40', '50', '1');
INSERT INTO `salacorazon`.`conciertos` (`fecha`, `grupo`, `hora`, `plazas`, `precioanticipado`, `preciotaquilla`, `idsala`) VALUES ('2023-04-25', 'Blur', '20:30', '250', '25', '30', '1');
INSERT INTO `salacorazon`.`conciertos` (`fecha`, `grupo`, `hora`, `plazas`, `precioanticipado`, `preciotaquilla`, `idsala`) VALUES ('2023-8-1', 'Metalica', '22:30', '300', '40', '50', '1');



INSERT INTO `salacorazon`.`usuarios` (`password`, `username`) VALUES ('$2a$10$mr2iXiNMF6FUQxe6A0iadOWkRlJk0SrTzGbi3AP3LXbdkKrNnCfFC', 'admin');
INSERT INTO `salacorazon`.`usuarios` (`password`, `username`) VALUES ('$2a$10$mr2iXiNMF6FUQxe6A0iadOWkRlJk0SrTzGbi3AP3LXbdkKrNnCfFC', 'user');
INSERT INTO `salacorazon`.`roles` (`nombre`) VALUES ('ROLE_ADMIN');
INSERT INTO `salacorazon`.`roles` (`nombre`) VALUES ('ROLE_USER');
INSERT INTO `salacorazon`.`usuariorol` (`idrol`, `idusuario`) VALUES ('1', '1');
INSERT INTO `salacorazon`.`usuariorol` (`idrol`, `idusuario`) VALUES ('2', '2');

INSERT INTO `salacorazon`.`ventas` VALUES (1,'111','2023-04-10','21:14:00',2,1);

