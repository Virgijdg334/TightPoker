-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Servidor: sql7.freesqldatabase.com
-- Tiempo de generación: 22-05-2025 a las 19:13:19
-- Versión del servidor: 5.5.62-0ubuntu0.14.04.1
-- Versión de PHP: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sql7780337`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dni` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombreUsuario` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `contraseña` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `edad` int(3) NOT NULL,
  `telefono` int(9) NOT NULL,
  `codigoPostal` int(5) NOT NULL,
  `n_tarjeta` int(20) NOT NULL DEFAULT '0',
  `saldo` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`dni`, `nombre`, `apellidos`, `nombreUsuario`, `contraseña`, `edad`, `telefono`, `codigoPostal`, `n_tarjeta`, `saldo`) VALUES
('12345678A', 'Carlos', 'Mart', 'carlosML', 'pass1234', 30, 612345678, 28001, 2147483647, 3000),
('23456789C', 'Pedro', 'S', 'pedroSM', 'pedro2024', 45, 667889900, 29010, 2147483647, 0),
('34567890D', 'Laura', 'Fern', 'lauraFG', 'fglaura89', 22, 611223344, 41012, 2147483647, 7000),
('45678901E', 'Javier', 'Navarro Pe', 'javiNP', 'jnavpass', 35, 600112233, 3003, 2147483647, 16000),
('56341278F', 'Jose Antonio', 'Jimenez Bernaza', 'jank3', '1234', 18, 627547012, 41020, 0, 5000),
('87654321B', 'Luc', 'G', 'luciaGR', 'luciaPass', 27, 698765432, 46002, 2147483647, 10000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `username` (`nombreUsuario`),
  ADD UNIQUE KEY `numero_tlf` (`telefono`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
