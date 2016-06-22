-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 22-Jun-2016 às 03:55
-- Versão do servidor: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `manga`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `colecao`
--

CREATE TABLE `colecao` (
  `id` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `categoria` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `colecao`
--

INSERT INTO `colecao` (`id`, `nome`, `categoria`) VALUES
(1, 'Naruto', 'Shounen'),
(2, 'Pokemon', 'Shoujo'),
(3, 'Digimon', 'Yaoi'),
(8, 'fsfds', 'sdfdsf'),
(9, 'teste', 'teste'),
(10, 'aaa', 'bbbb'),
(11, 'oooo', 'bbbb');

-- --------------------------------------------------------

--
-- Estrutura da tabela `volume`
--

CREATE TABLE `volume` (
  `id` int(11) NOT NULL,
  `volume` varchar(20) NOT NULL,
  `id_colecao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `volume`
--

INSERT INTO `volume` (`id`, `volume`, `id_colecao`) VALUES
(1, '12', 0),
(2, '15', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `colecao`
--
ALTER TABLE `colecao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `volume`
--
ALTER TABLE `volume`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `colecao`
--
ALTER TABLE `colecao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `volume`
--
ALTER TABLE `volume`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
