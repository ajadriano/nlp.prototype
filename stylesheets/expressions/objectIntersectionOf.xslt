<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[VBZ[@cop]][NP][count(*)=2]/NP[SBAR[WDT[position() = 1]][S[count(*)=1]/VP[position() = 1]]]">
ObjectIntersectionOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="SBAR/S/VP"/>)
</xsl:template>

</xsl:stylesheet>