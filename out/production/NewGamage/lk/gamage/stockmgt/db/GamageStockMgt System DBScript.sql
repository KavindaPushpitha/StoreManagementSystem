/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Kavinda Pushpitha
 * Created: May 14, 2018
 */

DROP DATABASE GamageStockMgt;
CREATE DATABASE GamageStockMgt;
USE GamageStockMgt;

CREATE TABLE Customer(
	CustomerID VARCHAR(6) NOT NULL,
	CustomerName VARCHAR(25) NOT NULL,
	DOB VARCHAR (10),
	Address VARCHAR(30),
	Contact VARCHAR(15),
	NIC VARCHAR(12),
	CONSTRAINT PRIMARY KEY (CustomerID)
);

CREATE TABLE CustomerOrders(
	OrderID VARCHAR(6) NOT NULL,
	CustomerID VARCHAR(6)NOT NULL,
	OrderDate VARCHAR (10),
	CustomerPayment DECIMAL(10,2),
	CONSTRAINT PRIMARY KEY (OrderID),
	CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Warranty(
	WarrantyID VARCHAR(6) NOT NULL,
	WarrantyPeriod VARCHAR (6),
	WarrantyFrom VARCHAR (10),
	WarrantyTo VARCHAR (10),
	CONSTRAINT PRIMARY KEY (WarrantyID)
);

CREATE TABLE Items(
	ItemCode VARCHAR(6) NOT NULL,
	ItemName VARCHAR(30)NOT NULL,
	Brand VARCHAR(15) NOT NULL,
	ModelNo VARCHAR(20)NOT NULL,
	CONSTRAINT PRIMARY KEY (ItemCode)

);

CREATE TABLE Stock(
	StockID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	QtyOnHand int,
	SellingPrice decimal(10,2),
	CONSTRAINT PRIMARY KEY (StockID),
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE Supplier(
	SupplierID VARCHAR(6) NOT NULL,
	SupplierName VARCHAR(25) NOT NULL,
	Address VARCHAR(25) NOT NULL,
	Contact VARCHAR(15) NOT NULL,
	CompanyName VARCHAR(25) NOT NULL,
	CompanyAddress VARCHAR(25) NOT NULL,
	CompanyContact VARCHAR(15)NOT NULL,
	CONSTRAINT PRIMARY KEY (SupplierID)
);

CREATE TABLE OrderDetail(
	OrderID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	WarrantyID VARCHAR(6),
	UnitPrice DECIMAL(10,2),
	OrderQty INT,
	CONSTRAINT PRIMARY KEY (OrderID,ItemCode),
	CONSTRAINT FOREIGN KEY(WarrantyID) REFERENCES Warranty(WarrantyID)
	ON UPDATE CASCADE ON DELETE CASCADE

	
);
CREATE TABLE SupplierOrders(
	SupplierOrderID VARCHAR(6) NOT NULL,
	SupplierID VARCHAR(6)NOT NULL,
	SupplierOrderDate VARCHAR (10),
	CONSTRAINT PRIMARY KEY (SupplierOrderID),
	CONSTRAINT FOREIGN KEY(SupplierID) REFERENCES Supplier(SupplierID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE GRN(
	GRNID VARCHAR(6) NOT NULL,
	SupplierOrderID VARCHAR(6) NOT NULL,
	GRNDate VARCHAR (10),
	SupplierPayment DECIMAL(10,2),
	Status VARCHAR(20),
	CONSTRAINT PRIMARY KEY (GRNID),
	CONSTRAINT FOREIGN KEY(SupplierOrderID) REFERENCES SupplierOrders(SupplierOrderID)
	ON UPDATE CASCADE ON DELETE CASCADE
	
);

CREATE TABLE GRNDetail(
	GRNID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	StockID VARCHAR(6) NOT NULL,
	BuyingPrice DECIMAL(10,2),
	Qty INT,
	CONSTRAINT PRIMARY KEY (GRNID,ItemCode,StockID)
);

CREATE TABLE SupplierOrderDetail(
	SupplierOrderID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	Qty INT,
	CONSTRAINT PRIMARY KEY (SupplierOrderID,ItemCode)
);

CREATE TABLE DamageItem(
	DamageID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	Fault VARCHAR(30),
	DamageType VARCHAR(20),
	DamageDate VARCHAR (10),
	OrderID VARCHAR (6),
	CONSTRAINT PRIMARY KEY (DamageID),
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(OrderID) REFERENCES CustomerOrders(OrderID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ReturnItem(
	ReturnID VARCHAR(6) NOT NULL,
	DamageID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	ReturnDate VARCHAR (10),
	Status VARCHAR(30),
	CONSTRAINT PRIMARY KEY (ReturnID),
	CONSTRAINT FOREIGN KEY(DamageID) REFERENCES DamageItem(DamageID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE UserAccount(
	UserID VARCHAR(6) NOT NULL,
	UserName VARCHAR(10) NOT NULL,
	Password VARCHAR(256) NOT NULL,
	SaltValue VARCHAR (256),
	CONSTRAINT PRIMARY KEY (UserID)
);

INSERT INTO UserAccount VALUES('U01','admin','1234');

DROP DATABASE GamageStockMgt;
CREATE DATABASE GamageStockMgt;
USE GamageStockMgt;

CREATE TABLE Customer(
	CustomerID VARCHAR(6) NOT NULL,
	CustomerName VARCHAR(25) NOT NULL,
	DOB VARCHAR (10),
	Address VARCHAR(30),
	Contact VARCHAR(15),
	NIC VARCHAR(12),
	CONSTRAINT PRIMARY KEY (CustomerID)
);

CREATE TABLE CustomerOrders(
	OrderID VARCHAR(6) NOT NULL,
	CustomerID VARCHAR(6)NOT NULL,
	OrderDate VARCHAR (10),
	CustomerPayment DECIMAL(10,2),
	CONSTRAINT PRIMARY KEY (OrderID),
	CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Warranty(
	WarrantyID VARCHAR(6) NOT NULL,
	WarrantyPeriod VARCHAR (6),
	WarrantyFrom VARCHAR (10),
	WarrantyTo VARCHAR (10),
	CONSTRAINT PRIMARY KEY (WarrantyID)
);

CREATE TABLE Items(
	ItemCode VARCHAR(6) NOT NULL,
	ItemName VARCHAR(30)NOT NULL,
	Brand VARCHAR(15) NOT NULL,
	ModelNo VARCHAR(20)NOT NULL,
	CONSTRAINT PRIMARY KEY (ItemCode)

);

CREATE TABLE Stock(
	StockID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	QtyOnHand int,
	SellingPrice decimal(10,2),
	CONSTRAINT PRIMARY KEY (StockID),
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE Supplier(
	SupplierID VARCHAR(6) NOT NULL,
	SupplierName VARCHAR(25) NOT NULL,
	Address VARCHAR(25) NOT NULL,
	Contact VARCHAR(15) NOT NULL,
	CompanyName VARCHAR(25) NOT NULL,
	CompanyAddress VARCHAR(25) NOT NULL,
	CompanyContact VARCHAR(15)NOT NULL,
	CONSTRAINT PRIMARY KEY (SupplierID)
);

CREATE TABLE OrderDetail(
	OrderID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	WarrantyID VARCHAR(6),
	UnitPrice DECIMAL(10,2),
	OrderQty INT,
	CONSTRAINT PRIMARY KEY (OrderID,ItemCode),
	CONSTRAINT FOREIGN KEY(WarrantyID) REFERENCES Warranty(WarrantyID)
	ON UPDATE CASCADE ON DELETE CASCADE


);
CREATE TABLE SupplierOrders(
	SupplierOrderID VARCHAR(6) NOT NULL,
	SupplierID VARCHAR(6)NOT NULL,
	SupplierOrderDate VARCHAR (10),
	CONSTRAINT PRIMARY KEY (SupplierOrderID),
	CONSTRAINT FOREIGN KEY(SupplierID) REFERENCES Supplier(SupplierID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE GRN(
	GRNID VARCHAR(6) NOT NULL,
	SupplierOrderID VARCHAR(6) NOT NULL,
	GRNDate VARCHAR (10),
	SupplierPayment DECIMAL(10,2),
	Status VARCHAR(20),
	CONSTRAINT PRIMARY KEY (GRNID),
	CONSTRAINT FOREIGN KEY(SupplierOrderID) REFERENCES SupplierOrders(SupplierOrderID)
	ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE GRNDetail(
	GRNID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	StockID VARCHAR(6) NOT NULL,
	BuyingPrice DECIMAL(10,2),
	Qty INT,
	CONSTRAINT PRIMARY KEY (GRNID,ItemCode,StockID)
);

CREATE TABLE SupplierOrderDetail(
	SupplierOrderID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	Qty INT,
	CONSTRAINT PRIMARY KEY (SupplierOrderID,ItemCode)
);

CREATE TABLE DamageItem(
	DamageID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	Fault VARCHAR(30),
	DamageType VARCHAR(20),
	DamageDate VARCHAR (10),
	OrderID VARCHAR (6),
	CONSTRAINT PRIMARY KEY (DamageID),
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(OrderID) REFERENCES CustomerOrders(OrderID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ReturnItem(
	ReturnID VARCHAR(6) NOT NULL,
	DamageID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	ReturnDate VARCHAR (10),
	Status VARCHAR(30),
	CONSTRAINT PRIMARY KEY (ReturnID),
	CONSTRAINT FOREIGN KEY(DamageID) REFERENCES DamageItem(DamageID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE UserAccount(
	UserID VARCHAR(6) NOT NULL,
	UserName VARCHAR(10) NOT NULL,
	Password VARCHAR(256) NOT NULL,
	SaltValue VARCHAR (256),
	CONSTRAINT PRIMARY KEY (UserID)
);

INSERT INTO UserAccount VALUES('U01','admin','1234');

DROP DATABASE GamageStockMgt;
CREATE DATABASE GamageStockMgt;
USE GamageStockMgt;

CREATE TABLE Customer(
	CustomerID VARCHAR(6) NOT NULL,
	CustomerName VARCHAR(25) NOT NULL,
	DOB VARCHAR (10),
	Address VARCHAR(30),
	Contact VARCHAR(15),
	NIC VARCHAR(12),
	CONSTRAINT PRIMARY KEY (CustomerID)
);

CREATE TABLE CustomerOrders(
	OrderID VARCHAR(6) NOT NULL,
	CustomerID VARCHAR(6)NOT NULL,
	OrderDate VARCHAR (10),
	CustomerPayment DECIMAL(10,2),
	CONSTRAINT PRIMARY KEY (OrderID),
	CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Warranty(
	WarrantyID VARCHAR(6) NOT NULL,
	WarrantyPeriod VARCHAR (6),
	WarrantyFrom VARCHAR (10),
	WarrantyTo VARCHAR (10),
	CONSTRAINT PRIMARY KEY (WarrantyID)
);

CREATE TABLE Items(
	ItemCode VARCHAR(6) NOT NULL,
	ItemName VARCHAR(30)NOT NULL,
	Brand VARCHAR(15) NOT NULL,
	ModelNo VARCHAR(20)NOT NULL,
	CONSTRAINT PRIMARY KEY (ItemCode)

);

CREATE TABLE Stock(
	StockID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	QtyOnHand int,
	SellingPrice decimal(10,2),
	CONSTRAINT PRIMARY KEY (StockID),
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE Supplier(
	SupplierID VARCHAR(6) NOT NULL,
	SupplierName VARCHAR(25) NOT NULL,
	Address VARCHAR(25) NOT NULL,
	Contact VARCHAR(15) NOT NULL,
	CompanyName VARCHAR(25) NOT NULL,
	CompanyAddress VARCHAR(25) NOT NULL,
	CompanyContact VARCHAR(15)NOT NULL,
	CONSTRAINT PRIMARY KEY (SupplierID)
);

CREATE TABLE OrderDetail(
	OrderID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	WarrantyID VARCHAR(6),
	UnitPrice DECIMAL(10,2),
	OrderQty INT,
	CONSTRAINT PRIMARY KEY (OrderID,ItemCode),
	CONSTRAINT FOREIGN KEY(WarrantyID) REFERENCES Warranty(WarrantyID)
	ON UPDATE CASCADE ON DELETE CASCADE


);
CREATE TABLE SupplierOrders(
	SupplierOrderID VARCHAR(6) NOT NULL,
	SupplierID VARCHAR(6)NOT NULL,
	SupplierOrderDate VARCHAR (10),
	CONSTRAINT PRIMARY KEY (SupplierOrderID),
	CONSTRAINT FOREIGN KEY(SupplierID) REFERENCES Supplier(SupplierID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE GRN(
	GRNID VARCHAR(6) NOT NULL,
	SupplierOrderID VARCHAR(6) NOT NULL,
	GRNDate VARCHAR (10),
	SupplierPayment DECIMAL(10,2),
	Status VARCHAR(20),
	CONSTRAINT PRIMARY KEY (GRNID),
	CONSTRAINT FOREIGN KEY(SupplierOrderID) REFERENCES SupplierOrders(SupplierOrderID)
	ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE GRNDetail(
	GRNID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	StockID VARCHAR(6) NOT NULL,
	BuyingPrice DECIMAL(10,2),
	Qty INT,
	CONSTRAINT PRIMARY KEY (GRNID,ItemCode,StockID)
);

CREATE TABLE SupplierOrderDetail(
	SupplierOrderID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	Qty INT,
	CONSTRAINT PRIMARY KEY (SupplierOrderID,ItemCode)
);

CREATE TABLE DamageItem(
	DamageID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	Fault VARCHAR(30),
	DamageType VARCHAR(20),
	DamageDate VARCHAR (10),
	OrderID VARCHAR (6),
	CONSTRAINT PRIMARY KEY (DamageID),
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(OrderID) REFERENCES CustomerOrders(OrderID)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ReturnItem(
	ReturnID VARCHAR(6) NOT NULL,
	DamageID VARCHAR(6) NOT NULL,
	ItemCode VARCHAR(6) NOT NULL,
	ReturnDate VARCHAR (10),
	Status VARCHAR(30),
	CONSTRAINT PRIMARY KEY (ReturnID),
	CONSTRAINT FOREIGN KEY(DamageID) REFERENCES DamageItem(DamageID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Items(ItemCode)
	ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE UserAccount(
	UserID VARCHAR(6) NOT NULL,
	UserName VARCHAR(10) NOT NULL,
	Password VARCHAR(256) NOT NULL,
	SaltValue VARCHAR (256),
	CONSTRAINT PRIMARY KEY (UserID)
);

INSERT INTO UserAccount VALUES('U01','admin','1234');

