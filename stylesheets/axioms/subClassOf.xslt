<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/ROOT/S[NP[DT[position() = 1 and @lemma = 'all']][*[@nsubj]] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NNS[@nsubj] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NNS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>