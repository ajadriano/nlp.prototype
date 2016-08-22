<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="NP[count(NP)>0 and CC[@lemma='or']]">
ObjectUnionOf(
        <xsl:for-each select="NP">
            <xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="." />
            </xsl:call-template>
        </xsl:for-each>
        <xsl:text> </xsl:text>)
</xsl:template>

</xsl:stylesheet>