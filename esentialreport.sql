-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2019 a las 02:32:24
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `esentialreport`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacion_alumnos1151217`
--

CREATE TABLE `relacion_alumnos1151217` (
  `fechaRegistro` bigint(20) DEFAULT NULL,
  `Codigo_Alumno` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Nombre_Alumno` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Tipo_Doc` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Documento` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Semestre` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Ingreso` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Promedio` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Estado_Matricula` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Celular` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Email` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Email_Institucional` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Colegio_Egresado` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Municipio_Nacimiento` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `relacion_alumnos1151217`
--

INSERT INTO `relacion_alumnos1151217` (`fechaRegistro`, `Codigo_Alumno`, `Nombre_Alumno`, `Tipo_Doc`, `Documento`, `Semestre`, `Ingreso`, `Promedio`, `Estado_Matricula`, `Celular`, `Email`, `Email_Institucional`, `Colegio_Egresado`, `Municipio_Nacimiento`) VALUES
(1571099201032, '1151806', 'CORONEL ANDRADE MANUEL ALEJANDRO', 'CC', '1004877003', '4', '2018-1', '4.4', 'MATRICULADO', '3209572488', 'alejo.coronel@hotmail.com', 'andrademanuelalejandroc@ufps.edu.co', 'INST SALESIANO SAN JUAN BOSCO', 'CUCUTA'),
(1571099203139, '1151541', 'MARTINEZ MARTINEZ FABIAN ANDRES', 'CC', '1090514221', '5', '2016-1', '3.4', 'SEMESTRE CANCELADO', '3213634621', 'fc.martinez.fesc@gmail.com', 'fabianandresmmmam@ufps.edu.co', 'COLEGIO SANTA MARIA MAZZARELLO', 'CUCUTA'),
(1571099207413, '1151211', 'ORTEGA BAEZ ENDER HERNANDO', 'CC', '1090503218', '10', '2014-1', '4.06', 'PAGO REPORTADO', '3153042721', 'enderortega@outlook.com', 'enderhernandoob@ufps.edu.co', 'INSTITUTO TECNICO PATIOS CENTR', 'CUCUTA'),
(1571099216498, '1151484', 'MELO CALVO EDINSSON ADRIAN', 'CC', '1090520120', '7', '2016-1', '4.2', 'LIQUIDADO', '3112715929', 'edsunadrian@gmail.com', 'edinssonadrianmc@ufps.edu.co', 'JUAN PABLO I', 'VILLACARO'),
(1571099217158, '1151697', 'FORERO PUELLO NICOLAS', 'CC', '1093802336', '4', '2017-2', '4.17', 'ACTIVADO PARA LIQUIDACION', '3045268267', 'nicolasforero1199@gmail.com', 'nicolasfp@ufps.edu.co', 'INSTITUTO PEDAGOGICO OCUPACION', 'CUCUTA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usu_cod` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `usu_cla` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `usu_pro_aca` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `usu_nom` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `usu_tip` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usu_cod`, `usu_cla`, `usu_pro_aca`, `usu_nom`, `usu_tip`) VALUES
('\"1151218\"', 'cb87952551931684889effd6b4d20aa5', '\"Ingenieria Electronica\"', '\"DIRECTOR\"', 2),
('1151217', '73e0062b28ba65663aa3e2cabd9df145', 'sistemas', 'victor', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usu_cod`),
  ADD UNIQUE KEY `usu_pro_aca` (`usu_pro_aca`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
