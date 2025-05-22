-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Servidor: sql7.freesqldatabase.com
-- Tiempo de generación: 22-05-2025 a las 19:13:45
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripciones`
--

CREATE TABLE `inscripciones` (
  `nombreUsuario` varchar(30) NOT NULL,
  `id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneo`
--

CREATE TABLE `torneo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `lugar` varchar(50) NOT NULL,
  `buy-in` int(10) NOT NULL COMMENT 'Pago para acceder',
  `jugadores` int(10) NOT NULL COMMENT 'Jugadores inscritos',
  `fecha` varchar(20) NOT NULL COMMENT 'Fecha limite',
  `bote_premios` int(10) NOT NULL COMMENT 'Premio económico',
  `limite_jugadores` int(10) NOT NULL COMMENT 'Jugadores max',
  `tipo` varchar(30) NOT NULL COMMENT 'Tipo de juego'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `torneo`
--

INSERT INTO `torneo` (`id`, `nombre`, `lugar`, `buy-in`, `jugadores`, `fecha`, `bote_premios`, `limite_jugadores`, `tipo`) VALUES
(1, 'Gran Torneo Anual', 'Casino Madrid', 1000, 120, '2025-06-10', 10000, 150, 'MysteryBounty'),
(2, 'Mega Stack Valencia', 'Casino Valencia', 500, 95, '2025-07-05', 5000, 100, 'MonsterStacks'),
(3, 'KO Madness', 'Casino Barcelona', 750, 80, '2025-06-20', 7500, 100, 'KO_Progresivo'),
(4, 'Mystery Weekend', 'Casino Sevilla', 3000, 60, '2025-05-30', 30000, 80, 'MysteryBounty'),
(5, 'Stack Attack', 'Casino Marbella', 2500, 110, '2025-07-15', 25000, 120, 'MonsterStacks'),
(6, 'Bounty Night', 'Casino Murcia', 3000, 70, '2025-06-12', 30000, 90, 'KO_Progresivo'),
(7, 'Mystery Power', 'Casino Bilbao', 1000, 90, '2025-06-25', 10000, 90, 'MysteryBounty'),
(8, 'Monster June', 'Casino Zaragoza', 500, 130, '2025-06-05', 5000, 150, 'MonsterStacks'),
(9, 'KO Final', 'Casino Granada', 2000, 101, '2025-07-01', 20000, 110, 'KO_Progresivo'),
(10, 'Mystery Turbo', 'Casino Santander', 1500, 50, '2025-05-28', 15000, 60, 'MysteryBounty');

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
-- Indices de la tabla `casino`
--
ALTER TABLE `casino`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `torneo`
--
ALTER TABLE `torneo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `username` (`nombreUsuario`),
  ADD UNIQUE KEY `numero_tlf` (`telefono`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `torneo`
--
ALTER TABLE `torneo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
