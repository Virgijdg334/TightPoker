-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Servidor: sql7.freesqldatabase.com
-- Tiempo de generación: 22-05-2025 a las 19:12:20
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
-- Estructura de tabla para la tabla `casino`
--

CREATE TABLE `casino` (
  `nombre` varchar(50) NOT NULL,
  `lugar` varchar(50) NOT NULL,
  `n_mesas` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `casino`
--

INSERT INTO `casino` (`nombre`, `lugar`, `n_mesas`) VALUES
('Casino Barcelona', 'Calle Marina 19-21, Barcelona', 60),
('Casino Bilbao', 'Calle Gran Vía 34, Bilbao', 38),
('Casino Madrid', 'Calle Alcalá 15, Madrid', 45),
('Casino Marbella', 'Calle José Meliá, Marbella', 40),
('Casino Murcia', 'Avenida Juan Carlos I, Murcia', 30),
('Casino Sevilla', 'Avenida de la Constitución 23, Sevilla', 35),
('Casino Valencia', 'Avenida de las Cortes Valencianas 59, Valencia', 50);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `casino`
--
ALTER TABLE `casino`
  ADD PRIMARY KEY (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
