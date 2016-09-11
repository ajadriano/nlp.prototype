<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[MD[@aux] and VP]">
   <xsl:apply-templates select="VP"/>
</xsl:template>

<xsl:template match="VP[count(preceding-sibling::RB[@neg]) = 0][(VB|VBZ|VBP)[@cop and @lemma='be']][NP[count(preceding-sibling::RB[@neg]) = 0]]">
   <xsl:apply-templates select="NP"/>
</xsl:template>

</xsl:stylesheet>