<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[VBZ[@relcl]][NP[count(*)=2][QP[position()=1]][NNS]]">
ObjectMinCardinality(
   	<xsl:value-of select="NP/QP/CD"/>
   	<xsl:text> </xsl:text> 
   	<xsl:value-of select="VBZ"/>
   	<xsl:value-of select="concat(upper-case(substring(NP/NNS/@lemma,1,1)), substring(NP/NNS/@lemma, 2))"/>)
</xsl:template>

</xsl:stylesheet>