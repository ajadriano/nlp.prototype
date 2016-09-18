<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="/ROOT/SQ[(VB|VBZ|VBP)[@lemma='be']][NP[position()=1 and count(NNP|NNPS|PRP)>0]][NP[.//DT and position()=2]]">
IsInstanceOf(
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="NP[position()=2]"/>)
</xsl:template>

<xsl:template match="/ROOT/SQ[(VB|VBZ|VBP)[@lemma='be']][NP[position()=1 and count(NNP|NNPS|PRP)>0]][ADJP]">
IsInstanceOf(
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="ADJP"/>)
</xsl:template>

<xsl:template match="/ROOT/SQ[(VB|VBZ|VBP)[@lemma='be']][NNP|NNPS|PRP][NP[.//DT]]">
IsInstanceOf(
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP|NNPS|PRP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="NP"/>)
</xsl:template>

</xsl:stylesheet>