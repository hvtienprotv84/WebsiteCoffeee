-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2023 at 04:54 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tynstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `email` varchar(125) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(64) NOT NULL,
  `password` varchar(125) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `email`, `enabled`, `first_name`, `image`, `last_name`, `password`) VALUES
(1, 'tienadmin@gmail.com', b'1', 'Huỳnh', '5231020.png', 'Vĩnh Tiến', '$2y$10$QTn0DA8e00rvxuJL.ENBROWrLsCIqYUr6XbXGyo/nVTjjc1ntkLPC');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `image`, `name`, `status`) VALUES
(1, 'ca_phe_arabica_3.jpg', 'Cafe Arabica', NULL),
(2, 'ca-phe-robusta-1.jpg', 'Cà phê Robusta', NULL),
(3, 'tinh-chat-ca-phe-Culi.jpg', 'Cà phê Culi', NULL),
(4, 'ca-phe-cherry-2.jpg', 'Cafe Cherry', NULL),
(5, 'hat-moka-rang-moc.jpg', 'Cà phê Moka', NULL),
(6, '2-300x300.jpg', 'Thức Uống Khác', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `address`, `birthday`, `name`, `password`, `phone`, `status`, `username`) VALUES
(1, 'CMT8', '2000-01-01', 'Huỳnh Tiến', '$2a$10$QWZjshkQ9VgUJUrSSnoVcuc.ae7GJHZPlhlw9HRE0d67ENqjV0.uS', '0931111111', NULL, 'tienuser@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `recipient_address` varchar(255) DEFAULT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `recipient_phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_date`, `recipient_address`, `recipient_name`, `recipient_phone`, `status`, `customer_id`) VALUES
(1, '2023-06-16 17:09:14', 'CMT8', 'Huỳnh Vĩnh Tiến', '0931111111', 'Chờ xử lý', 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `prices` double NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `prices`, `quantity`, `product_id`, `order_id`) VALUES
(1, 25000, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `inventory` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prices` double NOT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `image`, `inventory`, `name`, `prices`, `category_id`) VALUES
(1, 'Hình-App_3006021-Cà-Phê-Đen.jpg', 49, 'Cafe Đen Đá', 25000, 1),
(2, 'Hình-App_3006021-Cà-Phê-Sữa.jpg', 30, 'Cafe Sữa Đá', 35000, 2),
(3, 'Hình-App_3006021-Cà-Phê-Ca-Cao-Sữa-Dừa.jpg', 20, 'Cafe Ca Cao Sữa Dừa', 55000, 3),
(4, 'Hình-App_3006021-Sữa-Tươi-Hoa-Đậu-Biếc-300x300.jpg', 30, 'Cafe Bạc Hà Sữa Dừa', 45000, 4),
(5, 'Hình-App_3006021-Latte-Đá-300x300.jpg', 20, 'Cafe Nhiều Sữa', 75000, 5),
(6, '1-768x768.jpg', 20, 'Cafe Việt Quất Sữa', 65000, 3),
(7, 'Cafe-đen-4.png', 20, 'Cafe Đen Đá Big Size', 70000, 5),
(8, 'Hình-App_3006021-Trà-Sữa-Thạch-Cà-Phê-300x300.jpg', 15, 'Cafe Trà Sữa Full Topping', 85000, 2),
(9, 'cafe-muói.png', 15, 'Cafe Muối', 25000, 3),
(10, 'Hình-App_3006021-Trà-Đào-Hạt-Chia-300x300.jpg', 50, 'Trà Chanh Muối Thanh Nhiệt', 15000, 6),
(11, 'Hình-App_3006021-Ép-Dưa-Hấu-300x300.jpg', 30, 'Nước Ép Dưa Hấu', 25000, 6),
(12, '2-300x300 (1).jpg', 20, 'Nước Ép Cam Sữa', 25000, 6),
(13, 'Hình-App_3006021-Chanh-Sả-Gừng-Hạt-Chi-300x300.jpg', 20, 'Trà Chanh Sả Gừng Hạt Chia', 15000, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_47bvqemyk6vlm0w7crc3opdd4` (`email`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bepynu3b6l8k2ppuq6b33xfxc` (`username`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  ADD KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
