-- 1.

GO
CREATE VIEW vw_Funcionarios_Nao_Chefes
AS
SELECT *
FROM Employees e
WHERE NOT EXISTS (
    SELECT 1
    FROM Employees sub
    WHERE sub.ReportsTo = e.EmployeeID
);
GO

-- 2.
GO
CREATE VIEW vw_Vendas_Produto
AS
SELECT P.ProductName, SUM(OD.Quantity) AS QuantidadeVendas
FROM [Order Details] AS OD

INNER JOIN Products AS P
ON P.ProductID = OD.ProductID

GROUP BY P.ProductName;
GO

-- 3.
GO
CREATE VIEW vw_Territorios_Vendedores
AS
SELECT
    T.TerritoryID,
    T.TerritoryDescription,
    COUNT(DISTINCT ET.EmployeeID) AS QuantidadeVendedores
FROM Territories AS T

LEFT JOIN EmployeeTerritories AS ET
    ON ET.TerritoryID = T.TerritoryID

GROUP BY
    T.TerritoryID,
    T.TerritoryDescription;
GO

-- 4.
GO
CREATE VIEW vw_Nome_Cliente_Venda_Maior_Valor
AS
SELECT TOP 1
    C.CompanyName AS Cliente,
    O.OrderID,
    SUM(OD.UnitPrice * OD.Quantity * (1 - OD.Discount)) AS ValorVenda
FROM Orders AS O

INNER JOIN Customers AS C
    ON C.CustomerID = O.CustomerID

INNER JOIN [Order Details] AS OD
    ON OD.OrderID = O.OrderID

GROUP BY
    C.CompanyName,
    O.OrderID

ORDER BY SUM(OD.UnitPrice * OD.Quantity * (1 - OD.Discount)) DESC;
GO

-- 5.
GO
CREATE VIEW vw_Vendedores_Lucratividade
AS
SELECT
    E.EmployeeID,
    E.FirstName + ' ' + E.LastName AS Vendedor,
    SUM(OD.UnitPrice * OD.Quantity * (1 - OD.Discount)) AS Lucratividade
FROM Employees AS E

INNER JOIN Orders AS O
    ON O.EmployeeID = E.EmployeeID

INNER JOIN [Order Details] AS OD
    ON OD.OrderID = O.OrderID

GROUP BY
    E.EmployeeID,
    E.FirstName,
    E.LastName;
GO

SELECT *
FROM vw_Vendedores_Lucratividade
ORDER BY Lucratividade DESC;

-- 6.
GO
CREATE VIEW vw_Produtos_Estoque
AS
SELECT
    P.ProductName AS Produto,
    S.CompanyName AS Fornecedor,
    C.CategoryName AS Categoria,
    P.UnitPrice AS Preco,
    CASE
        WHEN P.Discontinued = 1 THEN 'Sim'
        ELSE 'Nao'
    END AS Descontinuado
FROM Products AS P

INNER JOIN Suppliers AS S
    ON S.SupplierID = P.SupplierID

INNER JOIN Categories AS C
    ON C.CategoryID = P.CategoryID

WHERE P.UnitsInStock > 0;
GO
