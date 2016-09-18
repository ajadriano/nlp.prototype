<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[(VB|VBZ|VBP)[@lemma!='be' and @lemma!='have']][NP[RB/@advmod=(NN|NNS)/@id]]">
ObjectAllValuesFrom(
    <xsl:call-template name="verb_to_object_property">
            <xsl:with-param name="verb" select="(VB|VBZ|VBP)" />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:apply-templates select="NP"/>)
</xsl:template>

<xsl:template match="VP[preceding-sibling::ADVP/RB/@advmod=(VB|VBZ|VBP)/@id and (VB|VBZ|VBP)[@lemma!='be' and @lemma!='have']][NP]">
ObjectAllValuesFrom(
    <xsl:call-template name="verb_to_object_property">
            <xsl:with-param name="verb" select="(VB|VBZ|VBP)" />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:apply-templates select="NP"/>)
</xsl:template>

</xsl:stylesheet>