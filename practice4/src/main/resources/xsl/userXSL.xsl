<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- Указываем вывод в формате HTML -->
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="en">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>User List</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    padding: 20px;
                    background-color: #f9f9f9;
                    }
                    h1 {
                    color: #333;
                    }
                    .table-container {
                    max-width: 400px;
                    background: #fff;
                    padding: 20px;
                    border-radius: 8px;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    }
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    }
                    th, td {
                    padding: 12px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                    }
                    th {
                    background-color: #4CAF50;
                    color: white;
                    }
                    tr:hover {
                    background-color: #f1f1f1;
                    cursor: pointer;
                    }
                    a {
                    text-decoration: none;
                    color: inherit;
                    display: block;
                    }
                    a:hover {
                    color: #4CAF50;
                    }
                    .header {
                    display: flex;
                    justify-content: center;
                    background-color: #4CAF50;
                    padding: 10px 0;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    }
                    .header a {
                    text-decoration: none;
                    color: white;
                    padding: 14px 20px;
                    margin: 0 10px;
                    border-radius: 4px;
                    font-size: 16px;
                    transition: background-color 0.3s ease;
                    }
                    .header a:hover {
                    background-color: #45a049;
                    }
                    .content {
                    padding: 20px;
                    margin: 20px auto;
                    max-width: 800px;
                    background: #fff;
                    border-radius: 8px;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    }
                </style>
            </head>
            <body>

                <div class="header">
                    <button>
                        <a href="/userAdd">Create User</a>
                    </button>
                    <form action="/userList" method="get">
                        <button type="submit">User List</button>
                    </form>
                    <button>
                        <a href="/bookAdd">Create Book</a>
                    </button>
                    <form action="/bookList" method="get">
                        <button type="submit">Book List</button>
                    </form>
                </div>

                <h1>User List</h1>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>User</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Применяем цикл для каждого пользователя -->
                            <xsl:for-each select="ArrayList/item">
                                <tr>
                                    <td>
                                        <a href="../user/{id}">
                                            <xsl:value-of select="name"/>
                                            <xsl:text> </xsl:text>
                                            <xsl:value-of select="surname"/>
                                        </a>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>