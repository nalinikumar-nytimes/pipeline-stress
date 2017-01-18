<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:template match="/">
      <html>
		<table>
		<xsl:for-each select="testResults/sample">
		  <tr>
		    <td><xsl:value-of select="@lt"/></td>
		    <td><xsl:value-of select="@ts"/></td>
		  </tr>  
		</xsl:for-each>
		</table>
	    </html>	
	</xsl:template>

</xsl:stylesheet>