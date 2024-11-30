<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:template match="/">
    	<html>
    		<body>
    			<h2>Orarend HD9DOJ</h2>
    			
    			<table border ="4">
    				<tr bgcolor="#90EE90">
    					<th>id</th>
    					<th>tipus</th>
    					<th>targy</th>
    					<th>idopont</th>
    					<th>helyszin</th>
    					<th>oktato</th>
						<th>szak</th>
    				</tr>
    				
    				<xsl:for-each select="HB_orarend/ora">
    					<tr>
    						<td><xsl:value-of select = "@id"/></td>
							<td><xsl:value-of select = "@tipus"/></td>
    						<td><xsl:value-of select = "targy"/></td>
    						<td><xsl:value-of select = "idopont"/></td>
    						<td><xsl:value-of select = "helyszin"/></td>
    						<td><xsl:value-of select = "oktato"/></td>
    						<td><xsl:value-of select = "szak"/></td>
    					</tr>
    				</xsl:for-each>
    			</table>
    		</body>
    	</html>
    </xsl:template>
    
</xsl:stylesheet>