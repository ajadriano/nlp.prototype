<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(*)=1]/VP[(VB|VBZ)[@cop]][JJ[@relcl] and count(RB[@neg])=0]">
    DataHasBoolValue(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        true)
</xsl:template>

<xsl:template match="S[count(*)=1]/VP[(VB|VBZ)[@cop]][JJ[@relcl] and count(RB[@neg])>0]">
    DataHasBoolValue(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        false)
</xsl:template>

</xsl:stylesheet>