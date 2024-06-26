CREATE TABLE stockInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    bid DECIMAL(10, 2) NOT NULL,
    ask DECIMAL(10, 2) NOT NULL,
    openingPrice DECIMAL(10, 2) NOT NULL,
    highPrice DECIMAL(10, 2) NOT NULL,
    lowPrice DECIMAL(10, 2) NOT NULL,
    closingPrice DECIMAL(10, 2) NOT NULL,
    averagePrice DECIMAL(10, 2) NOT NULL,
    totalVolume INT NOT NULL,
    turnover DECIMAL(15, 2) NOT NULL,
    trades INT NOT NULL
);
