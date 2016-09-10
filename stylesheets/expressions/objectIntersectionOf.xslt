<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="NP[NP[*[position() = 1 and @det]]][SBAR[WHNP[position() = 1]][S]]">
ObjectIntersectionOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="SBAR/S"/>)
</xsl:template>

<xsl:template match="NP[*[position()= 1 and @det] and JJ/@amod=.//*/@id]">
ObjectIntersectionOf(
	<xsl:apply-templates select="NN|NNS"/>
        <xsl:text> </xsl:text>
        DataHasBoolValue(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        true))
</xsl:template>


</xsl:stylesheet>