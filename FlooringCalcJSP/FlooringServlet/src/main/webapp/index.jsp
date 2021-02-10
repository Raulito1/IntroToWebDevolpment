<%-- 
    Document   : index
    Created on : Aug 4, 2020, 6:34:10 PM
    Author     : raulalvarado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator</title>
    </head>
    <body>
        <h1>Flooring Calculator</h1>
        <p>
            Please enter your flooring project specs below:
        </p>
        <p>
        <form method="POST" action="FlooringServlet">
            <label for="width">Width:</label>
            <input type="test" name="width" id="width">
            <br/>
            <label for="length">Length:</label>
            <input type="test" name="length" id="length">
            <br/>
            <label for="cpsf">Cost Per Square Foot:</label>
            <input type="test" name="costPerSqFt" id="cpsf">
            <br/>
            <input type="submit" value="Calculate flooring!!!">
        </form>
    </p> 

</body>
</html>

