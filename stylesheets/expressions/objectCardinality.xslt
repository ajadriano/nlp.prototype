<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[(VBP|VBZ)[@lemma='have'] and NP[QP/CD/@nummod=(NN|NNS)/@id and QP[JJS[@lemma='least']]]]">
ObjectMinCardinality(
        <xsl:value-of select="NP/QP/CD/@number"/>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_have_object_property">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[(VBP|VBZ)[@lemma='have'] and NP[QP/CD/@nummod=(NN|NNS)/@id and QP[JJS[@lemma='most']]]]">
ObjectMaxCardinality(
        <xsl:value-of select="NP/QP/CD/@number"/>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_have_object_property">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[(VBP|VBZ)[@lemma='have'] and NP[CD/@nummod=(NN|NNS)/@id and RB[@lemma='exactly']/@advmod=(NN|NNS)/@id]]">
ObjectExactCardinality(
        <xsl:value-of select="NP/CD/@number"/>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_have_object_property">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[(VBP|VBZ)[@lemma='have'] and NP[CD/@nummod=(NN|NNS)/@id and count(RB)=0]]">
ObjectExactCardinality(
        <xsl:value-of select="NP/CD/@number"/>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_have_object_property">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>